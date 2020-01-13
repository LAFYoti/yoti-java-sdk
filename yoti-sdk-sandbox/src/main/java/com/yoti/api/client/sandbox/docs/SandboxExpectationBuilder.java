package com.yoti.api.client.sandbox.docs;

import com.yoti.api.client.sandbox.docs.task.SandboxTaskResult;

import java.util.HashMap;
import java.util.Map;

public class SandboxExpectationBuilder {

    private Map<String, SandboxTaskResult> taskResults = new HashMap<>();

    private SandboxCheckReport sandboxCheckReports;

    public SandboxExpectationBuilder withTaskResults(Map<String, SandboxTaskResult> taskResults) {
        this.taskResults = taskResults;
        return this;
    }

    public SandboxExpectationBuilder withTaskResult(String key, SandboxTaskResult taskResult) {
        this.taskResults.put(key, taskResult);
        return this;
    }

    public SandboxExpectationBuilder withCheckReport(SandboxCheckReport sandboxCheckReport) {
        this.sandboxCheckReports = sandboxCheckReport;
        return this;
    }

    public SandboxExpectation build() {
        return new SandboxExpectation(taskResults, sandboxCheckReports);
    }

}
