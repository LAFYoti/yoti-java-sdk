package com.yoti.api.client.sandbox.docs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class TaskResult {

    @JsonProperty("document_fields")
    private final Map<String, Object> documentFields;

    TaskResult(Map<String, Object> documentFields) {
        this.documentFields = documentFields;
    }

    public Map<String, Object> getDocumentFields() {
        return documentFields;
    }

}
