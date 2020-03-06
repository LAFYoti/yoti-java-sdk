package com.yoti.api.client.sandbox.profile.request.share.thirdparty;

import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.yoti.api.client.sandbox.profile.request.share.SandboxDataEntry;

public class SandboxAttributeIssuanceDetails extends SandboxDataEntry<SandboxAttributeIsssuanceDetailsPayload> {

    private final SandboxAttributeIsssuanceDetailsPayload value;

    private SandboxAttributeIssuanceDetails(SandboxAttributeIsssuanceDetailsPayload value) {
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String issuanceToken;
        private String expiryDate;
        private List<SandboxDefinition> definitions = new ArrayList<>();

        private Builder() {
        }

        public Builder withIssuanceToken(String issuanceToken) {
            notNullOrEmpty(issuanceToken, "issuanceToken");

            this.issuanceToken = issuanceToken;
            return this;
        }

        public Builder withExpiryDate(String expiryDate) {
            notNullOrEmpty(expiryDate, "expiryDate");

            this.expiryDate = expiryDate;
            return this;
        }

        public Builder withDefinition(String definition) {
            notNullOrEmpty(definition, "definition");

            this.definitions.add(new SandboxDefinition(definition));
            return this;
        }

        public SandboxAttributeIssuanceDetails build() {
            SandboxIssuingAttributes issuingAttributes = new SandboxIssuingAttributes(expiryDate, definitions);
            SandboxAttributeIsssuanceDetailsPayload payload = new SandboxAttributeIsssuanceDetailsPayload(issuanceToken, issuingAttributes);

            return new SandboxAttributeIssuanceDetails(payload);
        }
    }
}
