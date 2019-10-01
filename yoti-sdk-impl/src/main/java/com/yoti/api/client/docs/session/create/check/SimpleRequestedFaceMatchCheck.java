package com.yoti.api.client.docs.session.create.check;

import com.yoti.api.client.docs.Constants;

public class SimpleRequestedFaceMatchCheck
        extends SimpleRequestedCheck<RequestedFaceMatchConfig>
        implements RequestedFaceMatchCheck<RequestedFaceMatchConfig> {

    private final RequestedFaceMatchConfig config;

    public SimpleRequestedFaceMatchCheck(RequestedFaceMatchConfig config) {
        this.config = config;
    }

    @Override
    public String getType() {
        return Constants.ID_DOCUMENT_FACE_MATCH;
    }

    @Override
    public RequestedFaceMatchConfig getConfig() {
        return config;
    }

}
