package com.yoti.api.client.sandbox.docs.resource.document;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class RegionTest {

    private static final String SOME_TYPE = "FULL_DOCUMENT";
    private static final Coordinates SOME_COORDINATES = Coordinates.valid();

    @Test
    public void Builder_shouldThrowExceptionWhenMissingType() {
        try {
            new Region.Builder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("type"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenMissingCoordinates() {
        try {
            new Region.Builder()
                    .withType(SOME_TYPE)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("coordinates"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldCreateRegionWithValidProperties() {
        Region result = new Region.Builder()
                .withType(SOME_TYPE)
                .withCoordinates(SOME_COORDINATES)
                .build();

        assertThat(result.getType(), is(SOME_TYPE));
        assertThat(result.getCoordinates(), is(SOME_COORDINATES));
    }

}
