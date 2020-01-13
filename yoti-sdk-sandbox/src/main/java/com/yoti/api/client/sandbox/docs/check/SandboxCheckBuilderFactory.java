package com.yoti.api.client.sandbox.docs.check;

public class SandboxCheckBuilderFactory {

    public SandboxDocumentAuthenticityCheckBuilder createDocumentAuthenticityCheckReportBuilder() {
        return new SandboxDocumentAuthenticityCheckBuilder();
    }

    public SandboxLivenessCheckBuilder createLivenessCheckReportBuilder() {
        return new SandboxLivenessCheckBuilder();
    }

    public SandboxFaceMatchCheckBuilder createFaceMatchCheckReportBuilder() {
        return new SandboxFaceMatchCheckBuilder();
    }

    public SandboxTextDataCheckBuilder createTextDataCheckReportBuilder() {
        return new SandboxTextDataCheckBuilder();
    }

}
