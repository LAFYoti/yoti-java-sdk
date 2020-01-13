package com.yoti.api.client.sandbox.docs;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.List;

public class SandboxLivenessCheck extends SandboxCheck {

    SandboxLivenessCheck(RecommendationResponse recommendation, List<BreakdownResponse> breakdown) {
        super(recommendation, breakdown);
    }

}
