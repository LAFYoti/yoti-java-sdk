package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

public class RecommendationResponseBuilder {

    private String value;

    private String reason;

    private String recoverySuggestion;

    public static RecommendationResponse approvedRecommendation() {
        return new RecommendationResponse("APPROVE", null, null);
    }

    public static RecommendationResponse notAvailableRecommendation() {
        return new RecommendationResponse("NOT_AVAILABLE", "PICTURE_TOO_DARK", "BETTER_LIGHTING");
    }

    public static RecommendationResponse rejectedRecommendation() {
        return new RecommendationResponse("REJECT", "NOT_GENUINE", null);
    }

    public RecommendationResponseBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public RecommendationResponseBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public RecommendationResponseBuilder withRecoverySuggestion(String recoverySuggestion) {
        this.recoverySuggestion = recoverySuggestion;
        return this;
    }

    public RecommendationResponse build() {
        notNull(value, "value");

        return new RecommendationResponse(value, reason, recoverySuggestion);
    }

}
