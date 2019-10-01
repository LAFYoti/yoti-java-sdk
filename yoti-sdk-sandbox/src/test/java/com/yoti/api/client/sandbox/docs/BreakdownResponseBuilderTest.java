package com.yoti.api.client.sandbox.docs;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;
import org.junit.Test;

public class BreakdownResponseBuilderTest {

    private static final String SOME_RESULT = "PASS";
    private static final String SOME_SUB_CHECK = "security_check";
    private static final ArrayNode SOME_DETAILS;

    static {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        arrayNode.add("SOME_FIRST_DETAIL");
        arrayNode.add("SOME_SECOND_DETAIL");
        SOME_DETAILS = arrayNode;
    }

    @Test
    public void shouldBuildBreakdownResponseWithCorrectProperties() {
        BreakdownResponse result = new BreakdownResponseBuilder()
                .withResult(SOME_RESULT)
                .withSubCheck(SOME_SUB_CHECK)
                .withDetails(SOME_DETAILS)
                .build();

        assertEquals(result.getResult(), SOME_RESULT);
        assertEquals(result.getSubCheck(), SOME_SUB_CHECK);
        assertEquals(result.getDetails(), SOME_DETAILS);
    }

    @Test
    public void shouldThrowWhenMissingSubCheck() {
        try {
            new BreakdownResponseBuilder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("subCheck"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldThrowWhenMissingResult() {
        try {
            new BreakdownResponseBuilder()
                    .withSubCheck(SOME_SUB_CHECK)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("result"));
            return;
        }
        fail("Expected an exception");
    }

}
