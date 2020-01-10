package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

public class SandboxDocumentAuthenticityCheckReportBuilder extends SandboxCheckReportBuilder<SandboxDocumentAuthenticityCheckReportBuilder> {

    @Override
    protected SandboxDocumentAuthenticityCheckReportBuilder self() {
        return this;
    }

    @Override
    public SandboxDocumentAuthenticityCheckReport build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");

        return new SandboxDocumentAuthenticityCheckReport(recommendation, breakdown);
    }

}
