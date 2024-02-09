package io.resurface.ndjson.formats;

import io.resurface.ndjson.HttpMessage;

public interface Initialized {
    void initialize(HttpMessage message);
}