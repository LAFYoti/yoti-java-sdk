package com.yoti.api.client.sandbox.docs.check;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SandboxCheckBuilderFactoryTest {

    @Test
    public void shouldReturnDocumentAuthenticityCheckBuilder() {
        SandboxDocumentAuthenticityCheckBuilder result = new SandboxCheckBuilderFactory().createDocumentAuthenticityCheckReportBuilder();

        assertNotNull(result);
        assertThat(result, instanceOf(SandboxDocumentAuthenticityCheckBuilder.class));
    }

    @Test
    public void shouldReturnTextDataCheckBuilder() {
        SandboxTextDataCheckBuilder result = new SandboxCheckBuilderFactory().createTextDataCheckReportBuilder();

        assertNotNull(result);
        assertThat(result, instanceOf(SandboxTextDataCheckBuilder.class));
    }

    @Test
    public void shouldReturnLivenessCheckBuilder() {
        SandboxLivenessCheckBuilder result = new SandboxCheckBuilderFactory().createLivenessCheckReportBuilder();

        assertNotNull(result);
        assertThat(result, instanceOf(SandboxLivenessCheckBuilder.class));
    }

    @Test
    public void shouldReturnFaceMatchCheckBuilder() {
        SandboxFaceMatchCheckBuilder result = new SandboxCheckBuilderFactory().createFaceMatchCheckReportBuilder();

        assertNotNull(result);
        assertThat(result, instanceOf(SandboxFaceMatchCheckBuilder.class));
    }

}
