package com.yoti.api.client.docs.session.retrieve;

import java.util.List;

import com.yoti.api.client.docs.Constants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZoomLivenessResourceResponse extends LivenessResourceResponse {

    @JsonProperty("facemap")
    private FaceMapResponse faceMap;

    @JsonProperty("frames")
    private List<FrameResponse> frames;

    public FaceMapResponse getFaceMap() {
        return faceMap;
    }

    public void setFaceMap(FaceMapResponse faceMap) {
        this.faceMap = faceMap;
    }

    public List<FrameResponse> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameResponse> frames) {
        this.frames = frames;
    }

    @Override
    public String getLivenessType() {
        return Constants.ZOOM;
    }

}
