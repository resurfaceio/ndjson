// © 2016-2023 Resurface Labs Inc.

package io.resurface.ndjson;

/**
 * Processes raw lines.
 */
public interface LineProcessor {

    void process(String line);

}
