package com.yoti.api.client.docs.session.create;

import com.yoti.api.client.docs.session.create.check.RequestedCheck;
import com.yoti.api.client.docs.session.create.task.RequestedTask;

public abstract class SessionSpecBuilder {

    public abstract SessionSpecBuilder withClientSessionTokenTtl(Integer clientSessionTokenTtl);

    public abstract SessionSpecBuilder withResourcesTtl(Integer resourcesTtl);

    public abstract SessionSpecBuilder withUserTrackingId(String userTrackingId);

    public abstract SessionSpecBuilder withNotifications(NotificationConfig notifications);

    public abstract SessionSpecBuilder withRequestedCheck(RequestedCheck requestedCheck);

    public abstract SessionSpecBuilder withRequestedTask(RequestedTask requestedTasks);

    public abstract SessionSpecBuilder withSdkConfig(SdkConfig sdkConfig);

    public abstract SessionSpec build();

}
