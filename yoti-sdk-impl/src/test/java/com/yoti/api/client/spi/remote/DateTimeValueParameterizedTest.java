package com.yoti.api.client.spi.remote;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.yoti.api.client.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class DateTimeValueParameterizedTest {

    private final String input;
    private final String expected;
    public DateTimeValueParameterizedTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0} parses to {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"2006-01-02T22:04:05Z", "DateTimeValue{date=2006-01-02,time=22:04:05.000000}"},
                {"2006-01-02T22:04:05.1Z", "DateTimeValue{date=2006-01-02,time=22:04:05.100000}"},
                {"2006-01-02T22:04:05.12Z", "DateTimeValue{date=2006-01-02,time=22:04:05.120000}"},
                {"2006-01-02T22:04:05.123Z", "DateTimeValue{date=2006-01-02,time=22:04:05.123000}"},
                {"2006-01-02T22:04:05.1234Z", "DateTimeValue{date=2006-01-02,time=22:04:05.123400}"},
                {"2006-01-02T22:04:05.12345Z", "DateTimeValue{date=2006-01-02,time=22:04:05.123450}"},
                {"2006-01-02T22:04:05.123456Z", "DateTimeValue{date=2006-01-02,time=22:04:05.123456}"},
                {"2002-10-02T10:00:00-05:00", "DateTimeValue{date=2002-10-02,time=15:00:00.000000}"},
                {"2002-10-02T10:00:00+11:00", "DateTimeValue{date=2002-10-01,time=23:00:00.000000}"}
        });
    }

    @Test
    public void shouldCorrectlyParseDateTimeValueFromString() {
        DateTime dateTime = DateTimeValue.from(input);
        assertThat(dateTime.toString(), is(expected));
    }

}
