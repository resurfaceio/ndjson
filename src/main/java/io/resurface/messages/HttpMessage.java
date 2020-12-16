// © 2016-2020 Resurface Labs Inc.

package io.resurface.messages;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Model for messages representing HTTP calls.
 */
public class HttpMessage {

    /**
     * Creates empty message.
     */
    public HttpMessage() {
        // do nothing
    }

    /**
     * Parses message details from specified JSON.
     */
    public HttpMessage(String json) {
        try {
            ArrayList<ArrayList<String>> details = GSON.fromJson(json, GSON_ARRAYLIST_TYPE);
            for (ArrayList<String> detail : details) add(detail);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid json message", e);
        }
    }

    /**
     * Adds detail as two-entry key/value list.
     */
    public void add(ArrayList<String> detail) {
        if (detail == null || detail.size() != 2) return;
        String key = detail.get(0);
        String value = detail.get(1);
        add(key, value);
    }

    /**
     * Adds detail as key/value pair.
     */
    public void add(String key, String value) {
        switch (key) {
            case "host":
                host = value;
                break;
            case "interval":
            case "interval_millis":
                interval_millis = (long) Float.parseFloat(value);
                break;
            case "now":
            case "response_time_millis":
                response_time_millis = Long.parseLong(value);
                break;
            case "request_body":
                request_body = value;
                break;
            case "request_method":
                request_method = value;
                break;
            case "request_url":
                request_url = value;
                break;
            case "response_body":
                response_body = value;
                break;
            case "response_code":
                response_code = value;
                break;
            default:
                if (key.startsWith("request_h")) {
                    key = key.substring(15);
                    add_request_header(key, value);
                } else if (key.startsWith("response_h")) {
                    key = key.substring(16);
                    add_response_header(key, value);
                } else if (key.startsWith("request_p")) {
                    key = key.substring(14);
                    add_request_param(key, value);
                } else if (key.startsWith("session_")) {
                    key = key.substring(14);
                    add_session_field(key, value);
                }
        }
    }

    /**
     * Adds request header to the message.
     */
    public void add_request_header(String name, String value) {
        if (name.equalsIgnoreCase("content-type")) {
            request_content_type = value;
        } else if (name.equalsIgnoreCase("user-agent")) {
            request_user_agent = value;
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(name.toLowerCase());
            list.add(value);
            request_headers.add(list);
        }
    }

    /**
     * Adds request param to the message.
     */
    public void add_request_param(String name, String value) {
        ArrayList<String> list = new ArrayList<>();
        list.add(name.toLowerCase());
        list.add(value);
        request_params.add(list);
    }

    /**
     * Adds response header to the message.
     */
    public void add_response_header(String name, String value) {
        if (name.equalsIgnoreCase("content-type")) {
            response_content_type = value;
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(name.toLowerCase());
            list.add(value);
            response_headers.add(list);
        }
    }

    /**
     * Adds session field to the message.
     */
    public void add_session_field(String name, String value) {
        ArrayList<String> list = new ArrayList<>();
        list.add(name.toLowerCase());
        list.add(value);
        session_fields.add(list);
    }

    /**
     * Returns the list as encoded JSON.
     */
    public String as_json(List<ArrayList<String>> list) {
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

    /**
     * Returns the host that responded to the API call.
     */
    public String host() {
        return host;
    }

    /**
     * Sets the host that responded to the API call.
     */
    public void set_host(String host) {
        this.host = host;
    }

    /**
     * Returns the interval between request and response in milliseconds.
     */
    public long interval_millis() {
        return interval_millis;
    }

    /**
     * Sets the interval between request and response in milliseconds.
     */
    public void set_interval_millis(long interval_millis) {
        this.interval_millis = interval_millis;
    }

    /**
     * Returns the request body payload.
     */
    public String request_body() {
        return request_body;
    }

    /**
     * Sets the request body payload.
     */
    public void set_request_body(String request_body) {
        this.request_body = request_body;
    }

    /**
     * Returns the value of the "content-type" request header.
     */
    public String request_content_type() {
        return request_content_type;
    }

    /**
     * Sets the value of the "content-type" request header.
     */
    public void set_request_content_type(String request_content_type) {
        this.request_content_type = request_content_type;
    }

    /**
     * Returns a list of generic request headers, without special headers like content-type or user-agent.
     */
    public List<ArrayList<String>> request_headers() {
        return request_headers;
    }

    /**
     * Sets list of generic request headers in JSON format.
     */
    public void set_request_headers_json(String json) {
        ArrayList<ArrayList<String>> details = GSON.fromJson(json, GSON_ARRAYLIST_TYPE);
        request_headers.clear();
        for (ArrayList<String> detail : details) add_request_header(detail.get(0), detail.get(1));
    }

    /**
     * Returns a list of generic request headers, in JSON format.
     */
    public String request_headers_json() {
        return as_json(request_headers);
    }

    /**
     * Returns the value of the request method.
     */
    public String request_method() {
        return request_method;
    }

    /**
     * Sets the value of the request method.
     */
    public void set_request_method(String request_method) {
        this.request_method = request_method;
    }

    /**
     * Returns a list of request parameters.
     */
    public List<ArrayList<String>> request_params() {
        return request_params;
    }

    /**
     * Sets list of request parameters in JSON format.
     */
    public void set_request_params_json(String json) {
        ArrayList<ArrayList<String>> details = GSON.fromJson(json, GSON_ARRAYLIST_TYPE);
        request_params.clear();
        for (ArrayList<String> detail : details) add_request_param(detail.get(0), detail.get(1));
    }

    /**
     * Returns a list of request parameters, in JSON format.
     */
    public String request_params_json() {
        return as_json(request_params);
    }

    /**
     * Returns the value of the request URL.
     */
    public String request_url() {
        return request_url;
    }

    /**
     * Sets the value of the request URL.
     */
    public void set_request_url(String request_url) {
        this.request_url = request_url;
    }

    /**
     * Returns the value of the "user-agent" request header.
     */
    public String request_user_agent() {
        return request_user_agent;
    }

    /**
     * Sets the value of the "user-agent" request header.
     */
    public void set_request_user_agent(String request_user_agent) {
        this.request_user_agent = request_user_agent;
    }

    /**
     * Returns the response body payload.
     */
    public String response_body() {
        return response_body;
    }

    /**
     * Sets the response body payload.
     */
    public void set_response_body(String response_body) {
        this.response_body = response_body;
    }

    /**
     * Returns the value of the response code.
     */
    public String response_code() {
        return response_code;
    }

    /**
     * Sets the value of the response code.
     */
    public void set_response_code(String response_code) {
        this.response_code = response_code;
    }

    /**
     * Returns the value of the "content-type" response header.
     */
    public String response_content_type() {
        return response_content_type;
    }

    /**
     * Sets the value of the "content-type" response header.
     */
    public void set_response_content_type(String response_content_type) {
        this.response_content_type = response_content_type;
    }

    /**
     * Returns a list of generic response headers, without special headers like content-type.
     */
    public List<ArrayList<String>> response_headers() {
        return response_headers;
    }

    /**
     * Sets list of generic response headers in JSON format.
     */
    public void set_response_headers_json(String json) {
        ArrayList<ArrayList<String>> details = GSON.fromJson(json, GSON_ARRAYLIST_TYPE);
        response_headers.clear();
        for (ArrayList<String> detail : details) add_response_header(detail.get(0), detail.get(1));
    }

    /**
     * Returns a list of generic response parameters, in JSON format.
     */
    public String response_headers_json() {
        return as_json(response_headers);
    }

    /**
     * Returns the timestamp marking the completion of the response, in milliseconds.
     */
    public long response_time_millis() {
        return response_time_millis;
    }

    /**
     * Sets the timestamp marking the completion of the response, in milliseconds.
     */
    public void set_response_time_millis(long response_time_millis) {
        this.response_time_millis = response_time_millis;
    }

    /**
     * Returns a list of session fields.
     */
    public List<ArrayList<String>> session_fields() {
        return session_fields;
    }

    /**
     * Returns a list of session fields, in JSON format.
     */
    public String session_fields_json() {
        return as_json(session_fields);
    }

    /**
     * Sets list of session fields in JSON format.
     */
    public void set_session_fields_json(String json) {
        ArrayList<ArrayList<String>> details = GSON.fromJson(json, GSON_ARRAYLIST_TYPE);
        session_fields.clear();
        for (ArrayList<String> detail : details) add_session_field(detail.get(0), detail.get(1));
    }

    /**
     * Sorts detail lists.
     */
    public void sort_details() {
        request_headers.sort(COMPARATOR);
        request_params.sort(COMPARATOR);
        response_headers.sort(COMPARATOR);
        session_fields.sort(COMPARATOR);
    }

    /**
     * Returns the approximate size of the request.
     */
    public int size_request_bytes() {
        int result = 0;
        if (request_body != null) result += request_body.length();
        if (request_content_type != null) result += 12 + request_content_type.length();
        for (ArrayList<String> i : request_headers) result += i.get(0).length() + i.get(1).length();
        for (ArrayList<String> i : request_params) result += i.get(0).length() + i.get(1).length();
        if (request_url != null) result += request_url.length();
        if (request_user_agent != null) result += 10 + request_user_agent.length();
        return result;
    }

    /**
     * Returns the approximate size of the response.
     */
    public int size_response_bytes() {
        int result = 0;
        if (response_body != null) result += response_body.length();
        if (response_content_type != null) result += 12 + response_content_type.length();
        for (ArrayList<String> i : response_headers) result += i.get(0).length() + i.get(1).length();
        return result;
    }

    /**
     * Returns the approximate size of the request and response combined.
     */
    public int size_total_bytes() {
        return size_request_bytes() + size_response_bytes();
    }

    /**
     * Returns message as a string.
     */
    public String toString() {
        try {
            StringWriter out = new StringWriter();
            JsonWriter writer = new JsonWriter(out);
            write(writer).flush();
            return out.toString();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Serializes message using writer.
     */
    public JsonWriter write(JsonWriter writer) {
        try {
            writer.beginArray();
            if (host != null) write(writer, "host", host);
            if (interval_millis != 0) write(writer, "interval", interval_millis);
            if (request_body != null) write(writer, "request_body", request_body);
            if (request_content_type != null) write(writer, "request_header:content-type", request_content_type);
            for (ArrayList<String> i : request_headers) write(writer, "request_header:" + i.get(0), i.get(1));
            if (request_method != null) write(writer, "request_method", request_method);
            for (ArrayList<String> i : request_params) write(writer, "request_param:" + i.get(0), i.get(1));
            if (request_url != null) write(writer, "request_url", request_url);
            if (request_user_agent != null) write(writer, "request_header:user-agent", request_user_agent);
            if (response_body != null) write(writer, "response_body", response_body);
            if (response_code != null) write(writer, "response_code", response_code);
            if (response_content_type != null) write(writer, "response_header:content-type", response_content_type);
            for (ArrayList<String> i : response_headers) write(writer, "response_header:" + i.get(0), i.get(1));
            if (response_time_millis != 0) write(writer, "now", response_time_millis);
            return writer.endArray();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Serializes message detail with string value.
     */
    private void write(JsonWriter writer, String key, long value) throws IOException {
        writer.beginArray();
        writer.value(key);
        writer.value(value);
        writer.endArray();
    }

    /**
     * Serializes message detail with long value.
     */
    private void write(JsonWriter writer, String key, String value) throws IOException {
        writer.beginArray();
        writer.value(key);
        writer.value(value);
        writer.endArray();
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

    private static final Comparator<ArrayList<String>> COMPARATOR = Comparator.comparing(e -> e.get(0));
    private static final Gson GSON = new Gson();
    private static final Type GSON_ARRAYLIST_TYPE = new TypeToken<ArrayList<ArrayList<String>>>() {
    }.getType();

}
