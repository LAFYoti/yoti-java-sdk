package com.yoti.api.client.docs.session.create.check;

public interface RequestedLivenessCheck<T extends RequestedLivenessConfig> extends RequestedCheck<RequestedLivenessConfig> {

    @Override
    RequestedLivenessConfig getConfig();

}
