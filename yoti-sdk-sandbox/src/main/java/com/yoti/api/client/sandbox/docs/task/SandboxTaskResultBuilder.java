package com.yoti.api.client.sandbox.docs.task;

import java.util.HashMap;
import java.util.Map;

public class SandboxTaskResultBuilder {

    private Map<String, Object> documentFields = new HashMap<>();

    public SandboxTaskResultBuilder withDocumentField(String key, Object value) {
        this.documentFields.put(key, value);
        return this;
    }

    public SandboxTaskResultBuilder withDocumentFields(Map<String, Object> documentFields) {
        this.documentFields = documentFields;
        return this;
    }

    public SandboxTaskResult build() {
        return new SandboxTaskResult(documentFields);
    }

}
