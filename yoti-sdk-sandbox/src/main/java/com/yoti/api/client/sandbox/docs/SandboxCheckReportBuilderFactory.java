package com.yoti.api.client.sandbox.docs;

public class SandboxCheckReportBuilderFactory {

    public SandboxDocumentAuthenticityCheckReportBuilder createDocumentAuthenticityCheckReportBuilder() {
        return new SandboxDocumentAuthenticityCheckReportBuilder();
    }

    public SandboxLivenessCheckReportBuilder createLivenessCheckReportBuilder() {
        return new SandboxLivenessCheckReportBuilder();
    }

    public SandboxFaceMatchCheckReportBuilder createFaceMatchCheckReportBuilder() {
        return new SandboxFaceMatchCheckReportBuilder();
    }

    public SandboxTextDataCheckReportBuilder createTextDataCheckReportBuilder() {
        return new SandboxTextDataCheckReportBuilder();
    }

}
