package com.yoti.api.client.docs.session.create.task;

public interface RequestedTask<T extends RequestedTaskConfig> {

    String getType();

    T getConfig();

}
