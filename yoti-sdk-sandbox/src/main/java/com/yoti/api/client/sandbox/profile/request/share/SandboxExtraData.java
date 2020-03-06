package com.yoti.api.client.sandbox.profile.request.share;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;
import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SandboxExtraData {

    @JsonProperty("data_entry")
    private List<SandboxDataEntry> dataEntries;

    private SandboxExtraData(List<SandboxDataEntry> dataEntries) {
        notNull(dataEntries, "dataEntries");
        this.dataEntries = dataEntries;
    }

    public List<SandboxDataEntry> getDataEntries() {
        return dataEntries;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<SandboxDataEntry> dataEntries = new ArrayList<>();

        private Builder() {}

        public Builder withDataEntry(SandboxDataEntry dataEntry) {
            dataEntries.add(dataEntry);
            return this;
        }

        public SandboxExtraData build() {
            notNullOrEmpty(dataEntries, "dataEntries");
            return new SandboxExtraData(dataEntries);
        }

    }

}
