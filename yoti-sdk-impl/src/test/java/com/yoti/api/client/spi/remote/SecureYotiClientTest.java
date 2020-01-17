package com.yoti.api.client.spi.remote;

import static com.yoti.api.client.spi.remote.util.CryptoUtil.base64Url;
import static com.yoti.api.client.spi.remote.util.CryptoUtil.encryptAsymmetric;
import static com.yoti.api.client.spi.remote.util.CryptoUtil.generateKeyPairFrom;
import static com.yoti.api.client.spi.remote.util.CryptoUtil.generateSymmetricKey;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;

import com.yoti.api.client.ActivityDetails;
import com.yoti.api.client.InitialisationException;
import com.yoti.api.client.KeyPairSource;
import com.yoti.api.client.ProfileException;
import com.yoti.api.client.docs.YotiDocsException;
import com.yoti.api.client.docs.session.create.SessionSpec;
import com.yoti.api.client.spi.remote.call.Receipt;
import com.yoti.api.client.spi.remote.call.aml.RemoteAmlService;
import com.yoti.api.client.spi.remote.call.docs.YotiDocsService;
import com.yoti.api.client.spi.remote.call.qrcode.DynamicSharingService;
import com.yoti.api.client.spi.remote.util.CryptoUtil;

import org.bouncycastle.openssl.PEMException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SecureYotiClientTest {

    private static final String TOKEN = "test-token-test-test-test";
    private static final String APP_ID = "appId";
    private static final String SOME_SESSION_ID = "someSessionId";
    private static final String SOME_MEDIA_ID = "someMediaId";

    @Mock ReceiptFetcher receiptFetcherMock;
    @Mock RemoteAmlService remoteAmlServiceMock;
    @Mock ActivityDetailsFactory activityDetailsFactoryMock;
    @Mock DynamicSharingService sharingServiceMock;
    @Mock YotiDocsService yotiDocsServiceMock;

    @Mock Receipt receiptMock;
    @Mock ActivityDetails activityDetailsMock;
    @Mock SessionSpec sessionSpecMock;
    String encryptedToken;
    KeyPairSource validKeyPairSource;
    byte[] validReceiptKey;

    @Before
    public void setUp() throws Exception {
        Key receiptKey = generateSymmetricKey();
        KeyPair keyPair = generateKeyPairFrom(CryptoUtil.KEY_PAIR_PEM);
        validReceiptKey = encryptAsymmetric(receiptKey.getEncoded(), keyPair.getPublic());

        encryptedToken = base64Url(encryptAsymmetric(TOKEN.getBytes(), keyPair.getPublic()));
        validKeyPairSource = new StaticKeyPairSource(CryptoUtil.KEY_PAIR_PEM);
    }

    @Test
    public void getActivityDetails_shouldFailWithExceptionFromReceiptFetcher() throws Exception {
        ProfileException profileException = new ProfileException("Test exception");
        when(receiptFetcherMock.fetch(eq(encryptedToken), any(KeyPair.class), eq(APP_ID))).thenThrow(profileException);

        try {
            SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
            testObj.getActivityDetails(encryptedToken);
        } catch (ProfileException e) {
            assertSame(profileException, e);
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void getActivityDetails_shouldFailWithExceptionFromActivityDetailsFactory() throws Exception {
        ProfileException profileException = new ProfileException("Test exception");
        when(receiptFetcherMock.fetch(eq(encryptedToken), any(KeyPair.class), eq(APP_ID))).thenReturn(receiptMock);
        when(activityDetailsFactoryMock.create(eq(receiptMock), any(PrivateKey.class))).thenThrow(profileException);

        try {
            SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
            testObj.getActivityDetails(encryptedToken);
        } catch (ProfileException e) {
            assertSame(profileException, e);
            verify(activityDetailsFactoryMock).create(eq(receiptMock), any(PrivateKey.class));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void getActivityDetails_shouldReturnActivityDetails() throws Exception {
        when(receiptFetcherMock.fetch(eq(encryptedToken), any(KeyPair.class), eq(APP_ID))).thenReturn(receiptMock);
        when(activityDetailsFactoryMock.create(eq(receiptMock), any(PrivateKey.class))).thenReturn(activityDetailsMock);

        SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        ActivityDetails result = testObj.getActivityDetails(encryptedToken);

        assertSame(activityDetailsMock, result);
    }

    @Test
    public void constructor_shouldFailWhenStreamExceptionLoadingKeys() {
        KeyPairSource badKeyPairSource = new StaticKeyPairSource(true);

        try {
            new SecureYotiClient(APP_ID, badKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (InitialisationException e) {
            assertTrue(e.getCause() instanceof IOException);
            assertThat(e.getCause().getMessage(), containsString("Test stream exception"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWhenKeyPairSourceExceptionLoadingKeys() {
        KeyPairSource badKeyPairSource = new StaticKeyPairSource(false);

        try {
            new SecureYotiClient(APP_ID, badKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (InitialisationException e) {
            assertTrue(e.getCause() instanceof IOException);
            assertThat(e.getCause().getMessage(), containsString("Test source exception"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWithNullApplicationId() {
        try {
            new SecureYotiClient(null, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("Application id"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWithNullKeyPairSource() {
        try {
            new SecureYotiClient(APP_ID, null, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("Key pair source"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWithNullReceiptFetcher() {
        try {
            new SecureYotiClient(APP_ID, validKeyPairSource, null, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("receiptFetcher"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWithNullActivityDetailsFactory() {
        try {
            new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, null, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("activityDetailsFactory"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWithNullAmlService() {
        try {
            new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, null, sharingServiceMock, yotiDocsServiceMock);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("amlService"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWithNoKeyPair() {
        KeyPairSource invalidKeyPairSource = new StaticKeyPairSource("no-key-pair-in-file");

        try {
            new SecureYotiClient(APP_ID, invalidKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (InitialisationException e) {
            assertThat(e.getMessage(), containsString("No key pair found in the provided source"));
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void constructor_shouldFailWithInvalidKeyPair() {
        KeyPairSource invalidKeyPairSource = new StaticKeyPairSource(CryptoUtil.INVALID_KEY_PAIR_PEM);

        try {
            new SecureYotiClient(APP_ID, invalidKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
        } catch (InitialisationException e) {
            assertThat(e.getMessage(), containsString("Cannot load key pair"));
            assertTrue(e.getCause() instanceof PEMException);
            return;
        }
        fail("Expected an Exception");
    }

    @Test
    public void createYotiDocsSession_shouldFailWithExceptionFromYotiDocsService() throws Exception {
        YotiDocsException original = new YotiDocsException("Test exception");

        when(yotiDocsServiceMock.createSession(eq(APP_ID), any(KeyPair.class), eq(sessionSpecMock))).thenThrow(original);

        try {
            SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
            testObj.createDocScanSession(sessionSpecMock);
        } catch (YotiDocsException thrown) {
            assertSame(thrown, original);
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void getYotiDocsSession_shouldFailWithExceptionFromYotiDocsService() throws Exception {
        YotiDocsException original = new YotiDocsException("Test exception");

        when(yotiDocsServiceMock.retrieveSession(eq(APP_ID), any(KeyPair.class), eq(SOME_SESSION_ID))).thenThrow(original);

        try {
            SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
            testObj.getDocScanSession(SOME_SESSION_ID);
        } catch (YotiDocsException thrown) {
            assertSame(thrown, original);
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void getYotiDocsMedia_shouldFailWithExceptionFromYotiDocsService() throws Exception {
        YotiDocsException original = new YotiDocsException("Test exception");

        when(yotiDocsServiceMock.getMediaContent(eq(APP_ID), any(KeyPair.class), eq(SOME_SESSION_ID), eq(SOME_MEDIA_ID))).thenThrow(original);

        try {
            SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
            testObj.getDocScanMedia(SOME_SESSION_ID, SOME_MEDIA_ID);
        } catch (YotiDocsException thrown) {
            assertSame(original, thrown);
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void deleteYotiDocsMedia_shouldFailWithExceptionFromYotiDocsService() throws Exception {
        YotiDocsException original = new YotiDocsException("Test exception");

        doThrow(original).when(yotiDocsServiceMock).deleteMediaContent(eq(APP_ID), any(KeyPair.class), eq(SOME_SESSION_ID), eq(SOME_MEDIA_ID));

        try {
            SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
            testObj.deleteDocScanMedia(SOME_SESSION_ID, SOME_MEDIA_ID);
        } catch (YotiDocsException thrown) {
            assertSame(thrown, original);
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void deleteYotiDocsSession_shouldFailWithExceptionFromYotiDocsService() throws Exception {
        YotiDocsException original = new YotiDocsException("Test exception");

        doThrow(original).when(yotiDocsServiceMock).deleteSession(eq(APP_ID), any(KeyPair.class), eq(SOME_SESSION_ID));

        try {
            SecureYotiClient testObj = new SecureYotiClient(APP_ID, validKeyPairSource, receiptFetcherMock, activityDetailsFactoryMock, remoteAmlServiceMock, sharingServiceMock, yotiDocsServiceMock);
            testObj.deleteDocScanSession(SOME_SESSION_ID);
        } catch (YotiDocsException thrown) {
            assertSame(thrown, original);
            return;
        }
        fail("Expected an exception");
    }

    private static class StaticKeyPairSource implements KeyPairSource {

        private String keypair;
        private boolean streamException;
        private boolean sourceException;

        public StaticKeyPairSource(boolean inStreamException) {
            this.streamException = inStreamException;
            this.sourceException = !inStreamException;
        }

        public StaticKeyPairSource(String keypair) {
            this.keypair = keypair;
        }

        @Override
        public KeyPair getFromStream(StreamVisitor streamVisitor) throws IOException, InitialisationException {
            if (sourceException) {
                throw new IOException("Test source exception");
            }
            if (streamException) {
                InputStream inputStreamMock = mock(InputStream.class);
                when(inputStreamMock.read(any(byte[].class), anyInt(), anyInt())).thenThrow(new IOException("Test stream exception"));
                return streamVisitor.accept(inputStreamMock);
            } else {
                InputStream is = new ByteArrayInputStream(keypair.getBytes());
                return streamVisitor.accept(is);
            }
        }

    }

}
