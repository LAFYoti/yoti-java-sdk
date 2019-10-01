package com.yoti.api.client.docs.session.retrieve;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class YotiDocsSession {

    @JsonProperty("client_session_token_ttl")
    private long clientSessionTokenTtl;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("user_tracking_id")
    private String userTrackingId;

    @JsonProperty("state")
    private String state;

    @JsonProperty("client_session_token")
    private String clientSessionToken;

    @JsonProperty("checks")
    private List<? extends CheckResponse> checks;

    @JsonProperty("resources")
    private ResourceContainer resources;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getClientSessionTokenTtl() {
        return clientSessionTokenTtl;
    }

    public void setClientSessionTokenTtl(long clientSessionTokenTtl) {
        this.clientSessionTokenTtl = clientSessionTokenTtl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClientSessionToken() {
        return clientSessionToken;
    }

    public void setClientSessionToken(String clientSessionToken) {
        this.clientSessionToken = clientSessionToken;
    }

    public List<? extends CheckResponse> getChecks() {
        return checks;
    }

    public void setChecks(List<CheckResponse> checks) {
        this.checks = checks;
    }

    public ResourceContainer getResources() {
        return resources;
    }

    public void setResources(ResourceContainer resources) {
        this.resources = resources;
    }

    public String getUserTrackingId() {
        return userTrackingId;
    }

    public void setUserTrackingId(String userTrackingId) {
        this.userTrackingId = userTrackingId;
    }

}
