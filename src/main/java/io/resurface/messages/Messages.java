// © 2016-2020 Resurface Labs Inc.

package io.resurface.messages;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Factory and utility methods for logger messages.
 */
public class Messages {

    /**
     * Returns reader to ndjson file.
     */
    public static BufferedReader build_reader(String file) {
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
     * Returns writer to ndjson file.
     */
    public static BufferedWriter build_writer(String file) {
        try {
            if (file.endsWith(".ndjson")) {
                return new BufferedWriter(new FileWriter(file));
            } else if (file.endsWith(".ndjson.gz")) {
                return new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(file))));
            } else {
                throw new IllegalArgumentException("File is not .ndjson or .ndjson.gz format");
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Iterates all lines in ndjson file without parsing first.
     */
    public static void iterate(String file, LineProcessor processor) {
        try (BufferedReader r = build_reader(file)) {
            String line;
            while ((line = r.readLine()) != null) processor.process(line);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Parses and processes all messages in ndjson file.
     */
    public static void parse(String file, MessageProcessor processor) {
        iterate(file, line -> processor.process(new HttpMessage(line)));
    }

}
