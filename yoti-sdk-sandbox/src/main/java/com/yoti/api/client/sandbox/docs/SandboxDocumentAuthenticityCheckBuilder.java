package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

public class SandboxDocumentAuthenticityCheckBuilder extends SandboxCheckBuilder<SandboxDocumentAuthenticityCheckBuilder> {

    @Override
    protected SandboxDocumentAuthenticityCheckBuilder self() {
        return this;
    }

    @Override
    public SandboxDocumentAuthenticityCheck build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");

        return new SandboxDocumentAuthenticityCheck(recommendation, breakdown);
    }

}
