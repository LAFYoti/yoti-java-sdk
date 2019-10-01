package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;
import com.yoti.api.client.docs.session.retrieve.ReportResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportResponseBuilder {

    private RecommendationResponse recommendation;

    private List<BreakdownResponse> breakdown = new ArrayList<>();

    private Map<String, Object> documentFields = new HashMap<>();

    public ReportResponseBuilder withRecommendation(RecommendationResponse recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public ReportResponseBuilder withBreakdown(BreakdownResponse breakdown) {
        this.breakdown.add(breakdown);
        return this;
    }

    public ReportResponseBuilder withBreakdownList(List<BreakdownResponse> breakdownList) {
        this.breakdown = breakdownList;
        return this;
    }

    public ReportResponseBuilder withDocumentField(String key, Object value) {
        this.documentFields.put(key, value);
        return this;
    }

    public ReportResponseBuilder withDocumentFields(Map<String, Object> documentFields) {
        this.documentFields = documentFields;
        return this;
    }

    public ReportResponse build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");

        return new ReportResponse(recommendation, breakdown, documentFields);
    }

}
