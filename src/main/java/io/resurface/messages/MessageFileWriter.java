// © 2016-2020 Resurface Labs Inc.

package io.resurface.messages;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * Writes to compressed NDJSON file.
 */
public class MessageFileWriter implements AutoCloseable, Closeable, Flushable {

    /**
     * Opens file for writing.
     */
    public MessageFileWriter(String file) {
        if (!file.endsWith(".ndjson.gz"))
            throw new IllegalArgumentException("File is not .ndjson.gz format");

        // todo throw exception if file already exists?

        try {
            fos = new FileOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(fos)));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Closes file.
     */
    public void close() {
        try {
            writer.flush();
            fos.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Flushes any buffered writes.
     */
    public void flush() {
        try {
            writer.flush();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Writes message to file.
     */
    public void write(HttpMessage message) {
        try {
            writer.write(message.toString());
            writer.newLine();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private final FileOutputStream fos;
    private final BufferedWriter writer;

}
