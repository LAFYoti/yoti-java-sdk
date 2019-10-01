package com.yoti.api.client.docs.session.retrieve;

import com.yoti.api.client.docs.Constants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneratedCheckResponse {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return Constants.ID_DOCUMENT_TEXT_DATA_CHECK;
    }

}
