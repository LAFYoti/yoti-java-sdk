package com.yoti.api.client.sandbox.docs.resource.document;

import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.sandbox.docs.resource.SandboxPayload;

public class DocumentResource implements SandboxPayload {

    @JsonProperty("document_type")
    private final String documentType;

    @JsonProperty("issuing_country")
    private final String issuingCountry;

    public DocumentResource(String documentType, String issuingCountry) {
        this.documentType = documentType;
        this.issuingCountry = issuingCountry;
    }

    public static DocumentResource validArePassport() {
        return new DocumentResource.Builder()
                .withDocumentType("PASSPORT")
                .withIssuingCountry("ARE")
                .build();
    }

    public static DocumentResource validAreDrivingLicense() {
        return new DocumentResource.Builder()
                .withDocumentType("DRIVING_LICENCE")
                .withIssuingCountry("ARE")
                .build();
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public static class Builder {

        private String documentType;
        private String issuingCountry;

        public Builder withDocumentType(String documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder withIssuingCountry(String issuingCountry) {
            this.issuingCountry = issuingCountry;
            return this;
        }

        public DocumentResource build() {
            notNullOrEmpty(documentType, "documentType");
            notNullOrEmpty(issuingCountry, "issuingCountry");

            return new DocumentResource(documentType, issuingCountry);
        }

    }

}
