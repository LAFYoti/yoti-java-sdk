package com.yoti.api.client.sandbox.docs.request.check;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SandboxTextDataCheckResult extends SandboxCheckResult {

    @JsonProperty("document_fields")
    private Map<String, Object> documentFields;

    SandboxTextDataCheckResult(SandboxCheckReport report, Map<String, Object> documentFields) {
        super(report);
        this.documentFields = documentFields;
    }

    public Map<String, Object> getDocumentFields() {
        return documentFields;
    }

}
