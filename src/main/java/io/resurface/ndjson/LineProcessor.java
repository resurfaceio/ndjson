// © 2016-2025 Graylog, Inc.

package io.resurface.ndjson;

/**
 * Processes raw lines.
 */
public interface LineProcessor {

    void process(String line);

}
