// © 2016-2023 Resurface Labs Inc.

package io.resurface.ndjson;

/**
 * Processes parsed messages.
 */
public interface MessageProcessor {

    void process(HttpMessage message);

}
