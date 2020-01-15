package com.yoti.api.client.sandbox.docs.resource.document;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;
import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Region {

    @JsonProperty("type")
    private final String type;

    @JsonProperty("coordinates")
    private final Coordinates coordinates;

    Region(String type, Coordinates coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public static Region valid() {
        return new Region("FULL_DOCUMENT", Coordinates.valid());
    }

    public String getType() {
        return type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public static class Builder {

        private String type;
        private Coordinates coordinates;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Region build() {
            notNullOrEmpty(type, "type");
            notNull(coordinates, "coordinates");

            return new Region(type, coordinates);
        }

    }

}
