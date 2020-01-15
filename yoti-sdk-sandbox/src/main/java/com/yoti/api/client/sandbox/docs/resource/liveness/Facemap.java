package com.yoti.api.client.sandbox.docs.resource.liveness;

import com.yoti.api.client.sandbox.docs.resource.SandboxMedia;
import org.apache.http.entity.ContentType;

public class Facemap implements SandboxMedia {

    private final byte[] binaryContent;
    private final ContentType contentType;

    public Facemap(byte[] binaryContent, ContentType contentType) {
        this.binaryContent = binaryContent;
        this.contentType = contentType;
    }

    public byte[] getBinaryContent() {
        return binaryContent.clone();
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

}
