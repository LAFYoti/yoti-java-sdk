package com.yoti.api.client.docs.session.create.check;

public abstract class RequestedFaceMatchCheckBuilder {

    public abstract RequestedFaceMatchCheckBuilder withManualCheckAlways();

    public abstract RequestedFaceMatchCheckBuilder withManualCheckFallback();

    public abstract RequestedFaceMatchCheckBuilder withManualCheckNever();

    public abstract RequestedFaceMatchCheck build();

}
