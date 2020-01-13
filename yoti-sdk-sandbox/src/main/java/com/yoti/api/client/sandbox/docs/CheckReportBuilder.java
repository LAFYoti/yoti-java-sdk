package com.yoti.api.client.sandbox.docs;

import java.util.ArrayList;
import java.util.List;

public class CheckReportBuilder {

    private SandboxTextDataCheck textDataCheckReport;

    private SandboxDocumentAuthenticityCheck documentAuthenticityReport;

    private List<SandboxLivenessCheck> livenessReport = new ArrayList<>();

    private SandboxFaceMatchCheck documentFaceMatchReport;

    private Integer asyncReportDelay;

    public CheckReportBuilder withTextDataCheckReport(SandboxTextDataCheck textDataCheckReport) {
        this.textDataCheckReport = textDataCheckReport;
        return this;
    }

    public CheckReportBuilder withDocumentAuthenticityReport(SandboxDocumentAuthenticityCheck documentAuthenticityReport) {
        this.documentAuthenticityReport = documentAuthenticityReport;
        return this;
    }

    public CheckReportBuilder withLivenessReport(SandboxLivenessCheck livenessReport) {
        this.livenessReport.add(livenessReport);
        return this;
    }

    public CheckReportBuilder withDocumentFaceMatchReport(SandboxFaceMatchCheck documentFaceMatchReport) {
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
