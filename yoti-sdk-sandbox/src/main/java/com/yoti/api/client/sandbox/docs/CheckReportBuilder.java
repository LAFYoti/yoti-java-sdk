package com.yoti.api.client.sandbox.docs;

import com.yoti.api.client.docs.session.retrieve.ReportResponse;

import java.util.ArrayList;
import java.util.List;

public class CheckReportBuilder {

    private ReportResponse textDataCheckReport;

    private ReportResponse documentAuthenticityReport;

    private List<ReportResponse> livenessReport = new ArrayList<>();

    private ReportResponse documentFaceMatchReport;

    private Integer asyncReportDelay;

    public CheckReportBuilder withTextDataCheckReport(ReportResponse textDataCheckReport) {
        this.textDataCheckReport = textDataCheckReport;
        return this;
    }

    public CheckReportBuilder withDocumentAuthenticityReport(ReportResponse documentAuthenticityReport) {
        this.documentAuthenticityReport = documentAuthenticityReport;
        return this;
    }

    public CheckReportBuilder withLivenessReport(ReportResponse livenessReport) {
        this.livenessReport.add(livenessReport);
        return this;
    }

    public CheckReportBuilder withDocumentFaceMatchReport(ReportResponse documentFaceMatchReport) {
        this.documentFaceMatchReport = documentFaceMatchReport;
        return this;
    }

    public CheckReportBuilder withAsyncReportDelay(int asyncReportDelay) {
        this.asyncReportDelay = asyncReportDelay;
        return this;
    }

    public CheckReport build() {
        return new CheckReport(textDataCheckReport, documentAuthenticityReport, livenessReport, documentFaceMatchReport, asyncReportDelay);
    }

}
