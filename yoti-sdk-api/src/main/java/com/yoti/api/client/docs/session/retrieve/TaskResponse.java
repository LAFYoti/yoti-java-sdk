package com.yoti.api.client.docs.session.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.yoti.api.client.docs.Constants;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextExtractionTaskResponse.class, name = Constants.ID_DOCUMENT_TEXT_DATA_EXTRACTION),
})
public abstract class TaskResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created")
    private String created;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("generated_checks")
    private List<GeneratedCheckResponse> generatedChecks = Collections.emptyList();

    @JsonProperty("generated_media")
    private List<GeneratedMedia> generatedMedia = Collections.emptyList();

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getCreated() {
        return created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public List<GeneratedCheckResponse> getGeneratedChecks() {
        return generatedChecks;
    }

    public List<GeneratedMedia> getGeneratedMedia() {
        return generatedMedia;
    }

}
