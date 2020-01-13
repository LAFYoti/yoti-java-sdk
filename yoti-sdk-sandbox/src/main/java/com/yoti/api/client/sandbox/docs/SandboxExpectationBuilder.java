package com.yoti.api.client.sandbox.docs;

import java.util.HashMap;
import java.util.Map;

public class SandboxExpectationBuilder {

    private Map<String, TaskResult> taskResults = new HashMap<>();

    private CheckReport checkReports;

    public SandboxExpectationBuilder withTaskResults(Map<String, TaskResult> taskResults) {
        this.taskResults = taskResults;
        return this;
    }

    public SandboxExpectationBuilder withTaskResult(String key, TaskResult taskResult) {
        this.taskResults.put(key, taskResult);
        return this;
    }

    public SandboxExpectationBuilder withCheckReport(CheckReport checkReport) {
        this.checkReports = checkReport;
        return this;
    }

    public SandboxExpectation build() {
        return new SandboxExpectation(taskResults, checkReports);
    }

}
