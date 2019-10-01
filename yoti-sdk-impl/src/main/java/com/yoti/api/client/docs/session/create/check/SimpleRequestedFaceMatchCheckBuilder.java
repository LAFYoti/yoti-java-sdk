package com.yoti.api.client.docs.session.create.check;

import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.yoti.api.client.docs.Constants;

public class SimpleRequestedFaceMatchCheckBuilder extends RequestedFaceMatchCheckBuilder {

    private String manualCheck;

    @Override
    public SimpleRequestedFaceMatchCheckBuilder withManualCheckAlways() {
        this.manualCheck = Constants.ALWAYS;
        return this;
    }

    @Override
    public SimpleRequestedFaceMatchCheckBuilder withManualCheckFallback() {
        this.manualCheck = Constants.FALLBACK;
        return this;
    }

    @Override
    public SimpleRequestedFaceMatchCheckBuilder withManualCheckNever() {
        this.manualCheck = Constants.NEVER;
        return this;
    }

    @Override
    public RequestedFaceMatchCheck build() {
        notNullOrEmpty(manualCheck, "manualCheck");

        RequestedFaceMatchConfig config = new SimpleRequestedFaceMatchConfig(manualCheck);
        return new SimpleRequestedFaceMatchCheck(config);
    }

}
