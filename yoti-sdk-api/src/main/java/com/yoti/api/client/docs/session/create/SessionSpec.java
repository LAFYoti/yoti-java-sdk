package com.yoti.api.client.docs.session.create;

import com.yoti.api.client.docs.session.create.check.RequestedCheck;
import com.yoti.api.client.docs.session.create.task.RequestedTask;

import java.util.List;

public interface SessionSpec {

    Integer getClientSessionTokenTtl();

    Integer getResourcesTtl();

    String getUserTrackingId();

    NotificationConfig getNotifications();

    List<RequestedCheck> getRequestedChecks();

    List<RequestedTask> getRequestedTasks();

    SdkConfig getSdkConfig();

}
