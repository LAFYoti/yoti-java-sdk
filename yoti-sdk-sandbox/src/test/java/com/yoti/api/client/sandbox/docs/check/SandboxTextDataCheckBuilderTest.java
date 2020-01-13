package com.yoti.api.client.sandbox.docs.check;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;
import com.yoti.api.client.sandbox.docs.BreakdownResponseBuilder;
import com.yoti.api.client.sandbox.docs.RecommendationResponseBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SandboxTextDataCheckBuilderTest {

    private static final String SOME_CHECK = "security_features";
    private static final RecommendationResponse SOME_RECOMMENDATION = RecommendationResponseBuilder.approvedRecommendation();
    private static final BreakdownResponse SOME_BREAKDOWN = BreakdownResponseBuilder.passForCheck(SOME_CHECK);
    private static final Map<String, Object> SOME_DOCUMENT_FIELDS;

    static {
        SOME_DOCUMENT_FIELDS = new HashMap<>();
        SOME_DOCUMENT_FIELDS.put("someFirstDocumentField", "someValue");
        SOME_DOCUMENT_FIELDS.put("someOtherDocumentField", 13);
    }

    @Test
    public void shouldRaiseExceptionWhenMissingRecommendation() {
        try {
            new SandboxTextDataCheckBuilder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("recommendation"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldRaiseExceptionWhenBreakdownListIsSetToNull() {
        try {
            new SandboxTextDataCheckBuilder().withRecommendation(SOME_RECOMMENDATION)
                    .withBreakdownList(null)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("breakdown"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldRaiseExceptionWhenDocumentFieldsIsSetToNull() {
        try {
            new SandboxTextDataCheckBuilder().withRecommendation(SOME_RECOMMENDATION)
                    .withDocumentFields(null)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("documentFields"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldBuildWhenGivenOnlyRecommendation() {
        SandboxTextDataCheck result = new SandboxTextDataCheckBuilder().withRecommendation(SOME_RECOMMENDATION)
                .build();

        assertNotNull(result);
        assertThat(result.getRecommendation().getValue(), is(SOME_RECOMMENDATION.getValue()));
        assertThat(result.getRecommendation().getReason(), is(SOME_RECOMMENDATION.getReason()));
        assertThat(result.getRecommendation().getRecoverySuggestion(), is(SOME_RECOMMENDATION.getRecoverySuggestion()));
        assertThat(result.getBreakdown(), hasSize(0));
        assertThat(result.getDocumentFields().entrySet(), hasSize(0));
    }

    @Test
    public void shouldBuildWhenGivenAllProperties() {
        SandboxTextDataCheck result = new SandboxTextDataCheckBuilder().withRecommendation(SOME_RECOMMENDATION)
                .withBreakdown(SOME_BREAKDOWN)
                .withDocumentFields(SOME_DOCUMENT_FIELDS)
                .build();

        assertNotNull(result);
        assertThat(result.getRecommendation().getValue(), is(SOME_RECOMMENDATION.getValue()));
        assertThat(result.getRecommendation().getReason(), is(SOME_RECOMMENDATION.getReason()));
        assertThat(result.getRecommendation().getRecoverySuggestion(), is(SOME_RECOMMENDATION.getRecoverySuggestion()));

        assertThat(result.getBreakdown(), hasSize(1));
        assertThat(result.getBreakdown().get(0).getResult(), is(SOME_BREAKDOWN.getResult()));
        assertThat(result.getBreakdown().get(0).getSubCheck(), is(SOME_BREAKDOWN.getSubCheck()));
        assertThat(result.getBreakdown().get(0).getDetails(), is(SOME_BREAKDOWN.getDetails()));

        assertThat(result.getDocumentFields().entrySet(), hasSize(2));
        assertThat((String) result.getDocumentFields().get("someFirstDocumentField"), is("someValue"));
        assertThat((Integer) result.getDocumentFields().get("someOtherDocumentField"), is(13));
    }

}
