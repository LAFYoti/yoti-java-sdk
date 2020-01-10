package com.yoti.api.client.sandbox.docs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SandboxTextDataCheckReport extends SandboxCheckReport {

    @JsonProperty("document_fields")
    private Map<String, Object> documentFields;

    SandboxTextDataCheckReport(RecommendationResponse recommendation, List<BreakdownResponse> breakdown, Map<String, Object> documentFields) {
        super(recommendation, breakdown);
        this.documentFields = documentFields;
    }

    public Map<String, Object> getDocumentFields() {
        return documentFields;
    }
}
