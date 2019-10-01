package com.yoti.api.client.docs.session.create.check;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleRequestedLivenessCheckBuilderTest {

    private static final int MAX_RETRIES_VALUE = 3;

    @Test
    public void shouldBuildSimpleRequestedLivenessCheck() {
        RequestedCheck requestedCheck = new SimpleRequestedLivenessCheckBuilder()
                .forLivenessType("SOME_LIVENESS_TYPE")
                .withMaxRetries(MAX_RETRIES_VALUE)
                .build();

        assertThat(requestedCheck, instanceOf(SimpleRequestedLivenessCheck.class));
        assertThat(requestedCheck.getConfig(), instanceOf(SimpleRequestedLivenessConfig.class));
        assertEquals(requestedCheck.getType(), "LIVENESS");

        SimpleRequestedLivenessConfig configResult = (SimpleRequestedLivenessConfig) requestedCheck.getConfig();
        assertEquals(configResult.getLivenessType(), "SOME_LIVENESS_TYPE");
        assertEquals(configResult.getMaxRetries(), MAX_RETRIES_VALUE);
    }

    @Test
    public void shouldBuildSimpleRequestedLivenessCheckWithZoomLivenessType() {
        RequestedCheck requestedCheck = new SimpleRequestedLivenessCheckBuilder()
                .forZoomLiveness()
                .withMaxRetries(MAX_RETRIES_VALUE)
                .build();

        SimpleRequestedLivenessConfig configResult = (SimpleRequestedLivenessConfig) requestedCheck.getConfig();
        assertEquals(configResult.getLivenessType(), "ZOOM");
        assertEquals(configResult.getMaxRetries(), MAX_RETRIES_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_shouldRaiseForNullLivenessType() {
        new SimpleRequestedLivenessCheckBuilder().build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_shouldRaiseForEmptyLivenessType() {
        new SimpleRequestedLivenessCheckBuilder()
                .forLivenessType("").build();
    }

}
