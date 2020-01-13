package com.yoti.api.client.sandbox.docs.check;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

public class SandboxFaceMatchCheckBuilder extends SandboxCheckBuilder<SandboxFaceMatchCheckBuilder> {

    @Override
    protected SandboxFaceMatchCheckBuilder self() {
        return this;
    }

    @Override
    public SandboxFaceMatchCheck build() {
        notNull(recommendation, "recommendation");
        notNull(breakdown, "breakdown");

        return new SandboxFaceMatchCheck(recommendation, breakdown);
    }

}
