package com.yoti.api.client.sandbox.profile.request.share.thirdparty;

import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SandboxDefinition {

    @JsonProperty("name")
    private String name;

    public SandboxDefinition(String name) {
        notNullOrEmpty(name, "name");
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
