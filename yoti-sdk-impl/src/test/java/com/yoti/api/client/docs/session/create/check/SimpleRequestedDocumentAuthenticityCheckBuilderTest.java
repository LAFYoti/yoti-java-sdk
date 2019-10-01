package com.yoti.api.client.docs.session.create.check;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleRequestedDocumentAuthenticityCheckBuilderTest {

    @Test
    public void shouldBuildSimpleRequestDocumentAuthenticityCheck() {
        RequestedCheck result = new SimpleRequestedDocumentAuthenticityCheckBuilder()
                .build();

        assertThat(result, instanceOf(SimpleRequestedDocumentAuthenticityCheck.class));
        assertThat(result.getConfig(), instanceOf(SimpleRequestedDocumentAuthenticityConfig.class));
        assertEquals(result.getType(), "ID_DOCUMENT_AUTHENTICITY");
    }

}
