package com.yoti.api.client.docs.session.create.check;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = SimpleRequestedDocumentAuthenticityCheck.class, name = Constants.ID_DOCUMENT_AUTHENTICITY),
//        @JsonSubTypes.Type(value = SimpleRequestedLivenessCheck.class, name = Constants.LIVENESS),
//        @JsonSubTypes.Type(value = SimpleRequestedFaceMatchCheck.class, name = Constants.ID_DOCUMENT_FACE_MATCH)
//})
public abstract class SimpleRequestedCheck<T extends RequestedCheckConfig> implements RequestedCheck<T> {

    @JsonProperty("type")
    public abstract String getType();

    @JsonProperty("config")
    public abstract T getConfig();

}
