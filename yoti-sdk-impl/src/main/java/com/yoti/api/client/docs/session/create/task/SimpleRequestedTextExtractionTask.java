package com.yoti.api.client.docs.session.create.task;

import com.yoti.api.client.docs.Constants;

public class SimpleRequestedTextExtractionTask
        extends SimpleRequestedTask<RequestedTextExtractionTaskConfig>
        implements RequestedTextExtractionTask<RequestedTextExtractionTaskConfig> {

    private final RequestedTextExtractionTaskConfig config;

    public SimpleRequestedTextExtractionTask(RequestedTextExtractionTaskConfig config) {
        this.config = config;
    }

    @Override
    public String getType() {
        return Constants.ID_DOCUMENT_TEXT_DATA_EXTRACTION;
    }

    @Override
    public RequestedTextExtractionTaskConfig getConfig() {
        return config;
    }

}
