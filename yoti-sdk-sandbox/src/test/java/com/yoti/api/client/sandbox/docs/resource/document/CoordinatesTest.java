package com.yoti.api.client.sandbox.docs.resource.document;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CoordinatesTest {

    private static final int SOME_X_VALUE = 10;
    private static final int SOME_Y_VALUE = 230;

    private static final Coordinates.Coordinate SOME_COORDINATE = new Coordinates.Coordinate(SOME_X_VALUE, SOME_Y_VALUE);

    @Test
    public void Coordinate_shouldRetainCorrectValues() {
        Coordinates.Coordinate result = new Coordinates.Coordinate(SOME_X_VALUE, SOME_Y_VALUE);

        assertThat(result.getX(), is(SOME_X_VALUE));
        assertThat(result.getY(), is(SOME_Y_VALUE));
    }

    @Test
    public void Builder_shouldThrowExceptionWhenMissingBottomLeft() {
        try {
            new Coordinates.Builder().build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("bottomLeft"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenMissingBottomRight() {
        try {
            new Coordinates.Builder()
                    .withBottomLeft(SOME_COORDINATE)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("bottomRight"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenMissingTopLeft() {
        try {
            new Coordinates.Builder()
                    .withBottomLeft(SOME_COORDINATE)
                    .withBottomRight(SOME_COORDINATE)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("topLeft"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldThrowExceptionWhenMissingTopRight() {
        try {
            new Coordinates.Builder()
                    .withBottomLeft(SOME_COORDINATE)
                    .withBottomRight(SOME_COORDINATE)
                    .withTopLeft(SOME_COORDINATE)
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("topRight"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void Builder_shouldCreateValidCoordinatesWithCorrectProperties() {
        Coordinates result = new Coordinates.Builder()
                .withBottomLeft(SOME_COORDINATE)
                .withBottomRight(SOME_COORDINATE)
                .withTopLeft(SOME_COORDINATE)
                .withTopRight(SOME_COORDINATE)
                .build();

        assertThat(result.getBottomLeft(), is(SOME_COORDINATE));
        assertThat(result.getBottomRight(), is(SOME_COORDINATE));
        assertThat(result.getTopLeft(), is(SOME_COORDINATE));
        assertThat(result.getTopRight(), is(SOME_COORDINATE));
    }

    @Test
    public void valid_shouldReturnValidCoordinatesObject() {
        Coordinates result = Coordinates.valid();

        assertThat(result.getBottomLeft().getX(), is(0));
        assertThat(result.getBottomLeft().getY(), is(10));

        assertThat(result.getBottomRight().getX(), is(200));
        assertThat(result.getBottomRight().getY(), is(13));

        assertThat(result.getTopLeft().getX(), is(10));
        assertThat(result.getTopLeft().getY(), is(200));

        assertThat(result.getTopRight().getX(), is(210));
        assertThat(result.getTopRight().getY(), is(205));
    }

}
