package com.yoti.api.client.sandbox.docs.resource.document;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;
import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.sandbox.docs.resource.SandboxPayload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageDescriptor implements SandboxPayload {

    @JsonProperty("regions")
    private final List<Region> regions;

    @JsonProperty("capture_method")
    private final String captureMethod;

    PageDescriptor(List<Region> regions, String captureMethod) {
        this.regions = regions;
        this.captureMethod = captureMethod;
    }

    public static PageDescriptor valid() {
        Region region = Region.valid();
        return new PageDescriptor(Arrays.asList(region, region, region), "CAMERA");
    }

    public List<Region> getRegions() {
        return regions;
    }

    public String getCaptureMethod() {
        return captureMethod;
    }

    public static class Builder {

        private List<Region> regions = new ArrayList<>();
        private String captureMethod;

        public Builder withRegion(Region region) {
            this.regions.add(region);
            return this;
        }

        public Builder withRegions(List<Region> regions) {
            this.regions = regions;
            return this;
        }

        public Builder withCaptureMethod(String captureMethod) {
            this.captureMethod = captureMethod;
            return this;
        }

        public PageDescriptor build() {
            notNull(regions, "regions");
            notNullOrEmpty(captureMethod, "captureMethod");

            return new PageDescriptor(regions, captureMethod);
        }

    }
}
