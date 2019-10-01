package com.yoti.api.client.docs.session.retrieve;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceContainer {

    @JsonProperty("id_documents")
    private List<DocumentResourceResponse> idDocuments;

    @JsonProperty("liveness_capture")
    private List<LivenessResourceResponse> livenessCapture;

    @JsonProperty("checks")
    private List<CheckResponse> checks;

    /**
     * Returns ID documents that were uploaded for checking
     * by the user
     *
     * @return the list of documents
     */
    public List<DocumentResourceResponse> getIdDocuments() {
        return idDocuments;
    }

    /**
     * Returns associated information of liveness capture
     * if this task was specified during session creation.
     *
     * @return the list of liveness resources
     */
    public List<LivenessResourceResponse> getLivenessCapture() {
        return livenessCapture;
    }

    public List<CheckResponse> getChecks() {
        return checks;
    }

}
