package com.yoti.api.client.sandbox.docs.check;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.List;

public class SandboxDocumentAuthenticityCheck extends SandboxCheck {

    SandboxDocumentAuthenticityCheck(RecommendationResponse recommendation, List<BreakdownResponse> breakdown) {
        super(recommendation, breakdown);
    }

}
