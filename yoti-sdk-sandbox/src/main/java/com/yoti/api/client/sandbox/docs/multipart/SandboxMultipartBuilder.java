package com.yoti.api.client.sandbox.docs.multipart;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoti.api.client.sandbox.docs.resource.SandboxPayload;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SandboxMultipartBuilder {

    public static final String DEFAULT_BOUNDARY = "-----default-test-dynamic-boundary";
    public static final String CONTENT_TYPE_MULTIPART = "multipart/form-data; boundary=";
    public static final String CONTENT_TYPE_MULTIPART_WITH_BOUNDARY = CONTENT_TYPE_MULTIPART + DEFAULT_BOUNDARY;

    private ObjectMapper objectMapper;
    private MultipartEntityBuilder multipartEntityBuilder;

    SandboxMultipartBuilder(ObjectMapper objectMapper,
                            MultipartEntityBuilder multipartEntityBuilder) {
        this.objectMapper = objectMapper;
        this.multipartEntityBuilder = multipartEntityBuilder;
    }

    public static SandboxMultipartBuilder newInstance() {
        return new SandboxMultipartBuilder(
                new ObjectMapper(),
                MultipartEntityBuilder.create().setBoundary(DEFAULT_BOUNDARY)
        );
    }

    public SandboxMultipartBuilder addTextBody(String name, String textBody, ContentType contentType) {
        multipartEntityBuilder = multipartEntityBuilder.addTextBody(name, textBody, contentType);
        return this;
    }

    public SandboxMultipartBuilder setBoundary(String boundary) {
        multipartEntityBuilder = multipartEntityBuilder.setBoundary(boundary);
        return this;
    }

    public SandboxMultipartBuilder addDescriptorJsonBody(String jsonBody) {
        multipartEntityBuilder = multipartEntityBuilder.addTextBody("descriptor", jsonBody, APPLICATION_JSON);
        return this;
    }

    public SandboxMultipartBuilder addDescriptorJsonBody(SandboxPayload jsonBody) {
        try {
            multipartEntityBuilder = multipartEntityBuilder.addTextBody("descriptor", objectMapper.writeValueAsString(jsonBody), APPLICATION_JSON);
            return this;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public SandboxMultipartBuilder addBinaryContentBody(byte[] binaryBody, ContentType contentType) {
        return addBinaryBody("binary-content", binaryBody, contentType);
    }

    public SandboxMultipartBuilder addBinaryBody(String name, byte[] binaryBody, ContentType contentType) {
        multipartEntityBuilder = multipartEntityBuilder.addBinaryBody(name, binaryBody, contentType, "dummyValue"); // FIXME: Do we want to be sending 'dummyValue' or actually something real?
        return this;
    }

    public byte[] getMultipartBytes() {
        try {
            ByteArrayOutputStream multipartStream = new ByteArrayOutputStream();
            HttpEntity data = multipartEntityBuilder.build();
            data.writeTo(multipartStream);
            return multipartStream.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
