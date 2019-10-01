package com.yoti.api.client.docs.session.create.check;

import java.util.ServiceLoader;

public abstract class RequestedCheckBuilderFactory {

    public static RequestedCheckBuilderFactory newInstance() {
        ServiceLoader<RequestedCheckBuilderFactory> requestedCheckBuilderFactoryServiceLoader = ServiceLoader.load(RequestedCheckBuilderFactory.class);
        if (!requestedCheckBuilderFactoryServiceLoader.iterator().hasNext()) {
            throw new IllegalStateException("Cannot find any implementation of " + RequestedCheckBuilderFactory.class.getSimpleName());
        }
        RequestedCheckBuilderFactory constraintBuilderFactory = requestedCheckBuilderFactoryServiceLoader.iterator().next();
        return constraintBuilderFactory.createRequestedCheckBuilderFactory();
    }

    protected abstract RequestedCheckBuilderFactory createRequestedCheckBuilderFactory();

    public abstract RequestedDocumentAuthenticityCheckBuilder createAuthenticityCheckBuilder();

    public abstract RequestedLivenessCheckBuilder createLivenessCheckBuilder();

    public abstract RequestedFaceMatchCheckBuilder createFaceMatchCheckBuilder();

}
