package com.yoti.api.client.sandbox.docs.resource.document;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {

    @JsonProperty("bottom_left")
    private Coordinate bottomLeft;

    @JsonProperty("bottom_right")
    private Coordinate bottomRight;

    @JsonProperty("top_left")
    private Coordinate topLeft;

    @JsonProperty("top_right")
    private Coordinate topRight;

    Coordinates(Coordinate bottomLeft,
                Coordinate bottomRight,
                Coordinate topLeft,
                Coordinate topRight) {
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.topLeft = topLeft;
        this.topRight = topRight;
    }

    public static Coordinates valid() {
        return new Coordinates(
                new Coordinate(0, 10),
                new Coordinate(200, 13),
                new Coordinate(10, 200),
                new Coordinate(210, 205)
        );
    }

    public Coordinate getBottomLeft() {
        return bottomLeft;
    }

    public Coordinate getBottomRight() {
        return bottomRight;
    }

    public Coordinate getTopLeft() {
        return topLeft;
    }

    public Coordinate getTopRight() {
        return topRight;
    }

    public static class Builder {

        private Coordinate bottomLeft;
        private Coordinate bottomRight;
        private Coordinate topLeft;
        private Coordinate topRight;

        public Builder withBottomLeft(Coordinate bottomLeft) {
            this.bottomLeft = bottomLeft;
            return this;
        }

        public Builder withBottomRight(Coordinate bottomRight) {
            this.bottomRight = bottomRight;
            return this;
        }

        public Builder withTopLeft(Coordinate topLeft) {
            this.topLeft = topLeft;
            return this;
        }

        public Builder withTopRight(Coordinate topRight) {
            this.topRight = topRight;
            return this;
        }

        public Coordinates build() {
            notNull(bottomLeft, "bottomLeft");
            notNull(bottomRight, "bottomRight");
            notNull(topLeft, "topLeft");
            notNull(topRight, "topRight");

            return new Coordinates(bottomLeft, bottomRight, topLeft, topRight);
        }

    }

    public static class Coordinate {

        @JsonProperty("x")
        private final int x;

        @JsonProperty("y")
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
