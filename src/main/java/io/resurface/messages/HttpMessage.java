// © 2016-2020 Resurface Labs Inc.

package io.resurface.messages;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Immutable message model built by parsing JSON.
 */
public class HttpMessage {

    /**
     * Parse message details from JSON.
     */
    public HttpMessage(String json) {
        try {
            ArrayList<ArrayList<String>> details = GSON.fromJson(json, GSON_ARRAYLIST_TYPE);
            for (ArrayList<String> detail : details) parseDetail(detail);
            request_headers.sort(COMPARATOR);
            request_params.sort(COMPARATOR);
            response_headers.sort(COMPARATOR);
            session_fields.sort(COMPARATOR);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid json message", e);
        }
    }

    public String host() {
        return host;
    }

    public long interval_millis() {
        return interval_millis;
    }

    public String request_body() {
        return request_body;
    }

    public String request_content_type() {
        return request_content_type;
    }

    public List<ArrayList<String>> request_headers() {
        return request_headers;
    }

    public String request_headers_json() {
        return to_json(request_headers);
    }

    public String request_method() {
        return request_method;
    }

    public List<ArrayList<String>> request_params() {
        return request_params;
    }

    public String request_params_json() {
        return to_json(request_params);
    }

    public String request_url() {
        return request_url;
    }

    public String request_user_agent() {
        return request_user_agent;
    }

    public String response_body() {
        return response_body;
    }

    public String response_code() {
        return response_code;
    }

    public String response_content_type() {
        return response_content_type;
    }

    public List<ArrayList<String>> response_headers() {
        return response_headers;
    }

    public String response_headers_json() {
        return to_json(response_headers);
    }

    public long response_time_millis() {
        return response_time_millis;
    }

    public List<ArrayList<String>> session_fields() {
        return session_fields;
    }

    public int size_request_bytes() {
        return size_request_bytes;
    }

    public int size_response_bytes() {
        return size_response_bytes;
    }

    public int size_total_bytes() {
        return size_request_bytes + size_response_bytes;
    }

    private void parseDetail(ArrayList<String> detail) {
        if (detail == null || detail.size() != 2) return;
        String key = detail.get(0);
        String value = detail.get(1);
        switch (key) {
            case "host":
                host = value;
                break;
            case "interval":
                interval_millis = (long) Float.parseFloat(value);
                break;
            case "now":
                response_time_millis = Long.parseLong(value);
                break;
            case "request_body":
                size_request_bytes += value.length();
                request_body = value;
                break;
            case "request_method":
                request_method = value;
                break;
            case "request_url":
                request_url = value;
                break;
            case "response_body":
                size_response_bytes += value.length();
                response_body = value;
                break;
            case "response_code":
                response_code = value;
                break;
            default:
                if (key.startsWith("request_h")) {
                    size_request_bytes += value.length();
                    key = key.substring(15).toLowerCase();
                    if (key.equals("content-type")) {
                        request_content_type = value;
                    } else if (key.equals("user-agent")) {
                        request_user_agent = value;
                    } else {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(key);
                        list.add(value);
                        request_headers.add(list);
                    }
                } else if (key.startsWith("response_h")) {
                    size_response_bytes += value.length();
                    key = key.substring(16).toLowerCase();
                    if (key.equals("content-type")) {
                        response_content_type = value;
                    } else {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(key);
                        list.add(value);
                        response_headers.add(list);
                    }
                } else if (key.startsWith("request_p")) {
                    size_request_bytes += value.length();
                    key = key.substring(14).toLowerCase();
                    ArrayList<String> list = new ArrayList<>();
                    list.add(key);
                    list.add(value);
                    request_params.add(list);
                } else if (key.startsWith("session_")) {
                    key = key.substring(14).toLowerCase();
                    ArrayList<String> list = new ArrayList<>();
                    list.add(key);
                    list.add(value);
                    session_fields.add(list);
                }
        }
    }

    private String to_json(List<ArrayList<String>> list) {
        if (list == null) return null;
        JsonArray results = new JsonArray();
        for (ArrayList<String> i : list) {
            JsonArray detail = new JsonArray();
            detail.add(i.get(0));
            detail.add(i.get(1));
            results.add(detail);
        }
        return GSON.toJson(results);
    }

    private String host;
    private long interval_millis;
    private String request_body;
    private String request_content_type;
    private final List<ArrayList<String>> request_headers = new ArrayList<>();
    private String request_method;
    private final List<ArrayList<String>> request_params = new ArrayList<>();
    private String request_url;
    private String request_user_agent;
    private String response_body;
    private String response_code;
    private String response_content_type;
    private final List<ArrayList<String>> response_headers = new ArrayList<>();
    private long response_time_millis;
    private final List<ArrayList<String>> session_fields = new ArrayList<>();
    private int size_request_bytes = 0;
    private int size_response_bytes = 0;

    private static final Comparator<ArrayList<String>> COMPARATOR = Comparator.comparing(e -> e.get(0));
    private static final Gson GSON = new Gson();
    private static final Type GSON_ARRAYLIST_TYPE = new TypeToken<ArrayList<ArrayList<String>>>() {
    }.getType();

}
