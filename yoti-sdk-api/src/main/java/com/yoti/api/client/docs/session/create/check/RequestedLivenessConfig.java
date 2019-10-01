package com.yoti.api.client.docs.session.create.check;

public interface RequestedLivenessConfig extends RequestedCheckConfig {

    int getMaxRetries();

    String getLivenessType();

}
