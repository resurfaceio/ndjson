// © 2016-2024 Graylog, Inc.

package io.resurface.ndjson;

import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Model for messages generated by IBM API Connect.
 */
public class APIConnectMessage {
    @SerializedName("host")
    public String host;
    @SerializedName("request_method")
    public String method;
    @SerializedName("request_http_headers")
    public List<Map<String, String>> requestHttpHeaders;
    @SerializedName("request_body")
    public String requestBody;
    @SerializedName("status_code")
    public String statusCode;
    @SerializedName("response_http_headers")
    public List<Map<String, String>> responseHttpHeaders;
    @SerializedName("response_body")
    public String responseBody;
    @SerializedName("@timestamp")
    public String timestamp;
    @SerializedName("@version")
    public String version;
    @SerializedName("api_id")
    public String apiId;
    @SerializedName("api_name")
    public String apiName;
    @SerializedName("api_version")
    public String apiVersion;
    @SerializedName("app_name")
    public String appName;
    @SerializedName("billing")
    public Map<String, Object> billing;
    @SerializedName("bytes_sent")
    public String bytesSent;
    @SerializedName("bytes_received")
    public String bytesReceived;
    @SerializedName("catalog_id")
    public String catalogId;
    @SerializedName("catalog_name")
    public String catalogName;
    @SerializedName("client_id")
    public String clientId;
    @SerializedName("client_ip")
    public String clientIp;
    @SerializedName("datetime")
    public String datetime;
    @SerializedName("developer_org_id")
    public String developerOrgId;
    @SerializedName("developer_org_name")
    public String developerOrgName;
    @SerializedName("developer_org_title")
    public String developerOrgTitle;
    @SerializedName("domain_name")
    public String domainName;
    @SerializedName("endpoint_url")
    public String endpointUrl;
    @SerializedName("gateway_ip")
    public String gatewayIp;
    @SerializedName("global_transaction_id")
    public String globalTransactionId;
    @SerializedName("headers")
    public Map<String, String> headers;
    @SerializedName("http_user_agent")
    public String httpUserAgent;
    @SerializedName("immediate_client_ip")
    public String immediateClientIp;
    @SerializedName("latency_info")
    public List<Map<String, String>> latencyInfo;
    @SerializedName("log_policy")
    public String logPolicy;
    @SerializedName("opentracing_info")
    public Object[] opentracingInfo;
    @SerializedName("org_id")
    public String orgId;
    @SerializedName("org_name")
    public String orgName;
    @SerializedName("product_name")
    public String productName;
    @SerializedName("plan_id")
    public String planId;
    @SerializedName("plan_name")
    public String planName;
    @SerializedName("query_string")
    public String queryString;
    @SerializedName("resource_id")
    public String resourceId;
    @SerializedName("resource_path")
    public String resourcePath;
    @SerializedName("request_protocol")
    public String requestProtocol;
    @SerializedName("tags")
    public String[] tags;
    @SerializedName("time_to_serve_request")
    public String timeToServeRequest;
    @SerializedName("transaction_id")
    public String transactionId;
    @SerializedName("uri_path")
    public String uriPath;

    /**
     * Creates empty message.
     */
    public APIConnectMessage() {
        // do nothing
    }

    /**
     * Creates message from the message provided.
     */
    public APIConnectMessage(HttpMessage msg) {
        copyFrom(msg);
    }

    /**
     * Copy all details from the message provided.
     */
    public void copyFrom(HttpMessage msg) {
        // copy all custom fields
        if ((msg.custom_fields() != null)) {
            for (ArrayList<String> d : msg.custom_fields()) {
                switch (d.get(0)) {
                    case "ibm-api_id":
                        apiId = d.get(1); break;
                    case "ibm-api_name":
                        apiName = d.get(1); break;
                    case "ibm-api_version":
                        apiVersion = d.get(1); break;
                    case "ibm-app_name":
                        appName = d.get(1); break;
                    case "ibm-catalog_id":
                        catalogId = d.get(1); break;
                    case "ibm-catalog_name":
                        catalogName = d.get(1); break;
                    case "ibm-client_id":
                        clientId = d.get(1); break;
                    case "ibm-developer_org_id":
                        developerOrgId = d.get(1); break;
                    case "ibm-developer_org_name":
                        developerOrgName = d.get(1); break;
                    case "ibm-developer_org_title":
                        developerOrgTitle = d.get(1); break;
                    case "ibm-gateway_ip":
                        gatewayIp = d.get(1); break;
                    case "ibm-global_transaction_id":
                        globalTransactionId = d.get(1); break;
                    case "ibm-host":
                        host = d.get(1); break;
                    case "ibm-immediate_client_ip":
                        immediateClientIp = d.get(1); break;
                    case "ibm-log_policy":
                        logPolicy = d.get(1); break;
                    case "ibm-org_id":
                        orgId = d.get(1); break;
                    case "ibm-org_name":
                        orgName = d.get(1); break;
                    case "ibm-product_name":
                        productName = d.get(1); break;
                    case "ibm-plan_id":
                        planId = d.get(1); break;
                    case "ibm-plan_name":
                        planName = d.get(1); break;
                    case "ibm-resource_id":
                        resourceId = d.get(1); break;
                    case "ibm-transaction_id":
                        transactionId = d.get(1); break;
                    case "ibm-version":
                        version = d.get(1); break;
                }
            }
        }

        // copy interval millis
        if (msg.interval_millis() > 0) timeToServeRequest = String.valueOf(msg.interval_millis());

        // copy request address
        if (msg.request_address() != null) clientIp = msg.request_address();

        // copy request body
        if (msg.request_body() != null) requestBody = msg.request_body();

        // copy request headers
        boolean hostHeaderExists = false;
        requestHttpHeaders = new ArrayList<>();
        if (msg.request_headers() != null) {
            for (ArrayList<String> d : msg.request_headers()) {
                String key = d.get(0);
                if (key.equalsIgnoreCase("host")) hostHeaderExists = true;
                requestHttpHeaders.add(Collections.singletonMap(key, d.get(1)));
            }
        }
        if (msg.request_content_type() != null) requestHttpHeaders.add(Collections.singletonMap("content-type", msg.request_content_type()));
        if (msg.request_user_agent() != null) requestHttpHeaders.add(Collections.singletonMap("user-agent", msg.request_user_agent()));

        // copy request method
        if (msg.request_method() != null) method = msg.request_method();

        // copy request params
        if (msg.request_params() != null) {
            StringBuilder s = new StringBuilder();
            for (ArrayList<String> d : msg.request_params()) {
                if (!s.isEmpty()) s.append("&");
                s.append(d.get(0));
                String value = d.get(1);
                s.append(value.isEmpty() ? "" : "=" + value);
            }
            queryString = s.toString();
        }

        // copy request url
        if (msg.request_url() != null) {
            try {
                URL url = new URL(msg.request_url());
                String urlHost = url.getHost();
                if ((!hostHeaderExists) && (host == null || !host.equalsIgnoreCase(urlHost)) && (gatewayIp == null || !gatewayIp.equalsIgnoreCase(urlHost))) {
                    requestHttpHeaders.add(Collections.singletonMap("host", urlHost));
                }
                requestProtocol = url.getProtocol();
                uriPath = url.getPath();
            } catch (MalformedURLException mue) {
                // do nothing
            }
        }

        // copy response body
        if (msg.response_body() != null) responseBody = msg.response_body();

        // copy response code
        if (msg.response_code() != null) statusCode = msg.response_code();

        // copy response headers
        responseHttpHeaders = new ArrayList<>();
        if (msg.response_headers() != null) {
            for (ArrayList<String> d : msg.response_headers()) responseHttpHeaders.add(Collections.singletonMap(d.get(0), d.get(1)));
        }
        if (msg.response_content_type() != null) responseHttpHeaders.add(Collections.singletonMap("content-type", msg.response_content_type()));

        // copy response time millis
        if (msg.response_time_millis() > 0) datetime = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(msg.response_time_millis()));
    }

    /**
     * Copy all details to the message provided.
     */
    public void copyTo(HttpMessage msg) {
        // copy all custom fields
        if (apiId != null) msg.add_custom_field("ibm-api_id", apiId);
        if (apiName != null) msg.add_custom_field("ibm-api_name", apiName);
        if (apiVersion != null) msg.add_custom_field("ibm-api_version", apiVersion);
        if (appName != null) msg.add_custom_field("ibm-app_name", appName);
        if (catalogId != null) msg.add_custom_field("ibm-catalog_id", catalogId);
        if (catalogName != null) msg.add_custom_field("ibm-catalog_name", catalogName);
        if (clientId != null) msg.add_custom_field("ibm-client_id", clientId);
        if (developerOrgId != null) msg.add_custom_field("ibm-developer_org_id", developerOrgId);
        if (developerOrgName != null) msg.add_custom_field("ibm-developer_org_name", developerOrgName);
        if (developerOrgTitle != null) msg.add_custom_field("ibm-developer_org_title", developerOrgTitle);
        if (gatewayIp != null) msg.add_custom_field("ibm-gateway_ip", gatewayIp);
        if (globalTransactionId != null) msg.add_custom_field("ibm-global_transaction_id", globalTransactionId);
        if (host != null) msg.add_custom_field("ibm-host", host);
        if (immediateClientIp != null) msg.add_custom_field("ibm-immediate_client_ip", immediateClientIp);
        if (logPolicy != null) msg.add_custom_field("ibm-log_policy", logPolicy);
        if (orgId != null) msg.add_custom_field("ibm-org_id", orgId);
        if (orgName != null) msg.add_custom_field("ibm-org_name", orgName);
        if (productName != null) msg.add_custom_field("ibm-product_name", productName);
        if (planId != null) msg.add_custom_field("ibm-plan_id", planId);
        if (planName != null) msg.add_custom_field("ibm-plan_name", planName);
        if (resourceId != null) msg.add_custom_field("ibm-resource_id", resourceId);
        if (transactionId != null) msg.add_custom_field("ibm-transaction_id", transactionId);
        if (version != null) msg.add_custom_field("ibm-version", version);

        // copy interval millis
        if (timeToServeRequest != null) msg.set_interval_millis(Long.parseLong(timeToServeRequest));

        // copy request address
        if (clientIp != null) msg.set_request_address(clientIp);

        // copy request body
        if (requestBody != null) msg.set_request_body(requestBody);

        // copy request headers
        String hostHeader = null;
        if ((requestHttpHeaders != null) && !requestHttpHeaders.isEmpty()) {
            for (Map<String, String> header : requestHttpHeaders) {
                for (String key : header.keySet()) {
                    if (key.equalsIgnoreCase("host")) hostHeader = header.get(key);
                    msg.add_request_header(key, header.get(key));
                }
            }
        }

        // copy request method
        if (method != null) msg.set_request_method(method);

        // copy request params
        if (queryString != null) {
            for (Map<String, String> param : parseParameters(queryString)) {
                for (String key : param.keySet()) {
                    msg.add_request_param(key, param.get(key));
                }
            }
        }

        // copy request url
        StringBuilder s = new StringBuilder();
        if (requestProtocol != null) s.append(requestProtocol);
        s.append("://");
        if (hostHeader != null) {
            s.append(hostHeader);
        } else if (gatewayIp != null) {
            s.append(gatewayIp);
        } else if (host != null) {
            s.append(host);
        }
        if (uriPath != null) s.append(uriPath);
        msg.set_request_url(s.toString());

        // copy response body
        if (responseBody != null) msg.set_response_body(responseBody);

        // copy response code
        if (statusCode != null) msg.set_response_code(statusCode.substring(0, 3));

        // copy response headers
        if (responseHttpHeaders != null) {
            for (Map<String, String> header : responseHttpHeaders) {
                for (String key : header.keySet()) {
                    msg.add_response_header(key, header.get(key));
                }
            }
        }

        // copy response time millis
        if (datetime != null) msg.set_response_time_millis(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(datetime)).toEpochMilli());
    }

    /**
     * Returns collection of parameters parsed from specified query string.
     */
    private List<Map<String, String>> parseParameters(String queryString) {
        List<Map<String, String>> params = new ArrayList<>();
        if (queryString.isEmpty()) return params;
        if (queryString.charAt(0) == '?' && queryString.length() > 1) {
            queryString = queryString.substring(1);
        }

        for (String param : queryString.split("&")) {
            String k, v;
            int i = param.indexOf('=');
            if (i == -1) {
                k = param;
                v = "";
            } else {
                k = param.substring(0, i);
                v = param.substring(i + 1);
            }
            params.add(Collections.singletonMap(k, v));
        }
        return params;
    }

    /**
     * Returns message as a string.
     */
    public String toString() {
        return HttpMessage.GSON.toJson(this);
    }

}
