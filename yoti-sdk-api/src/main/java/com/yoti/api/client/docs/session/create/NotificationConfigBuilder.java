package com.yoti.api.client.docs.session.create;

public abstract class NotificationConfigBuilder {

    public abstract NotificationConfigBuilder withAuthToken(String authToken);

    public abstract NotificationConfigBuilder withEndpoint(String endpoint);

    public abstract NotificationConfigBuilder forResourceUpdate();

    public abstract NotificationConfigBuilder forTaskCompletion();

    public abstract NotificationConfigBuilder forCheckCompletion();

    public abstract NotificationConfigBuilder forSessionCompletion();

    // FIXME: Do we want this?  Whats the policy on Enums?  Compare with allowedCaptureMethods
    public abstract NotificationConfigBuilder withTopic(String topicName);

    public abstract NotificationConfig build();

}
