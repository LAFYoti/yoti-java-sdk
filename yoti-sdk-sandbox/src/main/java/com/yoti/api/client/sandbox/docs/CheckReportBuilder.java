package com.yoti.api.client.sandbox.docs;

import java.util.ArrayList;
import java.util.List;

public class CheckReportBuilder {

    private SandboxTextDataCheckReport textDataCheckReport;

    private SandboxDocumentAuthenticityCheckReport documentAuthenticityReport;

    private List<SandboxLivenessCheckReport> livenessReport = new ArrayList<>();

    private SandboxFaceMatchCheckReport documentFaceMatchReport;

    private Integer asyncReportDelay;

    public CheckReportBuilder withTextDataCheckReport(SandboxTextDataCheckReport textDataCheckReport) {
        this.textDataCheckReport = textDataCheckReport;
        return this;
    }

    public CheckReportBuilder withDocumentAuthenticityReport(SandboxDocumentAuthenticityCheckReport documentAuthenticityReport) {
        this.documentAuthenticityReport = documentAuthenticityReport;
        return this;
    }

    public CheckReportBuilder withLivenessReport(SandboxLivenessCheckReport livenessReport) {
        this.livenessReport.add(livenessReport);
        return this;
    }

    public CheckReportBuilder withDocumentFaceMatchReport(SandboxFaceMatchCheckReport documentFaceMatchReport) {
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
