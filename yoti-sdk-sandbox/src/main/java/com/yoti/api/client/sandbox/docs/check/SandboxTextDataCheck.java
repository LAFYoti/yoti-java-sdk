package com.yoti.api.client.sandbox.docs.check;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SandboxTextDataCheck extends SandboxCheck {

    @JsonProperty("document_fields")
    private Map<String, Object> documentFields;

    SandboxTextDataCheck(RecommendationResponse recommendation, List<BreakdownResponse> breakdown, Map<String, Object> documentFields) {
        super(recommendation, breakdown);
        this.documentFields = documentFields;
    }

    public Map<String, Object> getDocumentFields() {
        return documentFields;
    }
}
