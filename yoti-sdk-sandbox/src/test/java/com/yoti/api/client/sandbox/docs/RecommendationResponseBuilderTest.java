package com.yoti.api.client.sandbox.docs;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import com.yoti.api.client.docs.session.retrieve.RecommendationResponse;
import org.junit.Test;

public class RecommendationResponseBuilderTest {

    private static final String SOME_VALUE = "APPROVE";
    private static final String SOME_REASON = "PICTURE_TOO_DARK";
    private static final String SOME_RECOVERY_SUGGESTION = "BETTER_LIGHTING";

    @Test
    public void shouldThrowWhenMissingValue() {
        try {
            new RecommendationResponseBuilder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("value"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldBuildRecommendationResponseWithRequiredProperties() {
        RecommendationResponse result = new RecommendationResponseBuilder()
                .withValue(SOME_VALUE)
                .build();

        assertEquals(result.getValue(), SOME_VALUE);
        assertNull(result.getReason());
        assertNull(result.getRecoverySuggestion());
    }

    @Test
    public void shouldBuildRecommendationResponseWithAllProperties() {
        RecommendationResponse result = new RecommendationResponseBuilder()
                .withValue(SOME_VALUE)
                .withReason(SOME_REASON)
                .withRecoverySuggestion(SOME_RECOVERY_SUGGESTION)
                .build();

        assertEquals(result.getValue(), SOME_VALUE);
        assertEquals(result.getReason(), SOME_REASON);
        assertEquals(result.getRecoverySuggestion(), SOME_RECOVERY_SUGGESTION);
    }

}
