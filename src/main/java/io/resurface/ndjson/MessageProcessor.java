// © 2016-2025 Graylog, Inc.

package io.resurface.ndjson;

/**
 * Processes parsed messages.
 */
public interface MessageProcessor {

    void process(HttpMessage message);

}
