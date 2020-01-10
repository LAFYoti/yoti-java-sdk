package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

public class SandboxFaceMatchCheckReportBuilder extends SandboxCheckReportBuilder<SandboxFaceMatchCheckReportBuilder> {

    @Override
    protected SandboxFaceMatchCheckReportBuilder self() {
        return this;
    }

    @Override
    public SandboxFaceMatchCheckReport build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");

        return new SandboxFaceMatchCheckReport(recommendation, breakdown);
    }

}
