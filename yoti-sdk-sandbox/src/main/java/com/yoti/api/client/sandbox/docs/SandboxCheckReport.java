package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.docs.Constants.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.sandbox.docs.check.SandboxDocumentAuthenticityCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxFaceMatchCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxLivenessCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxTextDataCheck;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SandboxCheckReport {

    @JsonProperty(ID_DOCUMENT_TEXT_DATA_CHECK)
    private final SandboxTextDataCheck textDataCheck;

    @JsonProperty(ID_DOCUMENT_AUTHENTICITY)
    private final SandboxDocumentAuthenticityCheck documentAuthenticityCheck;

    @JsonProperty(LIVENESS)
    private final List<SandboxLivenessCheck> livenessCheck;

    @JsonProperty(ID_DOCUMENT_FACE_MATCH)
    private final SandboxFaceMatchCheck documentFaceMatchCheck;

    @JsonProperty("async_report_delay")
    private final Integer asyncReportDelay;

    SandboxCheckReport(SandboxTextDataCheck textDataCheck,
                       SandboxDocumentAuthenticityCheck documentAuthenticityCheck,
                       List<SandboxLivenessCheck> livenessCheck,
                       SandboxFaceMatchCheck documentFaceMatchCheck,
                       Integer asyncReportsDelay) {
        this.textDataCheck = textDataCheck;
        this.documentAuthenticityCheck = documentAuthenticityCheck;
        this.livenessCheck = livenessCheck;
        this.documentFaceMatchCheck = documentFaceMatchCheck;
        this.asyncReportDelay = asyncReportsDelay;
    }

    public SandboxTextDataCheck getTextDataCheck() {
        return textDataCheck;
    }

    public SandboxDocumentAuthenticityCheck getDocumentAuthenticityCheck() {
        return documentAuthenticityCheck;
    }

    public List<SandboxLivenessCheck> getLivenessCheck() {
        return livenessCheck;
    }

    public SandboxFaceMatchCheck getDocumentFaceMatchCheck() {
        return documentFaceMatchCheck;
    }

    public Integer getAsyncReportDelay() {
        return asyncReportDelay;
    }

}
