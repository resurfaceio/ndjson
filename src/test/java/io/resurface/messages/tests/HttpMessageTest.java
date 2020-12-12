// © 2016-2019 Resurface Labs Inc.

package io.resurface.messages.tests;

import io.resurface.messages.HttpMessage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static org.junit.Assert.fail;

/**
 * Tests against messages parsed from JSON.
 */
public class HttpMessageTest {

    @Test
    public void parseInvalidJsonTest() {
        List<String> list = new ArrayList<>();
        list.add("\t");
        list.add("\t\n\t");
        list.add("  ");
        list.add("{");
        list.add("}");
        list.add("{}");
        list.add("{ }");
        list.add("{,}");
        list.add("[");
        list.add("]");
        list.add("[]");
        list.add("[],");
        list.add("[ ]");
        list.add("[ , ]");
        list.add("[ \"A, \"B\"]");
        list.add("[ \"A\",]");
        list.add("[ \"A\", \"B\"][ \"A\", \"B\"]");

        for (String json : list) {
            HttpMessage e = null;
            try {
                e = new HttpMessage("{");
                fail("expected parse exception");
            } catch (IllegalArgumentException iae) {
                expect(e).toBeNull();
            } catch (Exception ex) {
                fail("Unexpected exception type");
            }
        }
    }

    @Test
    public void parseMinimalJsonTest() {
        String json = "[[\"xyz\", \"123\"]]";
        HttpMessage m = new HttpMessage(json);
        expect(m.host()).toBeNull();
        expect(m.interval_millis()).toEqual(0);
        expect(m.request_body()).toBeNull();
        expect(m.request_content_type()).toBeNull();
        expect(m.request_headers().size()).toEqual(0);
        expect(m.request_method()).toBeNull();
        expect(m.request_params().size()).toEqual(0);
        expect(m.request_url()).toBeNull();
        expect(m.request_user_agent()).toBeNull();
        expect(m.response_body()).toBeNull();
        expect(m.response_code()).toBeNull();
        expect(m.response_content_type()).toBeNull();
        expect(m.response_headers().size()).toEqual(0);
        expect(m.response_time_millis()).toEqual(0);
        expect(m.session_fields().size()).toEqual(0);
    }

    @Test
    public void parseValidJsonTest() {
        String json = "[" +
                "[\"host\", \"radware\"]," +
                "[\"interval\", \"292.836995\"]," +
                "[\"now\", \"1492836995761\"]," +
                "[\"request_body\", \"{ \\\"hello\\\" : \\\"world\\\" }\"]," +
                "[\"request_header:a\", \"2\"]," +
                "[\"request_header:b\", \"1\"]," +
                "[\"request_header:content-type\", \"Application/JSON\"]," +
                "[\"request_header:user-agent\",\"Mozilla/5.0 (Ubuntu)...\"]," +
                "[\"request_method\", \"POST\"]," +
                "[\"request_param:x22\", \"x22value\"]," +
                "[\"request_param:p1\", \"pvalue1\"]," +
                "[\"request_url\", \"http://something.com/register\"]," +
                "[\"response_body\", \"<html>Hello World!</html>\"]," +
                "[\"response_code\", \"200\"]," +
                "[\"response_header:content-type\", \"text/html\"]," +
                "[\"response_header:x\", \"100\"]," +
                "[\"session_field:bar\", \"barval\"]," +
                "[\"session_field:foo\", \"fooval1\"]," +
                "[\"session_field:foo\", \"fooval2\"]" +
                "]";

        HttpMessage m = new HttpMessage(json);
        expect(m.host()).toEqual("radware");
        expect(m.interval_millis()).toEqual(292);
        expect(m.request_body()).toEqual("{ \"hello\" : \"world\" }");
        expect(m.request_content_type()).toEqual("Application/JSON");
        expect(m.request_headers().size()).toEqual(2);
        expect(m.request_headers().get(0).get(0)).toEqual("a");
        expect(m.request_headers().get(0).get(1)).toEqual("2");
        expect(m.request_headers().get(1).get(0)).toEqual("b");
        expect(m.request_headers().get(1).get(1)).toEqual("1");
        expect(m.request_method()).toEqual("POST");
        expect(m.request_params().size()).toEqual(2);
        expect(m.request_params().get(0).get(0)).toEqual("p1");
        expect(m.request_params().get(0).get(1)).toEqual("pvalue1");
        expect(m.request_params().get(1).get(0)).toEqual("x22");
        expect(m.request_params().get(1).get(1)).toEqual("x22value");
        expect(m.request_url()).toEqual("http://something.com/register");
        expect(m.request_user_agent()).toEqual("Mozilla/5.0 (Ubuntu)...");
        expect(m.response_content_type()).toEqual("text/html");
        expect(m.response_time_millis()).toEqual(1492836995761L);
        expect(m.response_headers().size()).toEqual(1);
        expect(m.response_headers().get(0).get(0)).toEqual("x");
        expect(m.response_headers().get(0).get(1)).toEqual("100");
        expect(m.session_fields().size()).toEqual(3);
        expect(m.session_fields().get(0).get(0)).toEqual("bar");
        expect(m.session_fields().get(0).get(1)).toEqual("barval");
        expect(m.session_fields().get(1).get(0)).toEqual("foo");
        expect(m.session_fields().get(1).get(1)).toEqual("fooval1");
        expect(m.session_fields().get(2).get(0)).toEqual("foo");
        expect(m.session_fields().get(2).get(1)).toEqual("fooval2");
    }

}
