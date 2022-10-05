# resurfaceio-ndjson
Readers &amp; writers for Resurface NDJSON files

This Java library makes it easy to parse or generate NDJSON payloads, streams, or compressed files in
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

⚠️ We publish our official binaries on [CloudSmith](https://cloudsmith.com) rather than Maven Central,
because CloudSmith is awesome.

Simply add these sections to `pom.xml` to install:

```xml
<dependency>
    <groupId>io.resurface</groupId>
    <artifactId>resurfaceio-ndjson</artifactId>
    <version>3.3.3</version>
</dependency>
```

```xml
<repositories>
    <repository>
        <id>resurfacelabs-public</id>
        <url>https://dl.cloudsmith.io/public/resurfacelabs/public/maven/</url>
        <releases>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </releases>
    </repository>
</repositories>
```


## Usage

This library is used by the Resurface database, for parsing messages from loggers, and for writing export files.

This library is also used by the open source [importer](https://github.com/resurfaceio/importer) utility, which reads 
compressed NDJSON files and loads them into a remote Resurface database. This is a great example of reading and processing 
NDJSON files in Resurface format, and is completely open source.

---
<small>&copy; 2016-2022 <a href="https://resurface.io">Resurface Labs Inc.</a></small>
