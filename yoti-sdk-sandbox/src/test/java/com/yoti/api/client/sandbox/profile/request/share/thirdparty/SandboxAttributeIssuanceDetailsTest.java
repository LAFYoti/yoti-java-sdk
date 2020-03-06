package com.yoti.api.client.sandbox.profile.request.share.thirdparty;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SandboxAttributeIssuanceDetailsTest {

    private static final String SOME_ISSUANCE_TOKEN = "someIssuanceToken";
    private static final String SOME_EXPIRY_DATE = "2019-06-09T00:00:00.000Z";
    private static final String SOME_DEFINITION = "com.thirdparty.id";

    @Test
    public void shouldThrowExceptionWhenIssuanceTokenIsNull() {
        try {
            SandboxAttributeIssuanceDetails.builder()
                    .withIssuanceToken(null);
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("issuanceToken"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldThrowExceptionWhenIssuanceTokenIsEmpty() {
        try {
            SandboxAttributeIssuanceDetails.builder()
                    .withIssuanceToken("");
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("issuanceToken"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldThrowExceptionWhenExpiryDateIsNull() {
        try {
            SandboxAttributeIssuanceDetails.builder()
                    .withExpiryDate(null);
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("expiryDate"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldThrowExceptionWhenExpiryDateIsEmpty() {
        try {
            SandboxAttributeIssuanceDetails.builder()
                    .withExpiryDate("");
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("expiryDate"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldThrowExceptionWhenDefinitionIsNull() {
        try {
            SandboxAttributeIssuanceDetails.builder()
                    .withDefinition(null);
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("definition"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldThrowExceptionWhenDefinitionIsEmpty() {
        try {
            SandboxAttributeIssuanceDetails.builder()
                    .withDefinition("");
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("definition"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldBuildCorrectly() {
        SandboxAttributeIssuanceDetails result = SandboxAttributeIssuanceDetails.builder()
                .withIssuanceToken(SOME_ISSUANCE_TOKEN)
                .withExpiryDate(SOME_EXPIRY_DATE)
                .withDefinition(SOME_DEFINITION)
                .build();

        assertThat(result.getValue(), is(notNullValue()));

        SandboxAttributeIsssuanceDetailsPayload payload = result.getValue();
        assertThat(payload.getIssuanceToken(), is(SOME_ISSUANCE_TOKEN));
        assertThat(payload.getIssuingAttributes().getExpiryDate(), is(SOME_EXPIRY_DATE));
        assertThat(payload.getIssuingAttributes().getDefinitions(), hasSize(1));
    }

}
