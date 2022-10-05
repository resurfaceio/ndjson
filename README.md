# resurfaceio-ndjson
Readers &amp; writers for Resurface NDJSON files

This library makes it easy to parse or generate NDJSON payloads, streams, or compressed files in
[Resurface JSON format](https://resurface.io/json.html). This library can be used to parse messages from any
compatible logger, as well as parsing NDJSON files exported from the Resurface database. This library also
provides a write interface to generate compatible NDJSON files or payloads programmatically.

[![CodeFactor](https://www.codefactor.io/repository/github/resurfaceio/ndjson/badge)](https://www.codefactor.io/repository/github/resurfaceio/ndjson)
[![License](https://img.shields.io/github/license/resurfaceio/ndjson)](https://github.com/resurfaceio/ndjson/blob/v3.3.x/LICENSE)
[![Contributing](https://img.shields.io/badge/contributions-welcome-green.svg)](https://github.com/resurfaceio/ndjson/blob/v3.3.x/CONTRIBUTING.md)

## Dependencies

* Java 11
* GSON (not included)

## Installing with Maven

Add these sections to `pom.xml`:

```xml
<dependency>
    <groupId>io.resurface</groupId>
    <artifactId>resurfaceio-ndjson</artifactId>
    <version>3.3.3</version>
</dependency>
```

```xml
<distributionManagement>
    <repository>
        <id>cloudsmith</id>
        <url>https://maven.cloudsmith.io/resurfacelabs/public/</url>
    </repository>
</distributionManagement>
```

## Usage

This library is used by the Resurface database, for parsing log messages from loggers, and for generating export files.

This library is also used by the open source [importer](https://github.com/resurfaceio/importer) utility, which reads 
compressed NDJSON files and loads them into a remote Resurface database. This is a great example of reading and processing 
NDJSON files in Resurface format, and is completely open source.