package com.yoti.api.client.docs.session.create;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SimpleNotificationConfig implements NotificationConfig {

    @JsonProperty("auth_token")
    private final String authToken;

    @JsonProperty("endpoint")
    private final String endpoint;

    @JsonProperty("topics")
    private final List<String> topics;

    SimpleNotificationConfig(String authToken, String endpoint, List<String> topics) {
        this.authToken = authToken;
        this.endpoint = endpoint;
        this.topics = topics;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public List<String> getTopics() {
        return topics;
    }

}
