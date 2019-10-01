package com.yoti.api.client.docs.session.create.check;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleRequestedFaceMatchCheckBuilderTest {

    @Test
    public void shouldBuildWithManualCheckAlways() {
        RequestedCheck result = new SimpleRequestedFaceMatchCheckBuilder()
                .withManualCheckAlways()
                .build();

        assertThat(result, instanceOf(SimpleRequestedFaceMatchCheck.class));
        assertThat(result.getConfig(), instanceOf(SimpleRequestedFaceMatchConfig.class));
        assertEquals(result.getType(), "ID_DOCUMENT_FACE_MATCH");

        SimpleRequestedFaceMatchConfig configResult = (SimpleRequestedFaceMatchConfig) result.getConfig();
        assertEquals(configResult.getManualCheck(), "ALWAYS");
    }

    @Test
    public void shouldBuildWithManualCheckFallback() {
        RequestedCheck result = new SimpleRequestedFaceMatchCheckBuilder()
                .withManualCheckFallback()
                .build();

        assertThat(result, instanceOf(SimpleRequestedFaceMatchCheck.class));
        assertThat(result.getConfig(), instanceOf(SimpleRequestedFaceMatchConfig.class));
        assertEquals(result.getType(), "ID_DOCUMENT_FACE_MATCH");

        SimpleRequestedFaceMatchConfig configResult = (SimpleRequestedFaceMatchConfig) result.getConfig();
        assertEquals(configResult.getManualCheck(), "FALLBACK");
    }

    @Test
    public void shouldBuildWithManualCheckNever() {
        RequestedCheck result = new SimpleRequestedFaceMatchCheckBuilder()
                .withManualCheckNever()
                .build();

        assertThat(result, instanceOf(SimpleRequestedFaceMatchCheck.class));
        assertThat(result.getConfig(), instanceOf(SimpleRequestedFaceMatchConfig.class));
        assertEquals(result.getType(), "ID_DOCUMENT_FACE_MATCH");

        SimpleRequestedFaceMatchConfig configResult = (SimpleRequestedFaceMatchConfig) result.getConfig();
        assertEquals(configResult.getManualCheck(), "NEVER");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseForNullManualCheckType() {
        new SimpleRequestedFaceMatchCheckBuilder().build();
    }

}
