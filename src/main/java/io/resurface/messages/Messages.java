// © 2016-2020 Resurface Labs Inc.

package io.resurface.messages;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Message factory methods.
 */
public class Messages {

    /**
     * Returns reader to ndjson file.
     */
    public static BufferedReader open(String file) {
        try {
            if (file.endsWith(".ndjson")) {
                return new BufferedReader(new FileReader(file));
            } else if (file.endsWith(".ndjson.gz")) {
                return new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
            } else {
                throw new IllegalArgumentException("File is not .ndjson or .ndjson.gz format");
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Process all lines in ndjson file.
     */
    public static void process(String file, LineProcessor processor) {
        try (BufferedReader reader = open(file)) {
            String line;
            while ((line = reader.readLine()) != null) processor.process(line);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Process all messages in ndjson file.
     */
    public static void process(String file, MessageProcessor processor) {
        process(file, (LineProcessor) line -> processor.process(new HttpMessage(line)));
    }

}
