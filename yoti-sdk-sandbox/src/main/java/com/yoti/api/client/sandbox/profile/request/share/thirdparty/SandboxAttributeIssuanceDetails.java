package com.yoti.api.client.sandbox.profile.request.share.thirdparty;

import com.yoti.api.client.sandbox.profile.request.share.SandboxDataEntry;

public class SandboxAttributeIssuanceDetails extends SandboxDataEntry<SandboxAttributeIsssuanceDetailsPayload> {

    private final SandboxAttributeIsssuanceDetailsPayload value;

    public SandboxAttributeIssuanceDetails(SandboxAttributeIsssuanceDetailsPayload value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return "THIRD_PARTY_ATTRIBUTE";
    }

    @Override
    public SandboxAttributeIsssuanceDetailsPayload getValue() {
        return value;
    }

}
