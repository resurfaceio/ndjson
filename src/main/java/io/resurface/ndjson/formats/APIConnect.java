package io.resurface.ndjson.formats;

import com.google.gson.annotations.SerializedName;
import io.resurface.ndjson.HttpMessage;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class APIConnect extends InitializedMessage {
    @SerializedName("host")
    private String host;
    @SerializedName("request_method")
    private String method;
    @SerializedName("request_http_headers")
    private List<Map<String, String>> requestHttpHeaders;
    @SerializedName("request_body")
    private String requestBody;
    @SerializedName("status_code")
    private String statusCode;
    @SerializedName("response_http_headers")
    private List<Map<String, String>> responseHttpHeaders;
    @SerializedName("response_body")
    private String responseBody;

    @SerializedName("@timestamp")
    private String timestamp;
    @SerializedName("@version")
    private String version;
    @SerializedName("api_id")
    private String apiId;
    @SerializedName("api_name")
    private String apiName;
    @SerializedName("api_version")
    private String apiVersion;
    @SerializedName("app_name")
    private String appName;
    @SerializedName("billing")
    private Map<String, Object> billing;
    @SerializedName("bytes_sent")
    private String bytesSent;
    @SerializedName("bytes_received")
    private String bytesReceived;
    @SerializedName("catalog_id")
    private String catalogId;
    @SerializedName("catalog_name")
    private String catalogName;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_ip")
    private String clientIp;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("developer_org_id")
    private String developerOrgId;
    @SerializedName("developer_org_name")
    private String developerOrgName;
    @SerializedName("developer_org_title")
    private String developerOrgTitle;
    @SerializedName("domain_name")
    private String domainName;
    @SerializedName("endpoint_url")
    private String endpointUrl;
    @SerializedName("gateway_ip")
    private String gatewayIp;
    @SerializedName("global_transaction_id")
    private String globalTransactionId;
    @SerializedName("headers")
    private Map<String, String> headers;
    @SerializedName("http_user_agent")
    private String httpUserAgent;
    @SerializedName("immediate_client_ip")
    private String immediateClientIp;
    @SerializedName("latency_info")
    private List<Map<String, String>> latencyInfo;
    @SerializedName("log_policy")
    private String logPolicy;
    @SerializedName("opentracing_info")
    private Object[] opentracingInfo;
    @SerializedName("org_id")
    private String orgId;
    @SerializedName("org_name")
    private String orgName;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("plan_id")
    private String planId;
    @SerializedName("plan_name")
    private String planName;
    @SerializedName("query_string")
    private String queryString;
    @SerializedName("resource_id")
    private String resourceId;
    @SerializedName("resource_path")
    private String resourcePath;
    @SerializedName("request_protocol")
    private String requestProtocol;
    @SerializedName("tags")
    private String[] tags;
    @SerializedName("time_to_serve_request")
    private String timeToServeRequest;
    @SerializedName("transaction_id")
    private String transactionId;
    @SerializedName("uri_path")
    private String uriPath;

    @Override
    public void initialize(HttpMessage msg) {
        setHost(host);
        setMethod(method);
        setUrl(requestProtocol + "://" + host + uriPath);
        setRequestHeaders(requestHttpHeaders);
        setRequestParameters(parseParameters(queryString));
        setRequestBody(requestBody);

        setStatusCode(statusCode.substring(0, 3));
        setResponseHeaders(responseHttpHeaders);
        setResponseBody(responseBody);

        setNow(String.valueOf(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(datetime)).toEpochMilli()));
        setInterval(timeToServeRequest);

        List<Map<String, String>> customFields = new ArrayList<>();
        if (Collections.addAll(customFields,
                Collections.singletonMap("api_id", apiId),
                Collections.singletonMap("api_name", apiName),
                Collections.singletonMap("api_version", apiVersion),
                Collections.singletonMap("app_name", appName),
                Collections.singletonMap("catalog_id", catalogId),
                Collections.singletonMap("catalog_name", catalogName),
                Collections.singletonMap("client_id", clientId),
                Collections.singletonMap("client_ip", clientIp),
                Collections.singletonMap("developer_org_id", developerOrgId),
                Collections.singletonMap("developer_org_name", developerOrgName),
                Collections.singletonMap("developer_org_title", developerOrgTitle),
                Collections.singletonMap("gateway_ip", gatewayIp),
                Collections.singletonMap("global_transaction_id", globalTransactionId),
                Collections.singletonMap("immediate_client_ip", immediateClientIp),
                Collections.singletonMap("log_policy", logPolicy),
                Collections.singletonMap("org_id", orgId),
                Collections.singletonMap("org_name", orgName),
                Collections.singletonMap("product_name", productName),
                Collections.singletonMap("plan_id", planId),
                Collections.singletonMap("plan_name", planName),
                Collections.singletonMap("resource_id", resourceId),
                Collections.singletonMap("transaction_id", transactionId),
                Collections.singletonMap("version", version)
        )) setCustomFields(customFields);

        super.initialize(msg);
    }
}
