package com.yoti.api.client.sandbox.docs.check;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.List;

public class SandboxFaceMatchCheck extends SandboxCheck {

    SandboxFaceMatchCheck(RecommendationResponse recommendation, List<BreakdownResponse> breakdown) {
        super(recommendation, breakdown);
    }

}
