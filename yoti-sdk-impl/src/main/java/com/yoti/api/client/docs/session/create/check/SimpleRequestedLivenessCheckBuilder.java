package com.yoti.api.client.docs.session.create.check;

import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.yoti.api.client.docs.Constants;

public class SimpleRequestedLivenessCheckBuilder extends RequestedLivenessCheckBuilder {

    private String livenessType;
    private int maxRetries = 1;

    @Override
    public RequestedLivenessCheckBuilder forZoomLiveness() {
        this.livenessType = Constants.ZOOM;
        return this;
    }

    // FIXME: Should we allow a custom liveness type?
    @Override
    public RequestedLivenessCheckBuilder forLivenessType(String livenessType) {
        this.livenessType = livenessType;
        return this;
    }

    @Override
    public RequestedLivenessCheckBuilder withMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    @Override
    public RequestedLivenessCheck build() {
        notNullOrEmpty(livenessType, "livenessType");

        RequestedLivenessConfig config = new SimpleRequestedLivenessConfig(maxRetries, livenessType);
        return new SimpleRequestedLivenessCheck(config);
    }

}
