// © 2016-2023 Resurface Labs Inc.

package io.resurface.ndjson.tests;

import io.resurface.ndjson.HttpMessage;
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
        list.add("[],");
        list.add("[ \"A, \"B\"]");
        list.add("[ \"A\",]");
        list.add("[ \"A\", \"B\"][ \"A\", \"B\"]");

        for (String json : list) {
            HttpMessage e = null;
            try {
                e = new HttpMessage(json);
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
        expect(m.custom_fields().size()).toEqual(0);
        expect(m.host()).toBeNull();
        expect(m.interval_millis()).toEqual(0);
        expect(m.request_address()).toBeNull();
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
        expect(m.toString()).toEqual("[]");
    }

    @Test
    public void parseValidJsonTest() {
        String json = "[" +
                "[\"custom_field:Foo\", \"Bar\"]," +
                "[\"host\", \"radware\"]," +
                "[\"interval\", \"292.836995\"]," +
                "[\"now\", \"1492836995761\"]," +
                "[\"request_body\", \"{ \\\"hello\\\" : \\\"world\\\" }\"]," +
                "[\"request_header:a\", \"2\"]," +
                "[\"request_header:b\", \"1\"]," +
                "[\"request_header:content-type\", \"Application/JSON\"]," +
                "[\"request_header:user-agent\",\"Mozilla/5.0 (Ubuntu)...\"]," +
                "[\"request_header:x-forwarded-for\", \"127.0.0.1\"]," +
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
        m.sort_details();
        expect(m.custom_fields().size()).toEqual(1);
        expect(m.custom_fields().get(0).get(0)).toEqual("foo");
        expect(m.custom_fields().get(0).get(1)).toEqual("Bar");
        expect(m.host()).toEqual("radware");
        expect(m.interval_millis()).toEqual(292);
        expect(m.request_address()).toEqual("127.0.0.1");
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

    @Test
    public void parseZeroIntervalJsonTest() {
        String json = "[[\"interval\", \"0.836995\"]]";
        HttpMessage m = new HttpMessage(json);
        expect(m.interval_millis()).toEqual(1);
    }

    @Test
    public void writeCustomFieldTest() {
        HttpMessage m = new HttpMessage();
        m.set_custom_fields_json("[[\"Foo\",\"Bar\"]]");
        expect(m.custom_fields_json()).toEqual("[[\"foo\",\"Bar\"]]");
        expect(m.toString()).toEqual("[[\"custom_field:foo\",\"Bar\"]]");
    }

    @Test
    public void writeHostTest() {
        HttpMessage m = new HttpMessage();
        m.set_host("radware");
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"host\",\"radware\"]]");
    }

    @Test
    public void writeIntervalMillisTest() {
        HttpMessage m = new HttpMessage();
        m.set_interval_millis(123456789);
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"interval\",123456789]]");
    }

    @Test
    public void writeRequestAddressTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_address("127.0.0.1");
        expect(m.size_request_bytes()).toEqual(24);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"request_header:x-forwarded-for\",\"127.0.0.1\"]]");
    }

    @Test
    public void writeRequestBodyTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_body("as if");
        expect(m.size_request_bytes()).toEqual(5);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"request_body\",\"as if\"]]");
    }

    @Test
    public void writeRequestContentTypeTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_content_type("text/plain");
        expect(m.size_request_bytes()).toEqual(22);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"request_header:content-type\",\"text/plain\"]]");
    }

    @Test
    public void writeRequestHeadersTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_headers_json("[[\"Cookie\",\"Yes\"]]");
        expect(m.size_request_bytes()).toEqual(9);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.request_headers_json()).toEqual("[[\"cookie\",\"Yes\"]]");
        expect(m.toString()).toEqual("[[\"request_header:cookie\",\"Yes\"]]");
    }

    @Test
    public void writeRequestMethodTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_method("GET");
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"request_method\",\"GET\"]]");
    }

    @Test
    public void writeRequestParamsTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_params_json("[[\"Foo\",\"100\"]]");
        expect(m.size_request_bytes()).toEqual(6);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.request_params_json()).toEqual("[[\"foo\",\"100\"]]");
        expect(m.toString()).toEqual("[[\"request_param:foo\",\"100\"]]");
    }

    @Test
    public void writeRequestUrlTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_url("https://resurface.io");
        expect(m.size_request_bytes()).toEqual(20);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"request_url\",\"https://resurface.io\"]]");
    }

    @Test
    public void writeRequestUserAgentTest() {
        HttpMessage m = new HttpMessage();
        m.set_request_user_agent("AWS Security Scanner");
        expect(m.size_request_bytes()).toEqual(30);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"request_header:user-agent\",\"AWS Security Scanner\"]]");
    }

    @Test
    public void writeResponseBodyTest() {
        HttpMessage m = new HttpMessage();
        m.set_response_body("archer");
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(6);
        expect(m.toString()).toEqual("[[\"response_body\",\"archer\"]]");
    }

    @Test
    public void writeResponseCodeTest() {
        HttpMessage m = new HttpMessage();
        m.set_response_code("200");
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"response_code\",\"200\"]]");
    }

    @Test
    public void writeResponseContentTypeTest() {
        HttpMessage m = new HttpMessage();
        m.set_response_content_type("text/html");
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(21);
        expect(m.toString()).toEqual("[[\"response_header:content-type\",\"text/html\"]]");
    }

    @Test
    public void writeResponseHeadersTest() {
        HttpMessage m = new HttpMessage();
        m.set_response_headers_json("[[\"Set-Cookie\",\"Yes\"]]");
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(13);
        expect(m.response_headers_json()).toEqual("[[\"set-cookie\",\"Yes\"]]");
        expect(m.toString()).toEqual("[[\"response_header:set-cookie\",\"Yes\"]]");
    }

    @Test
    public void writeResponseTimeMillisTest() {
        HttpMessage m = new HttpMessage();
        m.set_response_time_millis(123456789);
        expect(m.size_request_bytes()).toEqual(0);
        expect(m.size_response_bytes()).toEqual(0);
        expect(m.toString()).toEqual("[[\"now\",123456789]]");
    }

    @Test
    public void writeSessionFieldTest() {
        HttpMessage m = new HttpMessage();
        m.set_session_fields_json("[[\"Foo\",\"Bar\"]]");
        expect(m.session_fields_json()).toEqual("[[\"foo\",\"Bar\"]]");
        expect(m.toString()).toEqual("[[\"session_field:foo\",\"Bar\"]]");
    }

}
