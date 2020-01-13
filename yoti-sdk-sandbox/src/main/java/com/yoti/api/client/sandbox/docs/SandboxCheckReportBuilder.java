package com.yoti.api.client.sandbox.docs;

import com.yoti.api.client.sandbox.docs.check.SandboxDocumentAuthenticityCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxFaceMatchCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxLivenessCheck;
import com.yoti.api.client.sandbox.docs.check.SandboxTextDataCheck;

import java.util.ArrayList;
import java.util.List;

public class SandboxCheckReportBuilder {

    private SandboxTextDataCheck textDataCheck;

    private SandboxDocumentAuthenticityCheck documentAuthenticityCheck;

    private List<SandboxLivenessCheck> livenessCheck = new ArrayList<>();

    private SandboxFaceMatchCheck documentFaceMatchCheck;

    private Integer asyncReportDelay;

    public SandboxCheckReportBuilder withTextDataCheck(SandboxTextDataCheck textDataCheckReport) {
        this.textDataCheck = textDataCheckReport;
        return this;
    }

    public SandboxCheckReportBuilder withDocumentAuthenticityCheck(SandboxDocumentAuthenticityCheck documentAuthenticityReport) {
        this.documentAuthenticityCheck = documentAuthenticityReport;
        return this;
    }

    public SandboxCheckReportBuilder withLivenessCheck(SandboxLivenessCheck livenessReport) {
        this.livenessCheck.add(livenessReport);
        return this;
    }

    public SandboxCheckReportBuilder withDocumentFaceMatchCheck(SandboxFaceMatchCheck documentFaceMatchReport) {
        this.documentFaceMatchCheck = documentFaceMatchReport;
        return this;
    }

    public SandboxCheckReportBuilder withAsyncReportDelay(int asyncReportDelay) {
        this.asyncReportDelay = asyncReportDelay;
        return this;
    }

    public SandboxCheckReport build() {
        return new SandboxCheckReport(textDataCheck, documentAuthenticityCheck, livenessCheck, documentFaceMatchCheck, asyncReportDelay);
    }

}
