package com.yoti.api.client.sandbox.profile.request.share;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SandboxExtraDataTest {

    @Mock SandboxDataEntry dataEntryMock;

    @Test
    public void shouldThrowExceptionForEmptyListOfDataEntries() {
        try {
            SandboxExtraData.builder()
                    .build();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("dataEntries"));
            return;
        }
        fail("Expected an exception");
    }

    @Test
    public void shouldBuildCorrectly() {
        SandboxExtraData result = SandboxExtraData.builder()
                .withDataEntry(dataEntryMock)
                .build();

        assertThat(result.getDataEntries(), hasSize(1));
        assertThat(result.getDataEntries().get(0), is(dataEntryMock));
    }

}
