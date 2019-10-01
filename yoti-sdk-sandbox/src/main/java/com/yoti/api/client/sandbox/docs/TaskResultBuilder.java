package com.yoti.api.client.sandbox.docs;

import java.util.HashMap;
import java.util.Map;

public class TaskResultBuilder {

    private Map<String, Object> documentFields = new HashMap<>();

    public TaskResultBuilder withDocumentField(String key, Object value) {
        this.documentFields.put(key, value);
        return this;
    }

    public TaskResultBuilder withDocumentFields(Map<String, Object> documentFields) {
        this.documentFields = documentFields;
        return this;
    }

    public TaskResult build() {
        return new TaskResult(documentFields);
    }

}
