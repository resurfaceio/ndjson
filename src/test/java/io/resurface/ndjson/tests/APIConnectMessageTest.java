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

    public static String JSON = """
            {
              "response_body": "{\\"data\\":\\"This is some MORE data!!\\"}",
              "status_code": "200 OK",
              "product_name": "N/A",
              "endpoint_url": "N/A",
              "tags": [ "apicapievent" ],
              "org_id": "b18c6e24-8b0c-45db-b9d6-d3b2ee070b18",
              "resource_id": "::POST:/",
              "global_transaction_id": "58665d8063a1e26c000099d0",
              "request_http_headers": [
                {
                  "Host": "apis-minimum-gw-gateway-cp4i.b-vpc-cluster-56665e2c7fa43d098323a9b3845292d3-0000.us-south.containers.appdomain.cloud"
                },
                { "user-agent": "curl/7.81.0" },
                { "accept": "*/*" },
                { "foo": "baz" },
                { "content-type": "application/json" },
                { "content-length": "30" }
              ],
              "datetime": "2022-12-20T16:27:24.629Z",
              "api_id": "53cc83a7-ab06-49a7-a602-7df5ed3d069d",
              "catalog_name": "sandbox",
              "host": "172.17.24.166",
              "@timestamp": "2022-12-20T16:27:25.151Z",
              "immediate_client_ip": "172.17.61.204",
              "plan_name": "N/A",
              "transaction_id": "39376",
              "plan_id": "",
              "bytes_sent": "876",
              "api_version": "1.0.0",
              "bytes_received": "30",
              "domain_name": "apiconnect",
              "@version": "1",
              "http_user_agent": "curl/7.81.0",
              "api_name": "http-bin",
              "developer_org_title": "",
              "developer_org_id": "",
              "client_id": "",
              "org_name": "resurface",
              "query_string": "crash=course",
              "time_to_serve_request": "157",
              "latency_info": [
                { "task": "Start", "started": "0" },
                { "task": "api-routing", "started": "0" },
                { "task": "api-cors", "started": "0" },
                { "task": "api-client-identification", "started": "0" },
                { "task": "assembly-ratelimit", "started": "0" },
                { "task": "api-security", "started": "0" },
                { "task": "assembly-function-call", "started": "0" },
                { "task": "api-execute", "started": "1" },
                { "task": "assembly-invoke", "started": "1" }
              ],
              "app_name": "N/A",
              "opentracing_info": [],
              "billing": {
                "trial_period_days": 0,
                "provider": "none",
                "currency": "USD",
                "amount": 0,
                "model": "free"
              },
              "gateway_ip": "172.17.35.32",
              "uri_path": "/resurface/sandbox/post",
              "developer_org_name": "",
              "request_method": "POST",
              "log_policy": "payload",
              "catalog_id": "b6bf2ef0-672a-46d1-b78b-eac10998ae31",
              "headers": {
                "request_path": "/x2020/v1/apievent/_bulk",
                "content_type": "application/json",
                "http_version": "HTTP/1.0",
                "http_user_agent": null,
                "connection": "close",
                "request_method": "POST",
                "http_host": "apis-minim-4d83af8f-ingestion-https",
                "http_accept": null,
                "content_length": "2849"
              },
              "request_body": "{\\"data\\":\\"This is some data!!\\"}",
              "client_ip": "172.17.61.204",
              "response_http_headers": [
                { "Date": "Tue, 20 Dec 2022 16:27:24 GMT" },
                { "Content-Type": "application/JSON" },
                { "Content-Length": "876" },
                { "Server": "gunicorn/19.9.0" },
                { "Access-Control-Allow-Origin": "*" },
                { "Access-Control-Allow-Credentials": "true" }
              ],
              "resource_path": "POST",
              "request_protocol": "https"
            }
            """;

    @Test
    public void parseAndCopyTest() {
        HttpMessage m = HttpMessages.parse(JSON, "ibm");
        checkMessage(m);

        APIConnectMessage a = new APIConnectMessage(m);
        checkMessage(a);

        HttpMessage m2 = HttpMessages.parse(a.toString(), "ibm");
        checkMessage(m2);
    }

    private void checkMessage(HttpMessage m) {
        m.sort_details();

        // check custom fields
        expect(m.custom_fields().size()).toEqual(21);
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
