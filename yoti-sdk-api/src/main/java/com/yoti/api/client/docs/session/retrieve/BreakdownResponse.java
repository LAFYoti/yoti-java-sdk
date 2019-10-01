package com.yoti.api.client.docs.session.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreakdownResponse {

    @JsonProperty("sub_check")
    private String subCheck;

    @JsonProperty("result")
    private String result;

    @JsonProperty("details")
    private ArrayNode details;

    public BreakdownResponse() {}

    public BreakdownResponse(String subCheck, String result, ArrayNode details) {
        this.subCheck = subCheck;
        this.result = result;
        this.details = details;
    }

    public String getSubCheck() {
        return subCheck;
    }

    public void setSubCheck(String subCheck) {
        this.subCheck = subCheck;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayNode getDetails() {
        return details;
    }

    public void setDetails(ArrayNode details) {
        this.details = details;
    }

}
