package com.yoti.api.client.sandbox.docs.resource.liveness;

import com.yoti.api.client.sandbox.docs.resource.SandboxMedia;
import org.apache.http.entity.ContentType;

public class ZoomFrame implements SandboxMedia {

    private final byte[] binaryContent;
    private final ContentType contentType;

    public ZoomFrame(byte[] binaryContent, ContentType contentType) {
        this.binaryContent = binaryContent;
        this.contentType = contentType;
    }

    @Override
    public byte[] getBinaryContent() {
        return binaryContent.clone();
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }


}
