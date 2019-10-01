package com.yoti.api.client.docs.session.create;

public interface CreateSessionResult {

    int getClientSessionTokenTtl();

    String getClientSessionToken();

    String getSessionId();

}
