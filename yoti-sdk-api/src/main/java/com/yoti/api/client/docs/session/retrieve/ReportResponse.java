package com.yoti.api.client.docs.session.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReportResponse {

    @JsonProperty("recommendation")
    private RecommendationResponse recommendation;

    @JsonProperty("breakdown")
    private List<BreakdownResponse> breakdown;

    @JsonProperty("document_fields")
    private Map<String, Object> documentFields;

    public ReportResponse() {
    }

    public ReportResponse(RecommendationResponse recommendation, List<BreakdownResponse> breakdown, Map<String, Object> documentFields) {
        this.recommendation = recommendation;
        this.breakdown = breakdown;
        this.documentFields = documentFields;
    }

    public RecommendationResponse getRecommendation() {
        return recommendation;
    }

    public List<BreakdownResponse> getBreakdown() {
        return breakdown;
    }

    public Map<String, Object> getDocumentFields() {
        return documentFields;
    }

}
