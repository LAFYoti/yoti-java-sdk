package com.yoti.api.client.docs.session.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendationResponse {

    @JsonProperty("value")
    private String value;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("recovery_suggestion")
    private String recoverySuggestion;

    public RecommendationResponse() {}

    public RecommendationResponse(String value, String reason, String recoverySuggestion) {
        this.value = value;
        this.reason = reason;
        this.recoverySuggestion = recoverySuggestion;
    }

    public String getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    public String getRecoverySuggestion() {
        return recoverySuggestion;
    }

}
