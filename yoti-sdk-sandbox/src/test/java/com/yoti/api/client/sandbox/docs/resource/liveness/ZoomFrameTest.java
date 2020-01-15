package com.yoti.api.client.sandbox.docs.resource.liveness;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.http.entity.ContentType;
import org.junit.Test;

public class ZoomFrameTest {

    private static final byte[] SOME_BINARY_CONTENT = new byte[]{1, 2, 3, 4};
    private static final ContentType SOME_CONTENT_TYPE = ContentType.IMAGE_PNG;

    @Test
    public void shouldRetainBinaryContent() {
        ZoomFrame result = new ZoomFrame(SOME_BINARY_CONTENT, SOME_CONTENT_TYPE);

        assertThat(result.getBinaryContent(), equalTo(SOME_BINARY_CONTENT));
        assertThat(result.getContentType(), is(SOME_CONTENT_TYPE));
    }

}
