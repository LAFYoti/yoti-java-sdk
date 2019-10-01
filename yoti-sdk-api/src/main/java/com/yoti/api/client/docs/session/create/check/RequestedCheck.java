package com.yoti.api.client.docs.session.create.check;

public interface RequestedCheck<T extends RequestedCheckConfig> {

    String getType();

    T getConfig();

}
