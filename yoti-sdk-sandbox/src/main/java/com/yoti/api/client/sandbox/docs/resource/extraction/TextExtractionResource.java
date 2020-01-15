package com.yoti.api.client.sandbox.docs.resource.extraction;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.sandbox.docs.resource.SandboxPayload;

import java.util.HashMap;
import java.util.Map;

public class TextExtractionResource implements SandboxPayload {

    @JsonProperty("client_extraction_outcome")
    private final String clientExtractionOutcome;

    @JsonProperty("document_fields")
    private final Map<String, Object> documentFields;

    TextExtractionResource(String clientExtractionOutcome, Map<String, Object> documentFields) {
        this.clientExtractionOutcome = clientExtractionOutcome;
        this.documentFields = documentFields;
    }

    public String getClientExtractionOutcome() {
        return clientExtractionOutcome;
    }

    public Map<String, Object> getDocumentFields() {
        return documentFields;
    }

    public static class Builder {

        private String clientExtractionOutcome;
        private Map<String, Object> documentFields = new HashMap<>();

        public Builder withClientExtractionOutcome(String clientExtractionOutcome) {
            this.clientExtractionOutcome = clientExtractionOutcome;
            return this;
        }

        public Builder withDocumentField(String key, Object value) {
            this.documentFields.put(key, value);
            return this;
        }

        public Builder withDocumentFields(Map<String, Object> documentFields) {
            this.documentFields = documentFields;
            return this;
        }

        public TextExtractionResource build() {
            notNull(clientExtractionOutcome, "clientExtractionOutcome");
            notNull(documentFields, "documentFields");

            return new TextExtractionResource(clientExtractionOutcome, documentFields);
        }

    }

}
