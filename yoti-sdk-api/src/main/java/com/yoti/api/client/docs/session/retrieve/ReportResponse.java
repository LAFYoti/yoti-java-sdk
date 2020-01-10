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

    public ReportResponse() {
    }

    public ReportResponse(RecommendationResponse recommendation, List<BreakdownResponse> breakdown) {
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
