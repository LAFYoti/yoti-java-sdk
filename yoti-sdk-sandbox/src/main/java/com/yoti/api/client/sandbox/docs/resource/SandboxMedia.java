package com.yoti.api.client.sandbox.docs.resource;

import org.apache.http.entity.ContentType;

public interface SandboxMedia {

    byte[] getBinaryContent();

    ContentType getContentType();

}
