package com.yoti.api.client.docs.session.create;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

import com.yoti.api.client.docs.session.create.check.RequestedCheck;
import com.yoti.api.client.docs.session.create.check.SimpleRequestedCheckBuilderFactory;
import com.yoti.api.client.docs.session.create.task.RequestedTask;
import com.yoti.api.client.docs.session.create.task.SimpleRequestedTaskBuilderFactory;
import org.junit.Test;

public class SimpleSessionSpecBuilderTest {

    private static final Integer SOME_CLIENT_SESSION_TOKEN_TTL = 300;
    private static final Integer SOME_RESOURCES_TTL = 86000;
    private static final String SOME_USER_TRACKING_ID = "someUserTrackingId";

    private static final String SOME_NOTIFICATION_AUTH_TOKEN = "someAuthToken:someAuthPassword";
    private static final String SOME_NOTIFICATION_ENDPOINT = "https://yourdomain.com/some/notification/endpoint";

    private static final String SOME_SDK_CONFIG_PRIMARY_COLOUR = "#FFFFFF";
    private static final String SOME_SDK_CONFIG_SECONDARY_COLOUR = "#679bdd";
    private static final String SOME_SDK_CONFIG_FONT_COLOUR = "#b40c12";
    private static final String SOME_SDK_CONFIG_LOCALE = "en";
    private static final String SOME_SDK_CONFIG_PRESET_ISSUING_COUNTRY = "USA";

    private static final String SOME_SDK_CONFIG_SUCCESS_URL = "https://yourdomain.com/some/success/endpoint";
    private static final String SOME_SDK_CONFIG_ERROR_URL = "https://yourdomain.com/some/error/endpoint";
    
    @Test
    public void shouldBuildWithMinimalConfiguration() {
        SessionSpec result = new SimpleSessionSpecBuilder()
                .withClientSessionTokenTtl(SOME_CLIENT_SESSION_TOKEN_TTL)
                .withResourcesTtl(SOME_RESOURCES_TTL)
                .withUserTrackingId(SOME_USER_TRACKING_ID)
                .build();

        assertThat(result, instanceOf(SimpleSessionSpec.class));
        assertEquals(result.getClientSessionTokenTtl(), SOME_CLIENT_SESSION_TOKEN_TTL);
        assertEquals(result.getResourcesTtl(), SOME_RESOURCES_TTL);
        assertEquals(result.getUserTrackingId(), SOME_USER_TRACKING_ID);
    }

    @Test
    public void shouldRaiseForMissingClientSessionTokenTtl() {
        try {
            new SimpleSessionSpecBuilder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("clientSessionTokenTtl"));
        }
    }

    @Test
    public void shouldRaiseForMissingResourcesTtl() {
        try {
            new SimpleSessionSpecBuilder()
                    .withClientSessionTokenTtl(SOME_CLIENT_SESSION_TOKEN_TTL)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("resourcesTtl"));
        }
    }

    @Test
    public void shouldRaiseForMissingUserTrackingId() {
        try {
            new SimpleSessionSpecBuilder()
                    .withClientSessionTokenTtl(SOME_CLIENT_SESSION_TOKEN_TTL)
                    .withResourcesTtl(SOME_RESOURCES_TTL)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("userTrackingId"));
        }
    }

    @Test
    public void shouldBuildWithValidNotifications() {
        NotificationConfig notificationConfig = new SimpleNotificationConfigBuilder()
                .forResourceUpdate()
                .forSessionCompletion()
                .withAuthToken(SOME_NOTIFICATION_AUTH_TOKEN)
                .withEndpoint(SOME_NOTIFICATION_ENDPOINT)
                .build();

        SessionSpec result = new SimpleSessionSpecBuilder()
                .withClientSessionTokenTtl(SOME_CLIENT_SESSION_TOKEN_TTL)
                .withResourcesTtl(SOME_RESOURCES_TTL)
                .withUserTrackingId(SOME_USER_TRACKING_ID)
                .withNotifications(notificationConfig)
                .build();

        assertNotNull(result.getNotifications());
        assertThat(result.getNotifications().getTopics(), hasItems("RESOURCE_UPDATE", "SESSION_COMPLETION"));
        assertEquals(result.getNotifications().getAuthToken(), SOME_NOTIFICATION_AUTH_TOKEN);
        assertEquals(result.getNotifications().getEndpoint(), SOME_NOTIFICATION_ENDPOINT);
    }

    @Test
    public void shouldBuildWithValidRequestedChecks() {
        RequestedCheck authenticityCheck = new SimpleRequestedCheckBuilderFactory()
                .createAuthenticityCheckBuilder()
                .build();

        RequestedCheck livenessCheck = new SimpleRequestedCheckBuilderFactory()
                .createLivenessCheckBuilder()
                .forZoomLiveness()
                .withMaxRetries(3)
                .build();

        SessionSpec result = new SimpleSessionSpecBuilder()
                .withClientSessionTokenTtl(SOME_CLIENT_SESSION_TOKEN_TTL)
                .withResourcesTtl(SOME_RESOURCES_TTL)
                .withUserTrackingId(SOME_USER_TRACKING_ID)
                .withRequestedCheck(authenticityCheck)
                .withRequestedCheck(livenessCheck)
                .build();

        assertThat(result.getRequestedChecks(), hasSize(2));
        assertEquals(result.getRequestedChecks().get(0).getType(), "ID_DOCUMENT_AUTHENTICITY");
        assertEquals(result.getRequestedChecks().get(1).getType(), "LIVENESS");
    }

    @Test
    public void shouldBuildWithValidRequestedTasks() {
        RequestedTask textExtractionTask = new SimpleRequestedTaskBuilderFactory()
                .createTextExtractionTaskBuilder()
                .withManualFallbackAlways()
                .build();

        SessionSpec result = new SimpleSessionSpecBuilder()
                .withClientSessionTokenTtl(SOME_CLIENT_SESSION_TOKEN_TTL)
                .withResourcesTtl(SOME_RESOURCES_TTL)
                .withUserTrackingId(SOME_USER_TRACKING_ID)
                .withRequestedTask(textExtractionTask)
                .build();

        assertThat(result.getRequestedTasks(), hasSize(1));
        assertEquals(result.getRequestedTasks().get(0).getType(), "ID_DOCUMENT_TEXT_DATA_EXTRACTION");
    }
    
    @Test
    public void shouldBuildWithValidSdkConfig() {
        SdkConfig sdkConfig = new SimpleSdkConfigBuilder()
                .withAllowsCameraAndUpload()
                .withPrimaryColour(SOME_SDK_CONFIG_PRIMARY_COLOUR)
                .withSecondaryColour(SOME_SDK_CONFIG_SECONDARY_COLOUR)
                .withFontColour(SOME_SDK_CONFIG_FONT_COLOUR)
                .withLocale(SOME_SDK_CONFIG_LOCALE)
                .withPresetIssuingCountry(SOME_SDK_CONFIG_PRESET_ISSUING_COUNTRY)
                .withSuccessUrl(SOME_SDK_CONFIG_SUCCESS_URL)
                .withErrorUrl(SOME_SDK_CONFIG_ERROR_URL)
                .build();

        SessionSpec result = new SimpleSessionSpecBuilder()
                .withClientSessionTokenTtl(SOME_CLIENT_SESSION_TOKEN_TTL)
                .withResourcesTtl(SOME_RESOURCES_TTL)
                .withUserTrackingId(SOME_USER_TRACKING_ID)
                .withSdkConfig(sdkConfig)
                .build();

        assertNotNull(result.getSdkConfig());
        assertEquals(result.getSdkConfig().getAllowedCaptureMethods(), "CAMERA_AND_UPLOAD");
        assertEquals(result.getSdkConfig().getPrimaryColour(), SOME_SDK_CONFIG_PRIMARY_COLOUR);
        assertEquals(result.getSdkConfig().getSecondaryColour(), SOME_SDK_CONFIG_SECONDARY_COLOUR);
        assertEquals(result.getSdkConfig().getFontColour(), SOME_SDK_CONFIG_FONT_COLOUR);
        assertEquals(result.getSdkConfig().getLocale(), SOME_SDK_CONFIG_LOCALE);
        assertEquals(result.getSdkConfig().getPresetIssuingCountry(), SOME_SDK_CONFIG_PRESET_ISSUING_COUNTRY);
        assertEquals(result.getSdkConfig().getSuccessUrl(), SOME_SDK_CONFIG_SUCCESS_URL);
        assertEquals(result.getSdkConfig().getErrorUrl(), SOME_SDK_CONFIG_ERROR_URL);
    }

}
