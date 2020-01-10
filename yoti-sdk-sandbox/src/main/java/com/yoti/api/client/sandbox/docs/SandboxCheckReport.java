package com.yoti.api.client.sandbox.docs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
abstract class SandboxCheckReport {

    @JsonProperty("recommendation")
    private RecommendationResponse recommendation;

    @JsonProperty("breakdown")
    private List<BreakdownResponse> breakdown;

    SandboxCheckReport(RecommendationResponse recommendation, List<BreakdownResponse> breakdown) {
        this.recommendation = recommendation;
        this.breakdown = breakdown;
    }

    public RecommendationResponse getRecommendation() {
        return recommendation;
    }

    public List<BreakdownResponse> getBreakdown() {
        return breakdown;
    }

}
