package com.yoti.api.client.sandbox.docs.resource.document;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.apache.http.entity.ContentType;
import org.junit.Test;

public class PageTest {

    private static final PageDescriptor SOME_DESCRIPTOR = PageDescriptor.valid();
    private static final byte[] SOME_BINARY_CONTENT = new byte[]{1, 2, 3, 4};
    private static final ContentType SOME_CONTENT_TYPE = ContentType.IMAGE_PNG;

    @Test
    public void Builder_shouldThrowExceptionForMissingDescriptor() {
        try {
            new Page.Builder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("pageDescriptor"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionForMissingContentType() {
        try {
            new Page.Builder()
                    .withDescriptor(SOME_DESCRIPTOR)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("contentType"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionForMissingBinaryContent() {
        try {
            new Page.Builder()
                    .withDescriptor(SOME_DESCRIPTOR)
                    .withContentType(SOME_CONTENT_TYPE)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("binaryContent"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldCreatePageFromValidProperties() {
        Page result = new Page.Builder()
                .withDescriptor(SOME_DESCRIPTOR)
                .withContentType(SOME_CONTENT_TYPE)
                .withBinaryContent(SOME_BINARY_CONTENT)
                .build();

        assertThat(result.getDescriptor(), is(SOME_DESCRIPTOR));
        assertThat(result.getContentType(), is(SOME_CONTENT_TYPE));
        assertThat(result.getBinaryContent(), equalTo(SOME_BINARY_CONTENT));
    }

}
