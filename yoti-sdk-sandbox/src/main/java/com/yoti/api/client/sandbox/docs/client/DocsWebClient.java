package com.yoti.api.client.sandbox.docs.client;

import static com.yoti.api.client.spi.remote.call.YotiConstants.*;
import static com.yoti.api.client.spi.remote.util.Validation.notNull;
import static com.yoti.api.client.spi.remote.util.Validation.notNullOrEmpty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoti.api.client.sandbox.docs.DocsPathFactory;
import com.yoti.api.client.sandbox.docs.multipart.SandboxMultipartBuilder;
import com.yoti.api.client.sandbox.docs.resource.SandboxPayload;
import com.yoti.api.client.sandbox.docs.resource.document.DocumentResource;
import com.yoti.api.client.sandbox.docs.resource.document.Page;
import com.yoti.api.client.sandbox.docs.resource.extraction.TextExtractionResource;
import com.yoti.api.client.sandbox.docs.resource.liveness.Facemap;
import com.yoti.api.client.sandbox.docs.resource.liveness.ZoomFrame;
import com.yoti.api.client.sandbox.docs.resource.liveness.ZoomLivenessResource;
import com.yoti.api.client.spi.remote.call.HttpMethod;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

public class DocsWebClient {

    private final String baseUrl;
    private final String clientSessionToken;

    private final DocsPathFactory docsPathFactory;
    private final ObjectMapper objectMapper;

    public static DocsWebClient create(String clientSessionToken) {
            return new DocsWebClient(
                    notNull(clientSessionToken, "clientSessionToken"),
                    new DocsPathFactory(),
                    new ObjectMapper()
            );
    }

    DocsWebClient(String clientSessionToken,
                  DocsPathFactory docsPathFactory,
                  ObjectMapper objectMapper) {
        this.clientSessionToken = clientSessionToken;
        this.docsPathFactory = docsPathFactory;
        this.objectMapper = objectMapper;

        this.baseUrl = System.getProperty(PROPERTY_YOTI_DOCS_URL, DEFAULT_YOTI_DOCS_URL);
    }

    public JsonNode createResource(String sessionId, DocumentResource resource) {
        return post(docsPathFactory.getCreateResourcePath(sessionId), resource);
    }

    public JsonNode createPage(String sessionId, String resourceId, int index, Page page) throws IOException {
        String path = docsPathFactory.getCreatePagePath(sessionId, resourceId, index);

        byte[] multipartResource = SandboxMultipartBuilder.newInstance()
                .addDescriptorJsonBody(page.getDescriptor())
                .addBinaryContentBody(page.getBinaryContent(), page.getContentType())
                .getMultipartBytes();

        ByteArrayEntity entity = new ByteArrayEntity(multipartResource);
        HttpUriRequest request = createBaseRequest(HttpMethod.HTTP_PUT, path)
                .addHeader(CONTENT_TYPE, SandboxMultipartBuilder.CONTENT_TYPE_MULTIPART_WITH_BOUNDARY)
                .setEntity(entity)
                .build();

        String responseBody = performRequest(request);
        return responseBody.isEmpty() ? null : objectMapper.readValue(responseBody, JsonNode.class);
    }

    public JsonNode createZoomLivenessResource(String sessionId, ZoomLivenessResource zoomLivenessResource) {
        return post(docsPathFactory.getCreateZoomLivenessResourcePath(sessionId), zoomLivenessResource);
    }

    public JsonNode createZoomLivenessFrame(String sessionId, String zoomResourceId, int index, ZoomFrame zoomFrame) throws IOException {
        String path = docsPathFactory.getCreateZoomLivenessFramePath(sessionId, zoomResourceId, index);

        byte[] multipartResource = SandboxMultipartBuilder.newInstance()
                .addBinaryContentBody(zoomFrame.getBinaryContent(), zoomFrame.getContentType())
                .getMultipartBytes();

        ByteArrayEntity entity = new ByteArrayEntity(multipartResource);
        HttpUriRequest request = createBaseRequest(HttpMethod.HTTP_PUT, path)
                .addHeader(CONTENT_TYPE, SandboxMultipartBuilder.CONTENT_TYPE_MULTIPART_WITH_BOUNDARY)
                .setEntity(entity)
                .build();

        String responseBody = performRequest(request);
        return responseBody.isEmpty() ? null : objectMapper.readValue(responseBody, JsonNode.class);
    }

    public JsonNode createFacemapResource(String sessionId, String zoomResourceId, Facemap facemap) throws IOException {
        String path = docsPathFactory.getCreateFacemapResourcePath(sessionId, zoomResourceId);

        byte[] multipartResource = SandboxMultipartBuilder.newInstance()
                .addBinaryContentBody(facemap.getBinaryContent(), facemap.getContentType())
                .getMultipartBytes();

        ByteArrayEntity entity = new ByteArrayEntity(multipartResource);
        HttpUriRequest request = createBaseRequest(HttpMethod.HTTP_POST, path)
                .addHeader(CONTENT_TYPE, SandboxMultipartBuilder.CONTENT_TYPE_MULTIPART_WITH_BOUNDARY)
                .setEntity(entity)
                .build();

        String responseBody = performRequest(request);
        return responseBody.isEmpty() ? null : objectMapper.readValue(responseBody, JsonNode.class);
    }

    public void createTextExtractionResource(String sessionId, String resourceId, TextExtractionResource textExtractionResource) {
        put(docsPathFactory.getCreateTextExtractionResourcePath(sessionId, resourceId), textExtractionResource);
    }

    public JsonNode post(String path, SandboxPayload payload) {
        try {
            return post(path, objectMapper.writeValueAsBytes(payload));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode post(String path, byte[] body) throws IOException {
        String response = clientRequest(HttpMethod.HTTP_POST, path, body);
        return objectMapper.readValue(response, JsonNode.class);
    }

    public JsonNode put(String path, SandboxPayload payload) {
        try {
            return put(path, objectMapper.writeValueAsBytes(payload));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode put(String path, byte[] body) throws IOException {
        String response = clientRequest(HttpMethod.HTTP_PUT, path, body);
        return response.isEmpty() ? null : objectMapper.readValue(response, JsonNode.class);
    }

    public <T> T get(String path, Class<T> clazz) throws IOException {
        return requestWithCast(HttpMethod.HTTP_GET, path, clazz);
    }

    public <T> T post(String path, Class<T> clazz) throws IOException {
        return requestWithCast(HttpMethod.HTTP_POST, path, clazz);
    }

    public void delete(String path) throws IOException {
        clientRequest(HttpMethod.HTTP_DELETE, path);
    }

    private <T> T requestWithCast(String method, String path, Class<T> clazz) throws IOException {
        String responseBody = clientRequest(method, path);
        try {
            if (responseBody.isEmpty()) {
                return clazz.newInstance();
            }
            Constructor<T> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(responseBody);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String clientRequest(String method, String path, byte[] payload) throws IOException {
            ByteArrayEntity entity = new ByteArrayEntity(payload);
            HttpUriRequest request = createBaseRequest(method, path)
                    .addHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
                    .setEntity(entity)
                    .build();
            return performRequest(request);
    }

    private String clientRequest(String method, String path) throws IOException {
        HttpUriRequest request = createBaseRequest(method, path)
                .addHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
                .build();
        return performRequest(request);
    }

    private String performRequest(HttpUriRequest request) throws IOException {
        try (CloseableHttpClient closeableHttpClient = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(request);

            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity == null) {
                return "";
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode > 299) {
                throw new RuntimeException("Request failed with status code " + statusCode + " with response body " + response.toString());
            }

            return response.toString();
        }
    }

    private RequestBuilder createBaseRequest(String method, String path) {
        URI uri = URI.create(baseUrl + path);
        return RequestBuilder.create(method)
                .setUri(uri)
                .addHeader("X-Yoti-Auth-Token", clientSessionToken);

    }

}
