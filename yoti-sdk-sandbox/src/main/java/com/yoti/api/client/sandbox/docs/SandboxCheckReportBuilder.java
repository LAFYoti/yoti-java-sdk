package com.yoti.api.client.sandbox.docs;

import com.yoti.api.client.sandbox.docs.check.SandboxDocumentAuthenticityCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxFaceMatchCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxLivenessCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxTextDataCheck;

import java.util.ArrayList;
import java.util.List;

public class SandboxCheckReportBuilder {

    private SandboxTextDataCheck textDataCheckReport;

    private SandboxDocumentAuthenticityCheck documentAuthenticityReport;

    private List<SandboxLivenessCheck> livenessReport = new ArrayList<>();

    private SandboxFaceMatchCheck documentFaceMatchReport;

    private Integer asyncReportDelay;

    public SandboxCheckReportBuilder withTextDataCheckReport(SandboxTextDataCheck textDataCheckReport) {
        this.textDataCheckReport = textDataCheckReport;
        return this;
    }

    public SandboxCheckReportBuilder withDocumentAuthenticityReport(SandboxDocumentAuthenticityCheck documentAuthenticityReport) {
        this.documentAuthenticityReport = documentAuthenticityReport;
        return this;
    }

    public SandboxCheckReportBuilder withLivenessReport(SandboxLivenessCheck livenessReport) {
        this.livenessReport.add(livenessReport);
        return this;
    }

    public SandboxCheckReportBuilder withDocumentFaceMatchReport(SandboxFaceMatchCheck documentFaceMatchReport) {
        this.documentFaceMatchReport = documentFaceMatchReport;
        return this;
    }

    public SandboxCheckReportBuilder withAsyncReportDelay(int asyncReportDelay) {
        this.asyncReportDelay = asyncReportDelay;
        return this;
    }

    public SandboxCheckReport build() {
        return new SandboxCheckReport(textDataCheckReport, documentAuthenticityReport, livenessReport, documentFaceMatchReport, asyncReportDelay);
    }

}
