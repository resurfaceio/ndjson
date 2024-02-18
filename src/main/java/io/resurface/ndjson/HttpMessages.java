// © 2016-2024 Graylog, Inc.

package io.resurface.ndjson;

import java.util.ArrayList;

import static io.resurface.ndjson.HttpMessage.*;

/**
 * Parsing and conversion utilities for message models.
 */
public class HttpMessages {

    /**
     * Formats message object in specific JSON dialect.
     */
    public static String format(HttpMessage msg, String dialect) {
        if ("ibm".equals(dialect)) {
            return new APIConnectMessage(msg).toString();
        } else {
            return msg.toString();
        }
    }

    /**
     * Parses message object from JSON in specified dialect.
     */
    public static HttpMessage parse(String json, String dialect) {
        try {
            HttpMessage m = new HttpMessage();
            if ("ibm".equals(dialect)) {
                GSON.fromJson(json, APIConnectMessage.class).copyTo(m);
            } else {
                ArrayList<ArrayList<String>> details = GSON.fromJson(json, GSON_ARRAYLIST_TYPE);
                for (ArrayList<String> detail : details) m.add(detail);
            }
            return m;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid json message", e);
        }
    }

    /**
     * Parses message object from JSON in default dialect.
     */
    public static HttpMessage parse(String json) {
        return parse(json, null);
    }

}
