package com.yoti.api.client.docs.session.create;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleSdkConfigBuilderTest {

    private static final String SOME_PRIMARY_COLOUR = "#FFFFFF";
    private static final String SOME_SECONDARY_COLOUR = "#679bdd";
    private static final String SOME_FONT_COLOUR = "#b40c12";
    private static final String SOME_LOCALE = "en";
    private static final String SOME_PRESET_ISSUING_COUNTRY = "USA";

    private static final String SOME_SUCCESS_URL = "https://yourdomain.com/some/success/endpoint";
    private static final String SOME_ERROR_URL = "https://yourdomain.com/some/error/endpoint";

    @Test
    public void shouldBuildSimpleSdkConfigWithAllOptions() {
        SdkConfig result = new SimpleSdkConfigBuilder()
                .withAllowsCamera()
                .withPrimaryColour(SOME_PRIMARY_COLOUR)
                .withSecondaryColour(SOME_SECONDARY_COLOUR)
                .withFontColour(SOME_FONT_COLOUR)
                .withLocale(SOME_LOCALE)
                .withPresetIssuingCountry(SOME_PRESET_ISSUING_COUNTRY)
                .withSuccessUrl(SOME_SUCCESS_URL)
                .withErrorUrl(SOME_ERROR_URL)
                .build();

        assertThat(result, instanceOf(SimpleSdkConfig.class));

        assertEquals(result.getAllowedCaptureMethods(), "CAMERA");
        assertEquals(result.getPrimaryColour(), SOME_PRIMARY_COLOUR);
        assertEquals(result.getSecondaryColour(), SOME_SECONDARY_COLOUR);
        assertEquals(result.getFontColour(), SOME_FONT_COLOUR);
        assertEquals(result.getLocale(), SOME_LOCALE);
        assertEquals(result.getPresetIssuingCountry(), SOME_PRESET_ISSUING_COUNTRY);
        assertEquals(result.getSuccessUrl(), SOME_SUCCESS_URL);
        assertEquals(result.getErrorUrl(), SOME_ERROR_URL);
    }

    @Test
    public void shouldBuildSimpleSdkConfigWithOnlyCamera() {
        SdkConfig result = new SimpleSdkConfigBuilder()
                .withAllowsCamera()
                .build();

        assertEquals(result.getAllowedCaptureMethods(), "CAMERA");
    }

    @Test
    public void shouldBuildSimpleSdkConfigWithCameraAndUpload() {
        SdkConfig result = new SimpleSdkConfigBuilder()
                .withAllowsCameraAndUpload()
                .build();

        assertEquals(result.getAllowedCaptureMethods(), "CAMERA_AND_UPLOAD");
    }

    @Test
    public void shouldOverridePreviousAllowedCaptureMethods() {
        SdkConfig result = new SimpleSdkConfigBuilder()
                .withAllowsCameraAndUpload()
                .withAllowsCamera()
                .build();

        assertEquals(result.getAllowedCaptureMethods(), "CAMERA");
    }


}
