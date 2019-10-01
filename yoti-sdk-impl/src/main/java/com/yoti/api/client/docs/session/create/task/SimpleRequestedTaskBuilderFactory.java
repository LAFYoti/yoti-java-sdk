package com.yoti.api.client.docs.session.create.task;

public class SimpleRequestedTaskBuilderFactory extends RequestedTaskBuilderFactory {

    @Override
    protected RequestedTaskBuilderFactory createRequestedTaskBuilderFactory() {
        return new SimpleRequestedTaskBuilderFactory();
    }

    @Override
    public RequestedTextExtractionTaskBuilder createTextExtractionTaskBuilder() {
        return new SimpleRequestedTextExtractionTaskBuilder();
    }

}
