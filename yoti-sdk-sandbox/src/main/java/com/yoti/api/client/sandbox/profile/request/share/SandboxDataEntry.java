package com.yoti.api.client.sandbox.profile.request.share;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SandboxDataEntry<T> {

    @JsonProperty("type")
    public abstract String getType();

    @JsonProperty("value")
    public abstract T getValue();

}
