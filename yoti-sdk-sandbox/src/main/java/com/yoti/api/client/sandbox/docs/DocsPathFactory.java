package com.yoti.api.client.sandbox.docs;

public class DocsPathFactory {

    private static final String CREATE_RESOURCE_PATH = "/sessions/%s/resources/id-documents";
    private static final String CREATE_PAGE_PATH = "/sessions/%s/resources/id-documents/%s/pages/%d";

    private static final String CREATE_ZOOM_LIVENESS_RESOURCE_PATH = "/sessions/%s/resources/liveness/zoom";
    private static final String CREATE_ZOOM_LIVENESS_FRAME_PATH = "/sessions/%s/resources/liveness/zoom/%s/frames/%d";
    private static final String CREATE_FACEMAP_RESOURCE_PATH = "/sessions/%s/resources/liveness/zoom/%s/facemap";

    private static final String CREATE_TEXT_EXTRACTION_RESOURCE_PATH = "/sessions/%s/resources/id-documents/%s/text-extraction";

    public String getCreateResourcePath(String sessionId) {
        return String.format(CREATE_RESOURCE_PATH, sessionId);
    }

    public String getCreatePagePath(String sessionId, String resourceId, int index) {
        return String.format(CREATE_PAGE_PATH, sessionId, resourceId, index);
    }

    public String getCreateZoomLivenessResourcePath(String sessionId) {
        return String.format(CREATE_ZOOM_LIVENESS_RESOURCE_PATH, sessionId);
    }

    public String getCreateZoomLivenessFramePath(String sessionId, String zoomResourceId, int index) {
        return String.format(CREATE_ZOOM_LIVENESS_FRAME_PATH, sessionId, zoomResourceId, index);
    }

    public String getCreateFacemapResourcePath(String sessionId, String zoomResourceId) {
        return String.format(CREATE_FACEMAP_RESOURCE_PATH, sessionId, zoomResourceId);
    }

    public String getCreateTextExtractionResourcePath(String sessionId, String resourceId) {
        return String.format(CREATE_TEXT_EXTRACTION_RESOURCE_PATH, sessionId, resourceId);
    }

}
