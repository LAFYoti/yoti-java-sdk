package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import java.util.HashMap;
import java.util.Map;

public class SandboxTextDataCheckReportBuilder extends SandboxCheckReportBuilder<SandboxTextDataCheckReportBuilder> {

    private Map<String, Object> documentFields = new HashMap<>();

    public SandboxTextDataCheckReportBuilder withDocumentField(String key, Object value) {
        this.documentFields.put(key, value);
        return this;
    }

    public SandboxTextDataCheckReportBuilder withDocumentFields(Map<String, Object> documentFields) {
        this.documentFields = documentFields;
        return this;
    }

    @Override
    protected SandboxTextDataCheckReportBuilder self() {
        return this;
    }

    @Override
    public SandboxTextDataCheckReport build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");
        notNull(documentFields, "documentFields");

        return new SandboxTextDataCheckReport(recommendation, breakdown, documentFields);
    }

}
