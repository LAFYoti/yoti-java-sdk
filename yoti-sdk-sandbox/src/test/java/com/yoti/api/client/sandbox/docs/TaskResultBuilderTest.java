package com.yoti.api.client.sandbox.docs;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TaskResultBuilderTest {

    private static final String SOME_KEY = "someKey";

    @Test
    public void shouldBuildWithSingleDocumentField() {
        Object value = "someValue";

        TaskResult result = new TaskResultBuilder()
                .withDocumentField(SOME_KEY, value)
                .build();

        assertThat(result.getDocumentFields(), hasEntry(SOME_KEY, value));
    }

    @Test
    public void shouldBuildWithSingleDocumentFieldAsObject() {
        Object value = 13;

        TaskResult result = new TaskResultBuilder()
                .withDocumentField(SOME_KEY, value)
                .build();

        assertThat(result.getDocumentFields(), hasEntry(SOME_KEY, value));
    }

    @Test
    public void shouldOverrideCurrentMapOfDocumentFields() {
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("someFirstValue", "Jaskier");
        newMap.put("someSecondValue", "Geralt");

        TaskResult result = new TaskResultBuilder()
                .withDocumentField("someInitialName", "someInitialValue")
                .withDocumentFields(newMap)
                .build();

        assertThat(result.getDocumentFields(), not(hasEntry("someInitialName", (Object) "someInitialValue")));
        assertThat(result.getDocumentFields(), hasEntry("someFirstValue", (Object) "Jaskier"));
        assertThat(result.getDocumentFields(), hasEntry("someSecondValue", (Object) "Geralt"));
    }

}
