package com.yoti.api.client.docs.session.create.task;

public interface RequestedTextExtractionTask<T extends RequestedTextExtractionTaskConfig> extends RequestedTask<T> {

    @Override
    T getConfig();

}
