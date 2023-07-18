// © 2016-2023 Graylog, Inc.

package io.resurface.ndjson;

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
        if (new File(file).exists())
            throw new IllegalArgumentException("File already exists: " + file);

        try {
            fos = new FileOutputStream(file);
            gos = new GZIPOutputStream(fos);
            osw = new OutputStreamWriter(gos);
            writer = new BufferedWriter(osw);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private final FileOutputStream fos;
    private final GZIPOutputStream gos;
    private final OutputStreamWriter osw;
    private final BufferedWriter writer;

    /**
     * Closes file.
     */
    public void close() {
        try {
            writer.close();
            osw.close();
            gos.close();
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
            osw.flush();
            gos.flush();
            fos.flush();
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

}
