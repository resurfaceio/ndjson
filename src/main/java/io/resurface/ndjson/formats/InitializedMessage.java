package io.resurface.ndjson.formats;

import io.resurface.ndjson.HttpMessage;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InitializedMessage implements Initialized {

    public void initialize(HttpMessage msg) {
        msg.add("host", host);
        msg.add("interval", interval);
        msg.add("now", now);

        msg.add("request_method", method);
        msg.add("request_url", url);
        msg.add("request_body", requestBody);

        msg.add("response_code", statusCode);
        msg.add("response_body", responseBody);

        for (Map<String, String> header: requestHeaders) {
            for (String key: header.keySet()) {
                msg.add_request_header(key, header.get(key));
            }
        }

        for (Map<String, String> header: responseHeaders) {
            for (String key: header.keySet()) {
                msg.add_response_header(key, header.get(key));
            }
        }

        for (Map<String, String> header: requestParameters) {
            for (String key: header.keySet()) {
                msg.add_request_param(key, header.get(key));
            }
        }

        for (Map<String, String> header: sessionFields) {
            for (String key: header.keySet()) {
                msg.add_session_field(key, header.get(key));
            }
        }

        for (Map<String, String> header: customFields) {
            for (String key: header.keySet()) {
                msg.add_custom_field(key, header.get(key));
            }
        }
    }

    List<Map<String, String>> parseParameters (String queryString) {
        List<Map<String,String>> params = new ArrayList<>();
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
                v = param.substring(i+1);
            }
            params.add(Collections.singletonMap(k, v));
        }
        return params;
    }
    private transient String method;
    private transient String url;
    private transient String host;
    private transient List<Map<String, String>> requestHeaders = Collections.emptyList();
    private transient List<Map<String, String>> requestParameters = Collections.emptyList();
    private transient String requestBody;
    private transient String statusCode;
    private transient List<Map<String, String>> responseHeaders = Collections.emptyList();
    private transient String responseBody;
    private transient List<Map<String, String>> sessionFields = Collections.emptyList();
    private transient List<Map<String, String>> customFields = Collections.emptyList();
    private transient String now;
    private transient String interval;

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setRequestHeaders(List<Map<String, String>> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public void setRequestParameters(List<Map<String, String>> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseHeaders(List<Map<String, String>> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public void setSessionFields(List<Map<String, String>> sessionFields) {
        this.sessionFields = sessionFields;
    }

    public void setCustomFields(List<Map<String, String>> customFields) {
        this.customFields = customFields;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
