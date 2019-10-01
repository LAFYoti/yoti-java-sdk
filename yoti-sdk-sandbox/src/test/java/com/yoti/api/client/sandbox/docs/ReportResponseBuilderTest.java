package com.yoti.api.client.sandbox.docs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;
import com.yoti.api.client.docs.session.retrieve.ReportResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ReportResponseBuilderTest {

    private static final RecommendationResponse SOME_APPROVED_RECOMMENDATION = RecommendationResponseBuilder.approvedRecommendation();

    @Test
    public void shouldBuildWithRecommendationOnly() {
        ReportResponse result = new ReportResponseBuilder().withRecommendation(SOME_APPROVED_RECOMMENDATION)
                .build();

        assertNotNull(result);
        assertNotNull(result.getRecommendation());
        assertThat(result.getBreakdown(), hasSize(0));
        assertThat(result.getDocumentFields().entrySet(), empty());
    }

    @Test
    public void shouldBuildWithSingleBreakdownsAddedMultipleTimes() {
        BreakdownResponse firstBreakdown = new BreakdownResponseBuilder().withResult("PASS")
                .withSubCheck("security_features")
                .build();
        BreakdownResponse secondBreakdown = new BreakdownResponseBuilder().withResult("FAIL")
                .withSubCheck("data_positioning")
                .build();

        ReportResponse result = new ReportResponseBuilder().withRecommendation(SOME_APPROVED_RECOMMENDATION)
                .withBreakdown(firstBreakdown)
                .withBreakdown(secondBreakdown)
                .build();

        assertThat(result.getBreakdown(), hasSize(2));
        assertThat(result.getBreakdown().get(0).getResult(), is("PASS"));
        assertThat(result.getBreakdown().get(0).getSubCheck(), is("security_features"));
        assertThat(result.getBreakdown().get(1).getResult(), is("FAIL"));
        assertThat(result.getBreakdown().get(1).getSubCheck(), is("data_positioning"));
    }

    @Test
    public void shouldOverwriteExistingBreakdownsWhenSupplyingAList() {
        BreakdownResponse firstBreakdown = new BreakdownResponseBuilder().withResult("PASS")
                .withSubCheck("security_features")
                .build();
        BreakdownResponse secondBreakdown = new BreakdownResponseBuilder().withResult("FAIL")
                .withSubCheck("data_positioning")
                .build();
        List<BreakdownResponse> breakdownList = Arrays.asList(firstBreakdown, secondBreakdown);

        BreakdownResponse initialBreakdown = new BreakdownResponseBuilder().withResult("PASS")
                .withSubCheck("live_photo")
                .build();

        ReportResponse result = new ReportResponseBuilder().withRecommendation(SOME_APPROVED_RECOMMENDATION)
                .withBreakdown(initialBreakdown)
                .withBreakdownList(breakdownList)
                .build();

        assertThat(result.getBreakdown(), hasSize(2));
        assertThat(result.getBreakdown().get(0).getResult(), is("PASS"));
        assertThat(result.getBreakdown().get(0).getSubCheck(), is("security_features"));
        assertThat(result.getBreakdown().get(1).getResult(), is("FAIL"));
        assertThat(result.getBreakdown().get(1).getSubCheck(), is("data_positioning"));
    }

}
