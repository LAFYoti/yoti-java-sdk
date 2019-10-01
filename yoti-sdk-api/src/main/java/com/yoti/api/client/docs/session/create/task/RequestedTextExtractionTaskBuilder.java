package com.yoti.api.client.docs.session.create.task;

public abstract class RequestedTextExtractionTaskBuilder {

    public abstract RequestedTextExtractionTaskBuilder withManualFallbackAlways();

    public abstract RequestedTextExtractionTaskBuilder withManualFallbackFallback();

    public abstract RequestedTextExtractionTaskBuilder withManualFallbackNever();

    public abstract RequestedTextExtractionTask build();

}
