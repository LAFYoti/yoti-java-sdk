package com.yoti.api.client.docs.session.retrieve;

import java.util.Collections;
import java.util.List;

import com.yoti.api.client.docs.Constants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AuthenticityCheckResponse.class, name = Constants.ID_DOCUMENT_AUTHENTICITY),
        @JsonSubTypes.Type(value = TextDataCheckResponse.class, name = Constants.ID_DOCUMENT_TEXT_DATA_CHECK),
        @JsonSubTypes.Type(value = ZoomLivenessCheckResponse.class, name = Constants.LIVENESS),
        @JsonSubTypes.Type(value = FaceMatchCheckResponse.class, name = Constants.ID_DOCUMENT_FACE_MATCH)
})
public abstract class CheckResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("resources_used")
    private List<String> resourcesUsed = Collections.emptyList();

    @JsonProperty("generated_media")
    private List<GeneratedMedia> generatedMedia = Collections.emptyList();

    @JsonProperty("report")
    private ReportResponse report;

    @JsonProperty("created")
    private String created;

    @JsonProperty("last_updated")
    private String lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getResourcesUsed() {
        return resourcesUsed;
    }

    public void setResourcesUsed(List<String> resourcesUsed) {
        this.resourcesUsed = resourcesUsed;
    }

    public List<GeneratedMedia> getGeneratedMedia() {
        return generatedMedia;
    }

    public void setGeneratedMedia(List<GeneratedMedia> generatedMedia) {
        this.generatedMedia = generatedMedia;
    }

    public ReportResponse getReport() {
        return report;
    }

    public void setReport(ReportResponse report) {
        this.report = report;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
