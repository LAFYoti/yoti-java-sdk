package com.yoti.api.client.sandbox.docs.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class SandboxTaskResult {

    @JsonProperty("document_fields")
    private final Map<String, Object> documentFields;

    SandboxTaskResult(Map<String, Object> documentFields) {
        this.documentFields = documentFields;
    }

    public Map<String, Object> getDocumentFields() {
        return documentFields;
    }

}
