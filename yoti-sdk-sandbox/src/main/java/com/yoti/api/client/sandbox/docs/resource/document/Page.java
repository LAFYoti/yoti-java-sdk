package com.yoti.api.client.sandbox.docs.resource.document;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import com.yoti.api.client.sandbox.docs.resource.SandboxMedia;
import org.apache.http.entity.ContentType;

public class Page implements SandboxMedia {

    private final PageDescriptor descriptor;
    private final ContentType contentType;
    private final byte[] binaryContent;

    Page(PageDescriptor descriptor, ContentType contentType, byte[] binaryContent) {
        this.descriptor = descriptor;
        this.contentType = contentType;
        this.binaryContent = binaryContent;
    }

    public PageDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public byte[] getBinaryContent() {
        return binaryContent.clone();
    }

    public static class Builder {

        private PageDescriptor pageDescriptor;
        private ContentType contentType;
        private byte[] binaryContent;

        public Builder withDescriptor(PageDescriptor pageDescriptor) {
            this.pageDescriptor = pageDescriptor;
            return this;
        }

        public Builder withContentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder withBinaryContent(byte[] binaryContent) {
            this.binaryContent = binaryContent;
            return this;
        }

        public Page build() {
            notNull(pageDescriptor, "pageDescriptor");
            notNull(contentType, "contentType");
            notNull(binaryContent, "binaryContent");

            return new Page(pageDescriptor, contentType, binaryContent);
        }

    }

}
