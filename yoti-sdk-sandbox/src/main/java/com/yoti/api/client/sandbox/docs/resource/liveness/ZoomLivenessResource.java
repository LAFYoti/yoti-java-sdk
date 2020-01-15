package com.yoti.api.client.sandbox.docs.resource.liveness;

import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.sandbox.docs.resource.SandboxPayload;

public class ZoomLivenessResource implements SandboxPayload {

    @JsonProperty("source")
    private String source;

    ZoomLivenessResource(String source) {
        this.source = source;
    }

    public static ZoomLivenessResource validWebZoomLiveness() {
        return new ZoomLivenessResource("WEB");
    }

    public static ZoomLivenessResource validNativeZoomLiveness() {
        return new ZoomLivenessResource("NATIVE");
    }

    public String getSource() {
        return source;
    }

    public static class Builder {

        private String source;

        public Builder withSource(String source) {
            this.source = source;
            return this;
        }

        public ZoomLivenessResource build() {
            notNullOrEmpty(source, "source");

            return new ZoomLivenessResource(source);
        }

    }

}
