package com.yoti.api.client.sandbox.docs;

import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.yoti.api.client.docs.session.retrieve.BreakdownResponse;

public class BreakdownResponseBuilder {

    private String subCheck;

    private String result;

    private ArrayNode details = JsonNodeFactory.instance.arrayNode();

    public static BreakdownResponse passForCheck(String check) {
        return new BreakdownResponse("PASS", check, JsonNodeFactory.instance.arrayNode());
    }

    public static BreakdownResponse failForCheck(String check) {
        return new BreakdownResponse("FAIL", check, JsonNodeFactory.instance.arrayNode());
    }

    public BreakdownResponseBuilder withSubCheck(String subCheck) {
        this.subCheck = subCheck;
        return this;
    }

    public BreakdownResponseBuilder withResult(String result) {
        this.result = result;
        return this;
    }

    public BreakdownResponseBuilder withDetails(ArrayNode details) {
        this.details = details;
        return this;
    }

    public BreakdownResponse build() {
        notNullOrEmpty(subCheck, "subCheck");
        notNullOrEmpty(result, "result");

        return new BreakdownResponse(subCheck, result, details);
    }

}
