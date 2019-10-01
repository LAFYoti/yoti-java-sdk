package com.yoti.api.client.sandbox.docs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;
import com.yoti.api.client.docs.session.retrieve.ReportResponse;
import org.junit.Test;

public class CheckReportBuilderTest {

    private static final int SOME_ASYNC_REPORT_DELAY = 1;

    @Test
    public void shouldBuildWithNoPropertiesSupplied() {
        CheckReport result = new CheckReportBuilder().build();

        assertNotNull(result);
    }

    @Test
    public void shouldBuildWithAllPropertiesSupplied() {
        RecommendationResponse approvedRecommendation = RecommendationResponseBuilder.approvedRecommendation();

        ReportResponse basicReport = new ReportResponseBuilder()
                .withRecommendation(approvedRecommendation)
                .build();

        CheckReport result = new CheckReportBuilder().withTextDataCheckReport(basicReport)
                .withDocumentAuthenticityReport(basicReport)
                .withLivenessReport(basicReport)
                .withDocumentFaceMatchReport(basicReport)
                .withAsyncReportDelay(SOME_ASYNC_REPORT_DELAY)
                .build();

        assertNotNull(result);
        assertNotNull(result.getTextDataCheckReport());
        assertNotNull(result.getDocumentAuthenticityReport());
        assertNotNull(result.getDocumentFaceMatchReport());
        assertNotNull(result.getLivenessReport());
        assertThat(result.getAsyncReportDelay(), is(SOME_ASYNC_REPORT_DELAY));
    }

}
