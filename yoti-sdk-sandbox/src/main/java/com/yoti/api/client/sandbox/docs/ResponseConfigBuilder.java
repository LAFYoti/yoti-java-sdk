package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.docs.Constants.ID_DOCUMENT_TEXT_DATA_EXTRACTION;

import java.util.HashMap;
import java.util.Map;

public class ResponseConfigBuilder {

    private Map<String, TaskResult> taskResults = new HashMap<>();

    private CheckReport checkReports;

    public ResponseConfigBuilder withTaskResults(Map<String, TaskResult> taskResults) {
        this.taskResults = taskResults;
        return this;
    }

    public ResponseConfigBuilder withTaskResult(String key, TaskResult taskResult) {
        this.taskResults.put(key, taskResult);
        return this;
    }

    public ResponseConfigBuilder withCheckReport(CheckReport checkReport) {
        this.checkReports = checkReport;
        return this;
    }

    public ResponseConfig build() {
        return new ResponseConfig(taskResults, checkReports);
    }

}
