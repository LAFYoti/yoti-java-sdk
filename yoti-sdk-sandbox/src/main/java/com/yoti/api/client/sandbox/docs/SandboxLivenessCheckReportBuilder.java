package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

public class SandboxLivenessCheckReportBuilder extends SandboxCheckReportBuilder<SandboxLivenessCheckReportBuilder> {

    @Override
    protected SandboxLivenessCheckReportBuilder self() {
        return this;
    }

    @Override
    public SandboxLivenessCheckReport build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");

        return new SandboxLivenessCheckReport(recommendation, breakdown);
    }

}
