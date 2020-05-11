package com.yoti.api.client.sandbox.docs.request.check;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import com.yoti.api.client.sandbox.docs.request.SandboxDocumentFilter;

public class SandboxDocumentAuthenticityCheck extends SandboxDocumentCheck {

    SandboxDocumentAuthenticityCheck(SandboxCheckResult result, SandboxDocumentFilter documentFilter) {
        super(result, documentFilter);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link SandboxDocumentAuthenticityCheck}
     */
    public static class Builder extends SandboxDocumentCheckBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SandboxDocumentAuthenticityCheck build() {
            notNull(recommendation, "recommendation");
            notNull(breakdown, "breakdown");

            SandboxCheckReport report = new SandboxCheckReport(recommendation, breakdown);
            SandboxCheckResult result = new SandboxCheckResult(report);

            return new SandboxDocumentAuthenticityCheck(result, documentFilter);
        }

    }
}
