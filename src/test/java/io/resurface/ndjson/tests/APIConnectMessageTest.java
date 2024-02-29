// © 2016-2024 Graylog, Inc.

package io.resurface.ndjson.tests;

import io.resurface.ndjson.APIConnectMessage;
import io.resurface.ndjson.HttpMessage;
import io.resurface.ndjson.HttpMessages;
import org.junit.Test;

import static com.mscharhag.oleaster.matcher.Matchers.expect;

/**
 * Tests against messages in API Connect dialect.
 */
public class APIConnectMessageTest {

    @Test
    public void parseAndCopyTest() {
        String[] Messages = Helper.APIConnect.getMessages();
        for (String Message : Messages) {
            // parse dialect-specific JSON into HttpMessage
            HttpMessage m = HttpMessages.parse(Message, "ibm");
            checkMessage(m);

            // copy from HttpMessage (to APIConnectMessage)
            APIConnectMessage a = new APIConnectMessage(m);
            checkMessage(a);

            // copy to HttpMessage (from APIConnectMessage) directly
            HttpMessage m2 = new HttpMessage();
            a.copyTo(m2);
            checkMessage(m2);

            // copy to HttpMessage through HttpMessages::parse
            HttpMessage m3 = HttpMessages.parse(a.toString(), "ibm");
            checkMessage(m3);
        }
    }

    private void checkMessage(HttpMessage m) {
        m.sort_details();

        // check custom fields
        expect(m.custom_fields().size()).toEqual(22);
        // todo additional checks here

        // check host
        expect(m.host()).toBeNull();

        // check interval millis
        expect(m.interval_millis()).toEqual(157);

        // check request address
        expect(m.request_address()).toEqual("172.17.61.204");

        // check request body
        expect(m.request_body()).toEqual("{\"data\":\"This is some data!!\"}");

        // check request headers
        expect(m.request_content_type()).toEqual("application/json");
        expect(m.request_headers().size()).toEqual(4);
        expect(m.request_headers().get(0).get(0)).toEqual("accept");
        expect(m.request_headers().get(0).get(1)).toEqual("*/*");
        expect(m.request_headers().get(1).get(0)).toEqual("content-length");
        expect(m.request_headers().get(1).get(1)).toEqual("30");
        expect(m.request_headers().get(2).get(0)).toEqual("foo");
        expect(m.request_headers().get(2).get(1)).toEqual("baz");
        expect(m.request_headers().get(3).get(0)).toEqual("host");
        expect(m.request_headers().get(3).get(1)).toEqual("apis-minimum-gw-gateway-cp4i.b-vpc-cluster-56665e2c7fa43d098323a9b3845292d3-0000.us-south.containers.appdomain.cloud");
        expect(m.request_user_agent()).toEqual("curl/7.81.0");

        // check request method
        expect(m.request_method()).toEqual("POST");

        // check request params
        expect(m.request_params().size()).toEqual(1);
        expect(m.request_params().get(0).get(0)).toEqual("crash");
        expect(m.request_params().get(0).get(1)).toEqual("course");

        // check request url
        expect(m.request_url()).toEqual("https://172.17.24.166/resurface/sandbox/post");

        // check response body
        expect(m.response_body()).toEqual("{\"data\":\"This is some MORE data!!\"}");

        // check response code
        expect(m.response_code()).toEqual("200");

        // check response headers
        expect(m.response_content_type()).toEqual("application/JSON");
        expect(m.response_headers().size()).toEqual(5);
        expect(m.response_headers().get(0).get(0)).toEqual("access-control-allow-credentials");
        expect(m.response_headers().get(0).get(1)).toEqual("true");
        expect(m.response_headers().get(1).get(0)).toEqual("access-control-allow-origin");
        expect(m.response_headers().get(1).get(1)).toEqual("*");
        expect(m.response_headers().get(2).get(0)).toEqual("content-length");
        expect(m.response_headers().get(2).get(1)).toEqual("876");
        expect(m.response_headers().get(3).get(0)).toEqual("date");
        expect(m.response_headers().get(3).get(1)).toEqual("Tue, 20 Dec 2022 16:27:24 GMT");
        expect(m.response_headers().get(4).get(0)).toEqual("server");
        expect(m.response_headers().get(4).get(1)).toEqual("gunicorn/19.9.0");

        // check response time millis
        expect(m.response_time_millis()).toEqual(1671553644629L);

        // check session fields
        expect(m.session_fields().size()).toEqual(0);
    }

    private void checkMessage(APIConnectMessage m) {
        // check host
        expect(m.host).toEqual("172.17.24.166");

        // todo check custom fields

        // check interval millis
        expect(m.timeToServeRequest).toEqual("157");

        // check request address
        expect(m.clientIp).toEqual("172.17.61.204");

        // check request body
        expect(m.requestBody).toEqual("{\"data\":\"This is some data!!\"}");

        // check request headers
        expect(m.requestHttpHeaders.size()).toEqual(6);
        expect(m.requestHttpHeaders.get(0).get("accept")).toEqual("*/*");
        expect(m.requestHttpHeaders.get(1).get("content-length")).toEqual("30");
        expect(m.requestHttpHeaders.get(2).get("foo")).toEqual("baz");
        expect(m.requestHttpHeaders.get(3).get("host")).toEqual("apis-minimum-gw-gateway-cp4i.b-vpc-cluster-56665e2c7fa43d098323a9b3845292d3-0000.us-south.containers.appdomain.cloud");
        expect(m.requestHttpHeaders.get(4).get("content-type")).toEqual("application/json");
        expect(m.requestHttpHeaders.get(5).get("user-agent")).toEqual("curl/7.81.0");

        // check request method
        expect(m.method).toEqual("POST");

        // check request params
        expect(m.queryString).toEqual("crash=course");

        // check request url
        expect(m.requestProtocol).toEqual("https");
        expect(m.uriPath).toEqual("/resurface/sandbox/post");

        // check response body
        expect(m.responseBody).toEqual("{\"data\":\"This is some MORE data!!\"}");

        // check response code
        expect(m.statusCode).toEqual("200");

        // check response headers
        expect(m.responseHttpHeaders.size()).toEqual(6);
        expect(m.responseHttpHeaders.get(0).get("access-control-allow-credentials")).toEqual("true");
        expect(m.responseHttpHeaders.get(1).get("access-control-allow-origin")).toEqual("*");
        expect(m.responseHttpHeaders.get(2).get("content-length")).toEqual("876");
        expect(m.responseHttpHeaders.get(3).get("date")).toEqual("Tue, 20 Dec 2022 16:27:24 GMT");
        expect(m.responseHttpHeaders.get(4).get("server")).toEqual("gunicorn/19.9.0");
        expect(m.responseHttpHeaders.get(5).get("content-type")).toEqual("application/JSON");

        // check response time millis
        expect(m.datetime).toEqual("2022-12-20T16:27:24.629Z");
    }

}
