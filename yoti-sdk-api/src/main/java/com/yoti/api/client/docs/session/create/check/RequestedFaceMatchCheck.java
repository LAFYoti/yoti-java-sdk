package com.yoti.api.client.docs.session.create.check;

public interface RequestedFaceMatchCheck<T extends RequestedFaceMatchConfig> extends RequestedCheck<T> {

    @Override
    T getConfig();

}
