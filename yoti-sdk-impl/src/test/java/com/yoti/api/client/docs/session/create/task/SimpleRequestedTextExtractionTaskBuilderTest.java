package com.yoti.api.client.docs.session.create.task;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleRequestedTextExtractionTaskBuilderTest {

    @Test
    public void shouldBuildSimpleRequestedTextExtractionTaskWithManualFallbackAlways() {
        RequestedTextExtractionTask result = new SimpleRequestedTextExtractionTaskBuilder()
                .withManualFallbackAlways()
                .build();

        assertThat(result, instanceOf(SimpleRequestedTextExtractionTask.class));
        assertThat(result.getConfig(), instanceOf(SimpleRequestedTextExtractionTaskConfig.class));
        assertEquals(result.getType(), "ID_DOCUMENT_TEXT_DATA_EXTRACTION");

        SimpleRequestedTextExtractionTaskConfig configResult = (SimpleRequestedTextExtractionTaskConfig) result.getConfig();
        assertEquals(configResult.getManualCheck(), "ALWAYS");
    }

    @Test
    public void shouldBuildSimpleRequestedTextExtractionTaskWithManualFallbackFallback() {
        RequestedTextExtractionTask result = new SimpleRequestedTextExtractionTaskBuilder()
                .withManualFallbackFallback()
                .build();

        SimpleRequestedTextExtractionTaskConfig configResult = (SimpleRequestedTextExtractionTaskConfig) result.getConfig();
        assertEquals(configResult.getManualCheck(), "FALLBACK");
    }

    @Test
    public void shouldBuildSimpleRequestedTextExtractionTaskWithManualFallbackNever() {
        RequestedTextExtractionTask result = new SimpleRequestedTextExtractionTaskBuilder()
                .withManualFallbackNever()
                .build();

        SimpleRequestedTextExtractionTaskConfig configResult = (SimpleRequestedTextExtractionTaskConfig) result.getConfig();
        assertEquals(configResult.getManualCheck(), "NEVER");
    }

}
