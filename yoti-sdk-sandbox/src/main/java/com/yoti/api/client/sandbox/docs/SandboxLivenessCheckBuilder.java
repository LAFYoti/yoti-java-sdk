package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

public class SandboxLivenessCheckBuilder extends SandboxCheckBuilder<SandboxLivenessCheckBuilder> {

    @Override
    protected SandboxLivenessCheckBuilder self() {
        return this;
    }

    @Override
    public SandboxLivenessCheck build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");

        return new SandboxLivenessCheck(recommendation, breakdown);
    }

}
