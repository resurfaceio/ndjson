// © 2016-2023 Graylog, Inc.

package io.resurface.ndjson;

/**
 * Processes parsed messages.
 */
public interface MessageProcessor {

    void process(HttpMessage message);

}
