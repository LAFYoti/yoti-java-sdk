package com.yoti.api.client.docs.session.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfo {

    @JsonProperty("capture_method")
    private String captureMethod;

    @JsonProperty("media")
    private MediaResponse media;

    public String getCaptureMethod() {
        return captureMethod;
    }

    public MediaResponse getMedia() {
        return media;
    }

}
