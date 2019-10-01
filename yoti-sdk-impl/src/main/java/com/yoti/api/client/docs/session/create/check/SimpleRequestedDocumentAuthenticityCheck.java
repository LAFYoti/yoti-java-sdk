package com.yoti.api.client.docs.session.create.check;

import com.yoti.api.client.docs.Constants;

public class SimpleRequestedDocumentAuthenticityCheck
        extends SimpleRequestedCheck<RequestedDocumentAuthenticityConfig>
        implements RequestedDocumentAuthenticityCheck<RequestedDocumentAuthenticityConfig> {

    private final RequestedDocumentAuthenticityConfig config;

    // FIXME: PUBLIC constructors on these builders?
    public SimpleRequestedDocumentAuthenticityCheck(RequestedDocumentAuthenticityConfig config) {
        this.config = config;
    }

    @Override
    public String getType() {
        return Constants.ID_DOCUMENT_AUTHENTICITY;
    }

    @Override
    public RequestedDocumentAuthenticityConfig getConfig() {
        return config;
    }

}
