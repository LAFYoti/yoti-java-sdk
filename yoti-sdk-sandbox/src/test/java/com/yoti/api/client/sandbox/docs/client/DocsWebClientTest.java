package com.yoti.api.client.sandbox.docs.client;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DocsWebClientTest {

    private static final String SOME_CLIENT_SESSION_TOKEN = "someClientSessionToken";

    @Test
    public void create_shouldReturnValidInstanceOfDocsWebClient() {
        DocsWebClient result = DocsWebClient.create(SOME_CLIENT_SESSION_TOKEN);

        assertNotNull(result);
        assertThat(result, instanceOf(DocsWebClient.class));
    }

    @Test
    public void create_shouldThrowExceptionWhenNullClientSessionToken() {
        try {
            DocsWebClient.create(null);
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("clientSessionToken"));
            return;
        }
        fail("Expected an exception");
    }

}
