package com.yoti.api.client.sandbox.profile.request.share;

import static com.yoti.api.client.spi.remote.util.Validation.notNull;
import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

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

    private static class Builder {

        private List<SandboxDataEntry> dataEntries = new ArrayList<>();

        public Builder withDataEntry(SandboxDataEntry dataEntry) {
            dataEntries.add(dataEntry);
            return this;
        }

//        public SandboxExtraData build() {
//            notNullOrEmpty();
//        }

    }

}
