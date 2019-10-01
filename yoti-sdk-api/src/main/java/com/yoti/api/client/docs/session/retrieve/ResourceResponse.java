package com.yoti.api.client.docs.session.retrieve;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("tasks")
    private List<TaskResponse> tasks;

    public List<TaskResponse> getTasks() {
        return tasks;
    }

    public String getId() {
        return id;
    }

}
