package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.docs.Constants.ID_DOCUMENT_AUTHENTICITY;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;
import com.yoti.api.client.docs.session.retrieve.ReportResponse;
import org.junit.Test;

public class ResponseConfigBuilderTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void shouldBuildTaskResult() throws Exception {
        TaskResult taskResult = new TaskResultBuilder()
                .withDocumentField("full_name", "Alex Burt")
                .withDocumentField("age", 13)
                .build();

        RecommendationResponse recommendationResponse = new RecommendationResponseBuilder()
                .withValue("APPROVE")
                .build();

        BreakdownResponse breakdownResponse = new BreakdownResponseBuilder()
                .withResult("PASS")
                .withSubCheck("security_features")
                .build();

        ReportResponse documentAuthenticityReport = new ReportResponseBuilder()
                .withRecommendation(recommendationResponse)
                .withBreakdown(breakdownResponse)
                .build();

        CheckReport checkReport = new CheckReportBuilder()
                .withDocumentAuthenticityReport(documentAuthenticityReport)
                .build();

        ResponseConfig responseConfig = new ResponseConfigBuilder()
                .withCheckReport(checkReport)
                .build();

        String response = OBJECT_MAPPER.writeValueAsString(responseConfig);

        String s = "s";
    }


}
