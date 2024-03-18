# resurfaceio-ndjson
Readers &amp; writers for Resurface NDJSON files

This open source Java library makes it easy to parse or generate NDJSON payloads, streams, or compressed files in
[Resurface JSON format](https://resurface.io/json.html). This library can be used to parse messages from any
compatible logger, as well as parsing NDJSON files exported from the Resurface database. This library also
provides a write interface to generate compatible NDJSON files or payloads programmatically.

[![CodeFactor](https://www.codefactor.io/repository/github/resurfaceio/ndjson/badge)](https://www.codefactor.io/repository/github/resurfaceio/ndjson)
[![License](https://img.shields.io/github/license/resurfaceio/ndjson)](https://github.com/resurfaceio/ndjson/blob/v3.6.x/LICENSE)
[![Contributing](https://img.shields.io/badge/contributions-welcome-green.svg)](https://github.com/resurfaceio/ndjson/blob/v3.6.x/CONTRIBUTING.md)

## Usage

This library was originally designed for use by the Resurface database, for parsing messages from loggers, and for writing export files.

The test cases included with this project include examples of generating NDJSON files, and parsing JSON messages in Resurface and IBM dialects.

This library is also used by the [importer](https://github.com/resurfaceio/importer) utility, which reads
compressed NDJSON files and loads them into a remote Resurface database.

This library is also used by the [simulator](https://github.com/resurfaceio/simulator) utility, which generates
simulated batches of NDJSON messages and sends them to a remote Resurface database.

## Dependencies

* Java 17
* [google/gson](https://github.com/google/gson)

## Installing with Maven

⚠️ We publish our official binaries on [CloudSmith](https://cloudsmith.com) rather than Maven Central,
because CloudSmith is awesome.

Simply add these sections to `pom.xml` to install:

```xml
<dependency>
    <groupId>io.resurface</groupId>
    <artifactId>resurfaceio-ndjson</artifactId>
    <version>3.6.4</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

```xml
<repositories>
    <repository>
        <id>resurfaceio-public</id>
        <url>https://dl.cloudsmith.io/public/resurfaceio/public/maven/</url>
        <releases>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </releases>
    </repository>
</repositories>
```

---
<small>&copy; 2016-2024 <a href="https://resurface.io">Graylog, Inc.</a></small>
