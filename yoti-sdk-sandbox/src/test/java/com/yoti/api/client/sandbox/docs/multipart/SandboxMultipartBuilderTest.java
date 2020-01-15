package com.yoti.api.client.sandbox.docs.multipart;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoti.api.client.sandbox.docs.resource.document.DocumentResource;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SandboxMultipartBuilderTest {

    private static final String SOME_NAME = "someName";
    private static final String SOME_TEXT_BODY = "someTextBody";
    private static final String SOME_BOUNDARY = "someBoundary";
    private static final String SOME_JSON_BODY = "{ \"someKey\": \"someValue\" }";
    private static final byte[] SOME_BINARY_BODY = new byte[]{1, 2, 3, 4};
    private static final ContentType SOME_CONTENT_TYPE = ContentType.APPLICATION_JSON;

    @InjectMocks SandboxMultipartBuilder sandboxMultipartBuilder;

    @Mock ObjectMapper objectMapperMock;
    @Mock(answer = Answers.RETURNS_SELF) MultipartEntityBuilder multipartEntityBuilderMock;

    @Test
    public void shouldAddTextBodyToMultipartEntityBuilder() {
        sandboxMultipartBuilder.addTextBody(SOME_NAME, SOME_TEXT_BODY, SOME_CONTENT_TYPE);
        verify(multipartEntityBuilderMock).addTextBody(SOME_NAME, SOME_TEXT_BODY, SOME_CONTENT_TYPE);
    }

    @Test
    public void shouldSetBoundaryOnMultipartEntityBuilder() {
        sandboxMultipartBuilder.setBoundary(SOME_BOUNDARY);
        verify(multipartEntityBuilderMock).setBoundary(SOME_BOUNDARY);
    }

    @Test
    public void shouldAddTextBodyForDescriptorWithJsonString() {
        sandboxMultipartBuilder.addDescriptorJsonBody(SOME_JSON_BODY);
        verify(multipartEntityBuilderMock).addTextBody("descriptor", SOME_JSON_BODY, ContentType.APPLICATION_JSON);
    }

    @Test
    public void shouldAddTextBodyForDescriptorWithSandboxPayload() throws Exception {
        DocumentResource documentResourceMock = mock(DocumentResource.class);
        when(objectMapperMock.writeValueAsString(documentResourceMock)).thenReturn(SOME_JSON_BODY);

        sandboxMultipartBuilder.addDescriptorJsonBody(documentResourceMock);
        verify(multipartEntityBuilderMock).addTextBody("descriptor", SOME_JSON_BODY, ContentType.APPLICATION_JSON);
    }

    @Test
    public void shouldAddBinaryBodyToMultipartEntityBuilder() {
        sandboxMultipartBuilder.addBinaryBody(SOME_NAME, SOME_BINARY_BODY, ContentType.IMAGE_PNG);
        verify(multipartEntityBuilderMock).addBinaryBody(SOME_NAME, SOME_BINARY_BODY, ContentType.IMAGE_PNG, "dummyValue");
    }

    @Test
    public void shouldThrowExceptionWhenFailedToProcessObjectToJson() throws Exception {
        DocumentResource documentResourceMock = mock(DocumentResource.class);
        when(objectMapperMock.writeValueAsString(documentResourceMock)).thenThrow(JsonProcessingException.class);

        try {
            sandboxMultipartBuilder.addDescriptorJsonBody(documentResourceMock);
        } catch (RuntimeException ex) {
            assertThat(ex.getCause(), instanceOf(JsonProcessingException.class));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldAddBinaryContentBodyToMultipartEntityBuilder() {
        sandboxMultipartBuilder.addBinaryContentBody(SOME_BINARY_BODY, SOME_CONTENT_TYPE);
        verify(multipartEntityBuilderMock).addBinaryBody("binary-content", SOME_BINARY_BODY, SOME_CONTENT_TYPE, "dummyValue");
    }

}
