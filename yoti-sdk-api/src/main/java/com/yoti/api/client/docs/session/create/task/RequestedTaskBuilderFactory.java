package com.yoti.api.client.docs.session.create.task;

import java.util.ServiceLoader;

public abstract class RequestedTaskBuilderFactory {

    public static RequestedTaskBuilderFactory newInstance() {
        ServiceLoader<RequestedTaskBuilderFactory> requestedTaskBuilderFactoryServiceLoader = ServiceLoader.load(RequestedTaskBuilderFactory.class);
        if (!requestedTaskBuilderFactoryServiceLoader.iterator().hasNext()) {
            throw new IllegalStateException("Cannot find any implementation of " + RequestedTaskBuilderFactory.class.getSimpleName());
        }
        RequestedTaskBuilderFactory requestedTaskBuilderFactory = requestedTaskBuilderFactoryServiceLoader.iterator().next();
        return requestedTaskBuilderFactory.createRequestedTaskBuilderFactory();
    }

    protected abstract RequestedTaskBuilderFactory createRequestedTaskBuilderFactory();

    public abstract RequestedTextExtractionTaskBuilder createTextExtractionTaskBuilder();

}
