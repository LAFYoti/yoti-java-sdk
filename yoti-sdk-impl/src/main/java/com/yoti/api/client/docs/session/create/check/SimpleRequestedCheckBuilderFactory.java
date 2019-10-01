package com.yoti.api.client.docs.session.create.check;

public class SimpleRequestedCheckBuilderFactory extends RequestedCheckBuilderFactory {

    @Override
    protected RequestedCheckBuilderFactory createRequestedCheckBuilderFactory() {
        return new SimpleRequestedCheckBuilderFactory();
    }

    @Override
    public RequestedDocumentAuthenticityCheckBuilder createAuthenticityCheckBuilder() {
        return new SimpleRequestedDocumentAuthenticityCheckBuilder();
    }

    @Override
    public RequestedLivenessCheckBuilder createLivenessCheckBuilder() {
        return new SimpleRequestedLivenessCheckBuilder();
    }

    @Override
    public RequestedFaceMatchCheckBuilder createFaceMatchCheckBuilder() {
        return new SimpleRequestedFaceMatchCheckBuilder();
    }

}
