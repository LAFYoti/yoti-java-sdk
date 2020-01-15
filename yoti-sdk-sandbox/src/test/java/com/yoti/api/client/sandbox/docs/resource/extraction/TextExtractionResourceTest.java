package com.yoti.api.client.sandbox.docs.resource.extraction;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TextExtractionResourceTest {

    private static final String SOME_CLIENT_EXTRACTION_OUTCOME = "SUCCESS";
    private static final Map<String, Object> SOME_DOCUMENT_FIELDS;

    static {
        SOME_DOCUMENT_FIELDS = new HashMap<>();
        SOME_DOCUMENT_FIELDS.put("someFirstKey", "someFirstValue");
        SOME_DOCUMENT_FIELDS.put("someSecondKey", 13);
        SOME_DOCUMENT_FIELDS.put("someThirdKey", "2002-06-09");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenMissingClientExtractionOutcome() {
        try {
            new TextExtractionResource.Builder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("clientExtractionOutcome"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenDocumentFieldsIsNull() {
        try {
            new TextExtractionResource.Builder()
                    .withClientExtractionOutcome(SOME_CLIENT_EXTRACTION_OUTCOME)
                    .withDocumentFields(null)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("documentFields"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldCorrectlyBuildTextExtractionResourceWithValidProperties() {
        TextExtractionResource result = new TextExtractionResource.Builder()
                .withClientExtractionOutcome(SOME_CLIENT_EXTRACTION_OUTCOME)
                .withDocumentFields(SOME_DOCUMENT_FIELDS)
                .build();

        assertThat(result.getClientExtractionOutcome(), is(SOME_CLIENT_EXTRACTION_OUTCOME));
        assertThat(result.getDocumentFields().entrySet(), everyItem(isIn(SOME_DOCUMENT_FIELDS.entrySet())));
    }

    @Test
    public void Builder_shouldCorrectlyBuildTextExtrationResourceWithSingleDocumentFields() {
        TextExtractionResource result = new TextExtractionResource.Builder()
                .withClientExtractionOutcome(SOME_CLIENT_EXTRACTION_OUTCOME)
                .withDocumentField("firstKey", "firstValue")
                .withDocumentField("secondKey", 13)
                .build();

        assertThat(result.getClientExtractionOutcome(), is(SOME_CLIENT_EXTRACTION_OUTCOME));
        assertThat(result.getDocumentFields(), hasEntry("firstKey", (Object) "firstValue"));
        assertThat(result.getDocumentFields(), hasEntry("secondKey", (Object) 13));
    }

}
