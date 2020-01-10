package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.docs.Constants.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CheckReport {

    @JsonProperty(ID_DOCUMENT_TEXT_DATA_CHECK)
    private final SandboxTextDataCheckReport textDataCheckReport;

    @JsonProperty(ID_DOCUMENT_AUTHENTICITY)
    private final SandboxDocumentAuthenticityCheckReport documentAuthenticityReport;

    @JsonProperty(LIVENESS)
    private final List<SandboxLivenessCheckReport> livenessReport;

    @JsonProperty(ID_DOCUMENT_FACE_MATCH)
    private final SandboxFaceMatchCheckReport documentFaceMatchReport;

    @JsonProperty("async_report_delay")
    private final Integer asyncReportDelay;

    CheckReport(SandboxTextDataCheckReport textDataCheckReport,
                SandboxDocumentAuthenticityCheckReport documentAuthenticityReport,
                List<SandboxLivenessCheckReport> livenessReport,
                SandboxFaceMatchCheckReport documentFaceMatchReport,
                Integer asyncReportsDelay) {
        this.textDataCheckReport = textDataCheckReport;
        this.documentAuthenticityReport = documentAuthenticityReport;
        this.livenessReport = livenessReport;
        this.documentFaceMatchReport = documentFaceMatchReport;
        this.asyncReportDelay = asyncReportsDelay;
    }

    public SandboxTextDataCheckReport getTextDataCheckReport() {
        return textDataCheckReport;
    }

    public SandboxDocumentAuthenticityCheckReport getDocumentAuthenticityReport() {
        return documentAuthenticityReport;
    }

    public List<SandboxLivenessCheckReport> getLivenessReport() {
        return livenessReport;
    }

    public SandboxFaceMatchCheckReport getDocumentFaceMatchReport() {
        return documentFaceMatchReport;
    }

    public Integer getAsyncReportDelay() {
        return asyncReportDelay;
    }
}
