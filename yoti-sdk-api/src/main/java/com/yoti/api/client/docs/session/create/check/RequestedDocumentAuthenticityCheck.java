package com.yoti.api.client.docs.session.create.check;

public interface RequestedDocumentAuthenticityCheck<T extends RequestedDocumentAuthenticityConfig> extends RequestedCheck<T> {

    @Override
    T getConfig(); // FIXME: Need this?

}
