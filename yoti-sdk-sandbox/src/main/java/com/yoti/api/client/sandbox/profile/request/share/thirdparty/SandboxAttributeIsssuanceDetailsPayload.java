package com.yoti.api.client.sandbox.profile.request.share.thirdparty;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;
import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

class SandboxAttributeIsssuanceDetailsPayload {

    @JsonProperty("issuance_token")
    private String issuanceToken;

    @JsonProperty("issuing_attributes")
    private SandboxIssuingAttributes issuingAttributes;

    SandboxAttributeIsssuanceDetailsPayload(String issuanceToken, SandboxIssuingAttributes issuingAttributes) {
        notNullOrEmpty(issuanceToken, "issuanceToken");
        notNull(issuingAttributes, "issuingAttributes");

        this.issuanceToken = issuanceToken;
        this.issuingAttributes = issuingAttributes;
    }

    public String getIssuanceToken() {
        return issuanceToken;
    }

    public SandboxIssuingAttributes getIssuingAttributes() {
        return issuingAttributes;
    }

}
