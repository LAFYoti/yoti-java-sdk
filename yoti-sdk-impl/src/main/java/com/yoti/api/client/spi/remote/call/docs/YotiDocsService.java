package com.yoti.api.client.spi.remote.call.docs;

import static com.yoti.api.client.spi.remote.call.HttpMethod.HTTP_DELETE;
import static com.yoti.api.client.spi.remote.call.HttpMethod.HTTP_GET;
import static com.yoti.api.client.spi.remote.call.HttpMethod.HTTP_POST;
import static com.yoti.api.client.spi.remote.call.YotiConstants.DEFAULT_CHARSET;
import static com.yoti.api.client.spi.remote.call.YotiConstants.DEFAULT_YOTI_DOCS_URL;
import static com.yoti.api.client.spi.remote.call.YotiConstants.PROPERTY_YOTI_DOCS_URL;
import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.util.List;
import java.util.Map;

import com.yoti.api.client.Media;
import com.yoti.api.client.docs.YotiDocsException;
import com.yoti.api.client.docs.session.create.CreateSessionResult;
import com.yoti.api.client.docs.session.create.SessionSpec;
import com.yoti.api.client.docs.session.create.SimpleCreateSessionResult;
import com.yoti.api.client.docs.session.retrieve.YotiDocsSession;
import com.yoti.api.client.spi.remote.MediaValue;
import com.yoti.api.client.spi.remote.call.*;
import com.yoti.api.client.spi.remote.call.factory.HeadersFactory;
import com.yoti.api.client.spi.remote.call.factory.PathFactory;
import com.yoti.api.client.spi.remote.call.factory.SignedMessageFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yoti.api.client.spi.remote.call.factory.UnsignedPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YotiDocsService {

    public static YotiDocsService newInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return new YotiDocsService(new UnsignedPathFactory(), objectMapper);
    }

    private static final Logger LOG = LoggerFactory.getLogger(YotiDocsService.class);

    private final UnsignedPathFactory unsignedPathFactory;
    private final ObjectMapper objectMapper;

    private final String apiUrl;

    private YotiDocsService(UnsignedPathFactory pathFactory, ObjectMapper objectMapper) {
        this.unsignedPathFactory = pathFactory;
        this.objectMapper = objectMapper;

        apiUrl = System.getProperty(PROPERTY_YOTI_DOCS_URL, DEFAULT_YOTI_DOCS_URL);
    }

    public CreateSessionResult createSession(String appId, KeyPair keyPair, SessionSpec sessionSpec) throws YotiDocsException {
        notNull(appId, "Application id");
        notNull(keyPair, "Application key Pair");
        notNull(sessionSpec, "sessionSpec");

        String path = unsignedPathFactory.createNewYotiDocsSessionPath(appId);
        LOG.info("Creating session at '{}'", path);

        try {
            byte[] payload = objectMapper.writeValueAsString(sessionSpec).getBytes(DEFAULT_CHARSET);

            SignedRequest signedRequest = SignedRequestBuilder.newInstance()
                    .withKeyPair(keyPair)
                    .withBaseUrl(apiUrl)
                    .withEndpoint(path)
                    .withHttpMethod(HTTP_POST)
                    .withPayload(payload)
                    .withHeader("Content-Type", "application/json")
                    .build();

            return signedRequest.execute(SimpleCreateSessionResult.class);
        } catch (GeneralSecurityException ex) {
            throw new YotiDocsException("Error signing the request: " + ex.getMessage(), ex);
        } catch (ResourceException ex) {
            throw new YotiDocsException("Error posting the request: " + ex.getMessage(), ex);
        } catch (IOException | URISyntaxException ex) {
            throw new YotiDocsException("Error building the request: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new YotiDocsException("Error creating the session: " + ex.getMessage(), ex);
        }
    }

    public YotiDocsSession retrieveSession(String appId, KeyPair keyPair, String sessionId) throws YotiDocsException {
        notNull(appId, "Application id");
        notNull(keyPair, "Application key Pair");
        notNull(sessionId, "sessionId");

        String path = unsignedPathFactory.createYotiDocsSessionPath(appId, sessionId);
        LOG.info("Fetching session from '{}'", path);

        try {
            SignedRequest signedRequest = SignedRequestBuilder.newInstance()
                    .withKeyPair(keyPair)
                    .withBaseUrl(apiUrl)
                    .withEndpoint(path)
                    .withHttpMethod(HTTP_GET)
                    .build();

            return signedRequest.execute(YotiDocsSession.class);
        } catch (GeneralSecurityException ex) {
            throw new YotiDocsException("Error signing the request: " + ex.getMessage(), ex);
        } catch (ResourceException ex) {
            throw new YotiDocsException("Error executing the GET: " + ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new YotiDocsException("Error building the request: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new YotiDocsException("Error retrieving the session: " + ex.getMessage(), ex);
        }
    }

    public void deleteSession(String appId, KeyPair keyPair, String sessionId) throws YotiDocsException {
        notNull(appId, "Application id");
        notNull(keyPair, "Application key Pair");
        notNull(sessionId, "sessionId");

        String path = unsignedPathFactory.createYotiDocsSessionPath(appId, sessionId);
        LOG.info("Deleting session from '{}'", path);

        try {
            SignedRequest signedRequest = SignedRequestBuilder.newInstance()
                    .withKeyPair(keyPair)
                    .withBaseUrl(apiUrl)
                    .withEndpoint(path)
                    .withHttpMethod(HTTP_DELETE)
                    .build();

            signedRequest.execute();
        } catch (GeneralSecurityException ex) {
            throw new YotiDocsException("Error signing the request: " + ex.getMessage(), ex);
        } catch (ResourceException ex) {
            throw new YotiDocsException("Error executing the DELETE: " + ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new YotiDocsException("Error building the request: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new YotiDocsException("Error deleting the session: " + ex.getMessage(), ex);
        }
    }

    public Media getMediaContent(String appId, KeyPair keyPair, String sessionId, String mediaId) throws YotiDocsException {
        notNull(appId, "Application id");
        notNull(keyPair, "Application key Pair");
        notNull(sessionId, "sessionId");
        notNull(sessionId, "mediaId");

        String path = unsignedPathFactory.createMediaContentPath(appId, sessionId, mediaId);
        LOG.info("Fetching media from '{}'", path);

        try {
            SignedRequest signedRequest = SignedRequestBuilder.newInstance()
                    .withKeyPair(keyPair)
                    .withBaseUrl(apiUrl)
                    .withEndpoint(path)
                    .withHttpMethod(HTTP_GET)
                    .build();

            return signedRequest.execute(MediaValue.class);
        } catch (GeneralSecurityException ex) {
            throw new YotiDocsException("Error signing the request: " + ex.getMessage(), ex);
        } catch (ResourceException ex) {
            throw new YotiDocsException("Error executing the GET: " + ex.getMessage(), ex);
        } catch (IOException | URISyntaxException ex) {
            throw new YotiDocsException("Error building the request: " + ex.getMessage(), ex);
        }
    }

    public void deleteMediaContent(String appId, KeyPair keyPair, String sessionId, String mediaId) throws YotiDocsException {
        notNull(appId, "Application id");
        notNull(keyPair, "Application key Pair");
        notNull(sessionId, "sessionId");
        notNull(mediaId, "mediaId");

        String path = unsignedPathFactory.createMediaContentPath(appId, sessionId, mediaId);
        LOG.info("Deleting media at '{}'", path);

        try {
            SignedRequest signedRequest = SignedRequestBuilder.newInstance()
                    .withKeyPair(keyPair)
                    .withBaseUrl(apiUrl)
                    .withEndpoint(path)
                    .withHttpMethod(HTTP_DELETE)
                    .build();

            signedRequest.execute();
        } catch (GeneralSecurityException ex) {
            throw new YotiDocsException("Error signing the request: " + ex.getMessage(), ex);
        } catch (ResourceException ex) {
            throw new YotiDocsException("Error executing the DELETE: " + ex.getMessage(), ex);
        } catch (IOException | URISyntaxException ex) {
            throw new YotiDocsException("Error building the request: " + ex.getMessage(), ex);
        }
    }

}
