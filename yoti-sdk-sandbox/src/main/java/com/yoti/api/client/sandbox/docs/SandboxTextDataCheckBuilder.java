package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import java.util.HashMap;
import java.util.Map;

public class SandboxTextDataCheckBuilder extends SandboxCheckBuilder<SandboxTextDataCheckBuilder> {

    private Map<String, Object> documentFields = new HashMap<>();

    public SandboxTextDataCheckBuilder withDocumentField(String key, Object value) {
        this.documentFields.put(key, value);
        return this;
    }

    public SandboxTextDataCheckBuilder withDocumentFields(Map<String, Object> documentFields) {
        this.documentFields = documentFields;
        return this;
    }

    @Override
    protected SandboxTextDataCheckBuilder self() {
        return this;
    }

    @Override
    public SandboxTextDataCheck build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");
        notNull(documentFields, "documentFields");

        return new SandboxTextDataCheck(recommendation, breakdown, documentFields);
    }

}
