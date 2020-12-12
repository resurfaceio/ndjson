// © 2016-2020 Resurface Labs Inc.

package io.resurface.messages;

/**
 * Processes parsed messages.
 */
public interface MessageProcessor {

    void process(HttpMessage message);

}
