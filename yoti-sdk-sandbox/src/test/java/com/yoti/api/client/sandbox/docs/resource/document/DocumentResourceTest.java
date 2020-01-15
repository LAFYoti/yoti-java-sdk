package com.yoti.api.client.sandbox.docs.resource.document;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class DocumentResourceTest {

    private static final String SOME_DOCUMENT_TYPE = "someDocumentType";
    private static final String SOME_ISSUING_COUNTRY = "someIssuingCountry";

    @Test
    public void Builder_shouldThrowExceptionWhenMissingDocumentType() {
        try {
            new DocumentResource.Builder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("documentType"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenMissingIssuingCountry() {
        try {
            new DocumentResource.Builder()
                    .withDocumentType(SOME_DOCUMENT_TYPE)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("issuingCountry"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldBuildDocumentResourceWithCorrectProperties() {
        DocumentResource result = new DocumentResource.Builder()
                .withDocumentType(SOME_DOCUMENT_TYPE)
                .withIssuingCountry(SOME_ISSUING_COUNTRY)
                .build();

        assertThat(result.getDocumentType(), is(SOME_DOCUMENT_TYPE));
        assertThat(result.getIssuingCountry(), is(SOME_ISSUING_COUNTRY));
    }

    @Test
    public void validArePassport_shouldReturnCorrectValues() {
        DocumentResource result = DocumentResource.validArePassport();

        assertThat(result.getIssuingCountry(), is("ARE"));
        assertThat(result.getDocumentType(), is("PASSPORT"));
    }

    @Test
    public void validAreDrivingLicence_shouldReturnCorrectValues() {
        DocumentResource result = DocumentResource.validAreDrivingLicense();

        assertThat(result.getIssuingCountry(), is("ARE"));
        assertThat(result.getDocumentType(), is("DRIVING_LICENCE"));
    }

}
