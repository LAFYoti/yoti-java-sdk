package com.yoti.api.client.docs.session.create.check;

public abstract class RequestedLivenessCheckBuilder {

    public abstract RequestedLivenessCheckBuilder forZoomLiveness();

    public abstract RequestedLivenessCheckBuilder forLivenessType(String livenessType);

    public abstract RequestedLivenessCheckBuilder withMaxRetries(int maxRetries);

    public abstract RequestedLivenessCheck build();

}
