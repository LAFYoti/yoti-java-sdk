package com.yoti.api.client.sandbox.docs;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;

import java.util.List;

public class SandboxFaceMatchCheckReport extends SandboxCheckReport {

    SandboxFaceMatchCheckReport(RecommendationResponse recommendation, List<BreakdownResponse> breakdown) {
        super(recommendation, breakdown);
    }

}
