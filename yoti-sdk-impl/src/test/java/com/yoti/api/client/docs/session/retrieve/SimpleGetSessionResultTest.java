package com.yoti.api.client.docs.session.retrieve;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleGetSessionResultTest {

    @Mock AuthenticityCheckResponse authenticityCheckResponseMock;
    @Mock FaceMatchCheckResponse faceMatchCheckResponseMock;
    @Mock TextDataCheckResponse textDataCheckResponseMock;
    @Mock LivenessCheckResponse livenessCheckResponseMock;

    SimpleGetSessionResult getSessionResult;

    @Test
    public void shouldFilterAuthenticityChecks() throws NoSuchFieldException {
        getSessionResult = new SimpleGetSessionResult();

        FieldSetter.setField(
                getSessionResult,
                getSessionResult.getClass().getDeclaredField("checks"),
                Arrays.asList(
                        authenticityCheckResponseMock,
                        livenessCheckResponseMock,
                        textDataCheckResponseMock,
                        faceMatchCheckResponseMock
                )
        );

        List<AuthenticityCheckResponse> result = getSessionResult.getAuthenticityChecks();
        assertThat(getSessionResult.getChecks(), hasSize(4));
        assertThat(result, hasSize(1));
    }

    @Test
    public void shouldFilterLivenessChecks() throws NoSuchFieldException {
        getSessionResult = new SimpleGetSessionResult();

        FieldSetter.setField(
                getSessionResult,
                getSessionResult.getClass().getDeclaredField("checks"),
                Arrays.asList(
                        authenticityCheckResponseMock,
                        livenessCheckResponseMock,
                        textDataCheckResponseMock,
                        faceMatchCheckResponseMock
                )
        );

        List<LivenessCheckResponse> result = getSessionResult.getLivenessChecks();
        assertThat(getSessionResult.getChecks(), hasSize(4));
        assertThat(result, hasSize(1));
    }

    @Test
    public void shouldFilterTextDataChecks() throws NoSuchFieldException {
        getSessionResult = new SimpleGetSessionResult();

        FieldSetter.setField(
                getSessionResult,
                getSessionResult.getClass().getDeclaredField("checks"),
                Arrays.asList(
                        authenticityCheckResponseMock,
                        livenessCheckResponseMock,
                        textDataCheckResponseMock,
                        faceMatchCheckResponseMock
                )
        );

        List<TextDataCheckResponse> result = getSessionResult.getTextDataChecks();
        assertThat(getSessionResult.getChecks(), hasSize(4));
        assertThat(result, hasSize(1));
    }

    @Test
    public void shouldFilterFaceMatchChecks() throws NoSuchFieldException {
        getSessionResult = new SimpleGetSessionResult();

        FieldSetter.setField(
                getSessionResult,
                getSessionResult.getClass().getDeclaredField("checks"),
                Arrays.asList(
                        authenticityCheckResponseMock,
                        livenessCheckResponseMock,
                        textDataCheckResponseMock,
                        faceMatchCheckResponseMock
                )
        );

        List<FaceMatchCheckResponse> result = getSessionResult.getFaceMatchChecks();
        assertThat(getSessionResult.getChecks(), hasSize(4));
        assertThat(result, hasSize(1));
    }

    @Test
    public void shouldReturnEmptyLists() throws NoSuchFieldException {
        getSessionResult = new SimpleGetSessionResult();

        FieldSetter.setField(
                getSessionResult,
                getSessionResult.getClass().getDeclaredField("checks"),
                new ArrayList<>()
        );

        assertThat(getSessionResult.getChecks(), hasSize(0));
        assertThat(getSessionResult.getAuthenticityChecks(), hasSize(0));
        assertThat(getSessionResult.getFaceMatchChecks(), hasSize(0));
        assertThat(getSessionResult.getTextDataChecks(), hasSize(0));
        assertThat(getSessionResult.getLivenessChecks(), hasSize(0));
    }

}
