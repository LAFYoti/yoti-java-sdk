package com.yoti.api.client.docs.session.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FaceMapResponse {

    MediaResponse media;

    public MediaResponse getMedia() {
        return media;
    }

    public void setMedia(MediaResponse media) {
        this.media = media;
    }
}
