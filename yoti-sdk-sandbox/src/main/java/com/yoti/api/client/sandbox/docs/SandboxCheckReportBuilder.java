package com.yoti.api.client.sandbox.docs;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.ArrayList;
import java.util.List;

abstract class SandboxCheckReportBuilder<T extends SandboxCheckReportBuilder<T>> {

    protected RecommendationResponse recommendation;
    protected List<BreakdownResponse> breakdown = new ArrayList<>();

    public T withRecommendation(RecommendationResponse recommendation) {
        this.recommendation = recommendation;
        return self();
    }

    public T withBreakdown(BreakdownResponse breakdown) {
        this.breakdown.add(breakdown);
        return self();
    }

    public T withBreakdownList(List<BreakdownResponse> breakdownList) {
        this.breakdown = breakdownList;
        return self();
    }

    protected abstract T self();

    public abstract SandboxCheckReport build();

}
