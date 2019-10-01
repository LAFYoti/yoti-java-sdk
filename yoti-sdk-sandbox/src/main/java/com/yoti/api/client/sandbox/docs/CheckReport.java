package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.docs.Constants.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.docs.session.retrieve.ReportResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CheckReport {

    @JsonProperty(ID_DOCUMENT_TEXT_DATA_CHECK)
    private final ReportResponse textDataCheckReport;

    @JsonProperty(ID_DOCUMENT_AUTHENTICITY)
    private final ReportResponse documentAuthenticityReport;

    @JsonProperty(LIVENESS)
    private final List<ReportResponse> livenessReport;

    @JsonProperty(ID_DOCUMENT_FACE_MATCH)
    private final ReportResponse documentFaceMatchReport;

    @JsonProperty("async_report_delay")
    private final Integer asyncReportDelay;

    CheckReport(ReportResponse textDataCheckReport,
                ReportResponse documentAuthenticityReport,
                List<ReportResponse> livenessReport,
                ReportResponse documentFaceMatchReport,
                Integer asyncReportsDelay) {
        this.textDataCheckReport = textDataCheckReport;
        this.documentAuthenticityReport = documentAuthenticityReport;
        this.livenessReport = livenessReport;
        this.documentFaceMatchReport = documentFaceMatchReport;
        this.asyncReportDelay = asyncReportsDelay;
    }

    public ReportResponse getTextDataCheckReport() {
        return textDataCheckReport;
    }

    public ReportResponse getDocumentAuthenticityReport() {
        return documentAuthenticityReport;
    }

    public List<ReportResponse> getLivenessReport() {
        return livenessReport;
    }

    public ReportResponse getDocumentFaceMatchReport() {
        return documentFaceMatchReport;
    }

    public Integer getAsyncReportDelay() {
        return asyncReportDelay;
    }
}
