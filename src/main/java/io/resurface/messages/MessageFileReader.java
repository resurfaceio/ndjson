// © 2016-2022 Resurface Labs Inc.

package io.resurface.messages;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Reads from compressed NDJSON file.
 */
public class MessageFileReader implements AutoCloseable, Closeable {

    /**
     * Opens file for reading.
     */
    public MessageFileReader(String file) {
        if (!file.endsWith(".ndjson.gz"))
            throw new IllegalArgumentException("File is not .ndjson.gz format");
        if (!new File(file).exists())
            throw new IllegalArgumentException("File not found: " + file);

        this.file = file;
    }

    /**
     * Closes file.
     */
    public void close() {
        // do nothing
    }

    /**
     * Iterates all lines in file without parsing first.
     */
    public void iterate(LineProcessor processor) {
        try (FileInputStream fos = new FileInputStream(file)) {
            try (GZIPInputStream gos = new GZIPInputStream(fos)) {
                try (InputStreamReader isr = new InputStreamReader(gos)) {
                    try (BufferedReader br = new BufferedReader(isr)) {
                        String line;
                        while ((line = br.readLine()) != null) processor.process(line);
                    }
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Parses and processes all messages in file.
     */
    public void parse(MessageProcessor processor) {
        iterate(line -> processor.process(new HttpMessage(line)));
    }

    private final String file;

}
