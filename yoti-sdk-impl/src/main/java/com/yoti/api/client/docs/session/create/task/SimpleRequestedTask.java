package com.yoti.api.client.docs.session.create.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.docs.session.create.task.RequestedTask;
import com.yoti.api.client.docs.session.create.task.RequestedTaskConfig;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
//@JsonSubTypes(@JsonSubTypes.Type(value = SimpleRequestedTextExtractionTask.class, name = Constants.ID_DOCUMENT_TEXT_DATA_EXTRACTION))
public abstract class SimpleRequestedTask<T extends RequestedTaskConfig> implements RequestedTask<T> {

    @JsonProperty("type")
    @Override
    public abstract String getType();

    @JsonProperty("config")
    @Override
    public abstract T getConfig();

}
