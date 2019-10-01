package com.yoti.api.client.docs.session.create;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;

import com.yoti.api.client.docs.Constants;

import java.util.ArrayList;
import java.util.List;

public class SimpleNotificationConfigBuilder extends NotificationConfigBuilder {

    private final List<String> topics = new ArrayList<>();
    private String authToken;
    private String endpoint;

    @Override
    public NotificationConfigBuilder withAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    @Override
    public NotificationConfigBuilder withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Override
    public NotificationConfigBuilder forResourceUpdate() {
        return withTopic(Constants.RESOURCE_UPDATE);
    }

    @Override
    public NotificationConfigBuilder forTaskCompletion() {
        return withTopic(Constants.TASK_COMPLETION);
    }

    @Override
    public NotificationConfigBuilder forCheckCompletion() {
        return withTopic(Constants.CHECK_COMPLETION);
    }

    @Override
    public NotificationConfigBuilder forSessionCompletion() {
        return withTopic(Constants.SESSION_COMPLETION);
    }

    @Override
    public NotificationConfigBuilder withTopic(String topicName) {
        this.topics.add(topicName);
        return this;
    }

    @Override
    public NotificationConfig build() {
        notNull(topics, "topics");

        return new SimpleNotificationConfig(authToken, endpoint, topics);
    }

}
