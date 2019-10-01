package com.yoti.api.client.sandbox.docs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseConfig {

    @JsonProperty("task_results")
    private Map<String, TaskResult> taskResults;

    @JsonProperty("check_reports")
    private CheckReport checkReports;

    ResponseConfig(Map<String, TaskResult> taskResults, CheckReport checkReports) {
        this.taskResults = taskResults;
        this.checkReports = checkReports;
    }

    public Map<String, TaskResult> getTaskResults() {
        return taskResults;
    }

    public CheckReport getCheckReports() {
        return checkReports;
    }

}
