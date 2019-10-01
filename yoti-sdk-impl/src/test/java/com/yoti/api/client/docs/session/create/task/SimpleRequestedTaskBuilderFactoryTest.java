package com.yoti.api.client.docs.session.create.task;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleRequestedTaskBuilderFactoryTest {

    @Test
    public void shouldReturnSimpleRequestedTextExtractionTaskBuilder() {
        RequestedTextExtractionTaskBuilder result = new SimpleRequestedTaskBuilderFactory()
                .createTextExtractionTaskBuilder();

        assertThat(result, instanceOf(SimpleRequestedTextExtractionTaskBuilder.class));
    }

}
