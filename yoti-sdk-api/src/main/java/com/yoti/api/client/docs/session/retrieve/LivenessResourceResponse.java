package com.yoti.api.client.docs.session.retrieve;

import com.yoti.api.client.docs.Constants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "liveness_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ZoomLivenessResourceResponse.class, name = Constants.ZOOM),
})
public abstract class LivenessResourceResponse extends ResourceResponse {

    public abstract String getLivenessType();

}
