package com.yoti.api.client.docs.session.create.task;

import com.yoti.api.client.docs.Constants;

public class SimpleRequestedTextExtractionTaskBuilder extends RequestedTextExtractionTaskBuilder {

    private String manualFallback;

    @Override
    public RequestedTextExtractionTaskBuilder withManualFallbackAlways() {
        this.manualFallback = Constants.ALWAYS;
        return this;
    }

    @Override
    public RequestedTextExtractionTaskBuilder withManualFallbackFallback() {
        this.manualFallback = Constants.FALLBACK;
        return this;
    }

    @Override
    public RequestedTextExtractionTaskBuilder withManualFallbackNever() {
        this.manualFallback = Constants.NEVER;
        return this;
    }

    @Override
    public RequestedTextExtractionTask build() {
        RequestedTextExtractionTaskConfig config = new SimpleRequestedTextExtractionTaskConfig(manualFallback);
        return new SimpleRequestedTextExtractionTask(config);
    }

}
