package com.yoti.api.client.sandbox.docs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yoti.api.client.sandbox.docs.task.SandboxTaskResult;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SandboxExpectation {

    @JsonProperty("task_results")
    private Map<String, SandboxTaskResult> taskResults;

    @JsonProperty("check_reports")
    private SandboxCheckReport sandboxCheckReports;

    SandboxExpectation(Map<String, SandboxTaskResult> taskResults, SandboxCheckReport sandboxCheckReports) {
        this.taskResults = taskResults;
        this.sandboxCheckReports = sandboxCheckReports;
    }

    public Map<String, SandboxTaskResult> getTaskResults() {
        return taskResults;
    }

    public SandboxCheckReport getCheckReports() {
        return sandboxCheckReports;
    }

}
