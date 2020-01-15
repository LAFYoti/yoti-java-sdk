package com.yoti.api.client.sandbox.docs.resource.document;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PageDescriptorTest {

    private static final String SOME_CAPTURE_METHOD = "CAMERA";
    private static final Region SOME_REGION = Region.valid();

    @Test
    public void Builder_shouldThrowExceptionWhenRegionsSetToNull() {
        try {
            new PageDescriptor.Builder()
                    .withRegions(null)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("regions"));
            return;
        }
        fail("Expected and exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenNoCaptureMethodSupplied() {
        try {
            new PageDescriptor.Builder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("captureMethod"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldBuildWithValidProperties() {
        PageDescriptor result = new PageDescriptor.Builder()
                .withCaptureMethod(SOME_CAPTURE_METHOD)
                .withRegion(SOME_REGION)
                .build();

        assertThat(result.getCaptureMethod(), is(SOME_CAPTURE_METHOD));
        assertThat(result.getRegions(), hasSize(1));
        assertThat(result.getRegions().get(0), is(SOME_REGION));
    }

    @Test
    public void Builder_shouldDefaultRegionsToEmptyList() {
        PageDescriptor result = new PageDescriptor.Builder()
                .withCaptureMethod("CAMERA")
                .build();

        assertNotNull(result.getRegions());
        assertThat(result.getRegions(), hasSize(0));
    }

    @Test
    public void valid_shouldReturnPageDescriptorWithThreeRegionsAndValidCaptureMethod() {
        PageDescriptor result = PageDescriptor.valid();

        assertThat(result.getCaptureMethod(), is("CAMERA"));
        assertThat(result.getRegions(), hasSize(3));
    }

}
