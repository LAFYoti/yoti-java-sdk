package com.yoti.api.client.sandbox.docs.resource.liveness;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ZoomLivenessResourceTest {

    private static final String WEB_SOURCE_VALUE = "WEB";
    private static final String NATIVE_SOURCE_VALUE = "NATIVE";

    @Test
    public void Builder_shouldThrowExceptionWhenMissingSourceProperty() {
        try {
            new ZoomLivenessResource.Builder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("source"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenSourcePropertyIsEmpty() {
        try {
            new ZoomLivenessResource.Builder()
                    .withSource("")
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("source"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldBuildZoomLivenessResourceWithCorrectProperties() {
        ZoomLivenessResource result = new ZoomLivenessResource.Builder()
                .withSource(WEB_SOURCE_VALUE)
                .build();

        assertThat(result.getSource(), is(WEB_SOURCE_VALUE));
    }

    @Test
    public void validWebZoomLiveness_shouldReturnCorrectSourceValue() {
        ZoomLivenessResource result = ZoomLivenessResource.validWebZoomLiveness();

        assertThat(result.getSource(), is(WEB_SOURCE_VALUE));
    }

    @Test
    public void validNativeZoomLiveness_shouldReturnCorrectSourceValue() {
        ZoomLivenessResource result = ZoomLivenessResource.validNativeZoomLiveness();

        assertThat(result.getSource(), is(NATIVE_SOURCE_VALUE));
    }

}
