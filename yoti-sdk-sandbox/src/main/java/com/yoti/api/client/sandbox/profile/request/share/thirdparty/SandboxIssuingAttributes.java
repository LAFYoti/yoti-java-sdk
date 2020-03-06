package com.yoti.api.client.sandbox.profile.request.share.thirdparty;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;
import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class SandboxIssuingAttributes {

    @JsonProperty("expiry_date")
    private String expiryDate;

    @JsonProperty("definitions")
    private List<SandboxDefinition> definitions;

    SandboxIssuingAttributes(String expiryDate, List<SandboxDefinition> definitions) {
        notNullOrEmpty(expiryDate, "expiryDate");
        notNull(definitions, "definitions");

        this.expiryDate = expiryDate;
        this.definitions = definitions;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public List<SandboxDefinition> getDefinitions() {
        return definitions;
    }
}
