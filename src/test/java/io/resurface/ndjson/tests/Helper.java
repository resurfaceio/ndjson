package io.resurface.ndjson.tests;

public class Helper {

    public static class APIConnect {

        public static String[] getMessages() {
            return new String[] { PostData };
//            return new String[] { PostData, SingleKeyParam };
        }
        private static final String PostData = """
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
                  "Host": "apis-minimum-gw-gateway-cp4i.b-vpc.us-south.containers.appdomain.cloud"
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

        private static final String SingleKeyParam = """
            {
              "product_name": "undefined",
              "developer_org_name": "",
              "datetime": "2024-02-22T13:16:56.167Z",
              "developer_org_title": "",
              "log_policy": "payload",
              "immediate_client_ip": "192.168.0.102",
              "client_ip": "192.168.0.102",
              "offload_max_queue": 7730941132,
              "bytes_sent": 593,
              "plan_id": "",
              "catalog_id": "aac7ee1f-d895-4c35-8e50-79af7b15bb6f",
              "api_ref": "http-bin:1.0.0",
              "opentracing_info": [],
              "resource_id": "::POST:/",
              "transaction_id": "25425",
              "uri_path": "/testorg/testcatalog/post",
              "resource_path": "POST",
              "path_id": "::POST:/post",
              "time_to_serve_request": 368,
              "bytes_received": 0,
              "endpoint_url": "N/A",
              "offload_queue_size": 429754,
              "request_body": "[\\"hello\\"]",
              "app_name": "undefined",
              "product_version": "undefined",
              "plan_name": "undefined",
              "host": "172.16.132.77",
              "tags": [
                "apicapievent"
              ],
              "billing": {
                "model": "free",
                "provider": "none",
                "currency": "USD",
                "trial_period_days": 0,
                "amount": 0
              },
              "storage_max_queue": -1,
              "api_version": "1.0.0",
              "request_method": "POST",
              "response_http_headers": [
                {
                  "Date": "Thu, 22 Feb 2024 13:16:56 GMT"
                },
                {
                  "Content-Type": "application/json"
                },
                {
                  "Server": "gunicorn/19.9.0"
                },
                {
                  "Access-Control-Allow-Origin": "*"
                },
                {
                  "Access-Control-Allow-Credentials": "true"
                },
                {
                  "X-Global-Transaction-ID": "3a09080065d7494800006351"
                },
                {
                  "Content-Length": "593"
                }
              ],
              "latency_info": [
                {
                  "started": 0,
                  "task": "Start"
                },
                {
                  "started": 0,
                  "name": "default-api-route",
                  "task": "api-routing"
                },
                {
                  "started": 0,
                  "name": "default-api-cors",
                  "title": "default-cors",
                  "task": "api-cors"
                },
                {
                  "started": 0,
                  "name": "default-wsdl",
                  "title": "default-wsdl",
                  "task": "assembly-wsdl"
                },
                {
                  "started": 1,
                  "name": "default-html-page",
                  "title": "default-html-page",
                  "task": "assembly-html-page"
                },
                {
                  "started": 1,
                  "name": "default-api-client-identification",
                  "title": "default-client-identification",
                  "task": "api-client-identification"
                },
                {
                  "started": 1,
                  "name": "default-api-ratelimit",
                  "title": "default-ratelimit",
                  "task": "assembly-ratelimit"
                },
                {
                  "started": 1,
                  "name": "default-api-security",
                  "title": "default-security",
                  "task": "api-security"
                },
                {
                  "started": 1,
                  "name": "default-func-call-preflow",
                  "task": "assembly-function-call"
                },
                {
                  "started": 1,
                  "name": "default-api-execute",
                  "task": "api-execute"
                },
                {
                  "started": 1,
                  "name": "testorg_testcatalog_http-bin_1.0.0_invoke_0",
                  "title": "invoke",
                  "task": "assembly-invoke"
                },
                {
                  "started": 367,
                  "name": "default-func-call-main",
                  "task": "assembly-function-call"
                },
                {
                  "started": 368,
                  "name": "default-api-result",
                  "task": "api-result"
                },
                {
                  "started": 368,
                  "name": "default-func-call-global",
                  "task": "assembly-function-call"
                }
              ],
              "client_id": "",
              "@version": "1",
              "developer_org_id": "",
              "api_name": "http-bin",
              "global_transaction_id": "3a09080065d7494800006351",
              "org_name": "testorg",
              "request_http_headers": [
                {
                  "Host": "192.168.0.123:9443"
                },
                {
                  "user-agent": "curl/8.4.0"
                },
                {
                  "accept": "*/*"
                },
                {
                  "content-type": "application/json"
                },
                {
                  "content-length": "9"
                }
              ],
              "api_id": "4bf2decd-9ba0-46ec-b698-c47fd9090082",
              "domain_name": "myappdom",
              "request_protocol": "https",
              "http_user_agent": "curl/8.4.0",
              "storage_queue_size": -1,
              "org_id": "da8a3dbb-8a73-43cb-8034-a6c8699da5b3",
              "status_code": "200 OK",
              "query_string": "world",
              "@timestamp": "2024-02-22T13:16:57.531236778Z",
              "catalog_name": "testcatalog",
              "response_body": "{\\n  \\"args\\": {\\n    \\"world\\": \\"\\"\\n  }, \\n  \\"data\\": \\"[\\\\\\"hello\\\\\\"]\\", \\n  \\"files\\": {}, \\n  \\"form\\": {}, \\n  \\"headers\\": {\\n    \\"Accept\\": \\"*/*\\", \\n    \\"Content-Length\\": \\"9\\", \\n    \\"Content-Type\\": \\"application/json\\", \\n    \\"Host\\": \\"httpbin.org\\", \\n    \\"User-Agent\\": \\"curl/8.4.0\\", \\n    \\"X-Amzn-Trace-Id\\": \\"Root=1-65d74948-7ca238521404d0946d2377a0\\", \\n    \\"X-Client-Ip\\": \\"192.168.0.102\\", \\n    \\"X-Forwarded-Host\\": \\"192.168.0.123\\", \\n    \\"X-Global-Transaction-Id\\": \\"3a09080065d7494800006351\\"\\n  }, \\n  \\"json\\": [\\n    \\"hello\\"\\n  ], \\n  \\"origin\\": \\"192.168.0.123, 10.0.0.17\\", \\n  \\"url\\": \\"https://192.168.0.123/post?world\\"\\n}\\n",
              "gateway_ip": "192.168.0.123",
              "product_ref": "undefined:undefined"
            }
            
            """;
    }

}
