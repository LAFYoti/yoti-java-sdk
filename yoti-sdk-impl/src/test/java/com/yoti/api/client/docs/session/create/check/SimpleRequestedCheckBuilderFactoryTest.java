package com.yoti.api.client.docs.session.create.check;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleRequestedCheckBuilderFactoryTest {

    @Test
    public void shouldReturnSimpleRequestedDocumentAuthenticityCheckBuilder() {
        RequestedDocumentAuthenticityCheckBuilder result = new SimpleRequestedCheckBuilderFactory()
                .createAuthenticityCheckBuilder();

        assertThat(result, instanceOf(SimpleRequestedDocumentAuthenticityCheckBuilder.class));
    }

    @Test
    public void shouldReturnSimpleRequestedFaceMatchCheckBuilder() {
        RequestedFaceMatchCheckBuilder result = new SimpleRequestedCheckBuilderFactory()
                .createFaceMatchCheckBuilder();

        assertThat(result, instanceOf(SimpleRequestedFaceMatchCheckBuilder.class));
    }

    @Test
    public void shouldReturnSimpleRequestedLivenessCheckBuilder() {
        RequestedLivenessCheckBuilder result = new SimpleRequestedCheckBuilderFactory()
                .createLivenessCheckBuilder();

        assertThat(result, instanceOf(SimpleRequestedLivenessCheckBuilder.class));
    }

}
