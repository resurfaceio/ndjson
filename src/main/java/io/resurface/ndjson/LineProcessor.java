// © 2016-2023 Graylog, Inc.

package io.resurface.ndjson;

/**
 * Processes raw lines.
 */
public interface LineProcessor {

    void process(String line);

}
