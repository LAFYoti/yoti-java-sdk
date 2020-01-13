package com.yoti.api.client.sandbox.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;
import com.yoti.api.client.sandbox.docs.check.SandboxDocumentAuthenticityCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxDocumentAuthenticityCheckBuilder;
import com.yoti.api.client.sandbox.docs.check.SandboxTextDataCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxTextDataCheckBuilder;
import org.junit.Test;

public class SandboxExpectationBuilderTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void shouldBuildTaskResult() throws Exception {
//        TaskResult taskResult = new TaskResultBuilder()
//                .withDocumentField("full_name", "Alex Burt")
//                .withDocumentField("age", 13)
//                .build();

        RecommendationResponse recommendationResponse = new RecommendationResponseBuilder()
                .withValue("APPROVE")
                .build();

        BreakdownResponse breakdownResponse = new BreakdownResponseBuilder()
                .withResult("PASS")
                .withSubCheck("security_features")
                .build();

//        ReportResponse documentAuthenticityReport = new ReportResponseBuilder()
//                .withRecommendation(recommendationResponse)
//                .withBreakdown(breakdownResponse)
//                .build();

        SandboxDocumentAuthenticityCheck sandboxDocumentAuthenticityCheckReport = new SandboxDocumentAuthenticityCheckBuilder()
                .withBreakdown(breakdownResponse)
                .withRecommendation(recommendationResponse)
                .build();

        SandboxTextDataCheck sandboxTextDataCheckReport = new SandboxTextDataCheckBuilder()
                .withBreakdown(breakdownResponse)
                .withRecommendation(recommendationResponse)
                .withDocumentField("someName", "someValue")
                .build();

        SandboxCheckReport sandboxCheckReport = new SandboxCheckReportBuilder()
                .withDocumentAuthenticityReport(sandboxDocumentAuthenticityCheckReport)
                .withTextDataCheckReport(sandboxTextDataCheckReport)
                .build();

        SandboxExpectation sandboxExpectation = new SandboxExpectationBuilder()
                .withCheckReport(sandboxCheckReport)
                .build();

        String response = OBJECT_MAPPER.writeValueAsString(sandboxExpectation);

        String s = "s";
    }


}
