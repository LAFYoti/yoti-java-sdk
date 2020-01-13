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
    private final SandboxTextDataCheck textDataCheckReport;

    @JsonProperty(ID_DOCUMENT_AUTHENTICITY)
    private final SandboxDocumentAuthenticityCheck documentAuthenticityReport;

    @JsonProperty(LIVENESS)
    private final List<SandboxLivenessCheck> livenessReport;

    @JsonProperty(ID_DOCUMENT_FACE_MATCH)
    private final SandboxFaceMatchCheck documentFaceMatchReport;

    @JsonProperty("async_report_delay")
    private final Integer asyncReportDelay;

    SandboxCheckReport(SandboxTextDataCheck textDataCheckReport,
                       SandboxDocumentAuthenticityCheck documentAuthenticityReport,
                       List<SandboxLivenessCheck> livenessReport,
                       SandboxFaceMatchCheck documentFaceMatchReport,
                       Integer asyncReportsDelay) {
        this.textDataCheckReport = textDataCheckReport;
        this.documentAuthenticityReport = documentAuthenticityReport;
        this.livenessReport = livenessReport;
        this.documentFaceMatchReport = documentFaceMatchReport;
        this.asyncReportDelay = asyncReportsDelay;
    }

    public SandboxTextDataCheck getTextDataCheckReport() {
        return textDataCheckReport;
    }

    public SandboxDocumentAuthenticityCheck getDocumentAuthenticityReport() {
        return documentAuthenticityReport;
    }

    public List<SandboxLivenessCheck> getLivenessReport() {
        return livenessReport;
    }

    public SandboxFaceMatchCheck getDocumentFaceMatchReport() {
        return documentFaceMatchReport;
    }

    public Integer getAsyncReportDelay() {
        return asyncReportDelay;
    }
}
