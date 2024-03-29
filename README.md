<!--- Generated by scripts/configure.py --->
[![SWUbanner](https://raw.githubusercontent.com/vshymanskyy/StandWithUkraine/main/banner2-direct.svg)](https://github.com/vshymanskyy/StandWithUkraine/blob/main/docs/README.md)

# PushMode

[![Maven Central](https://img.shields.io/maven-central/v/com.machinezoo.pushmode/pushmode)](https://central.sonatype.com/artifact/com.machinezoo.pushmode/pushmode)
[![Build status](https://github.com/robertvazan/pushmode/workflows/build/badge.svg)](https://github.com/robertvazan/pushmode/actions/workflows/build.yml)
[![Test coverage](https://codecov.io/gh/robertvazan/pushmode/branch/master/graph/badge.svg)](https://codecov.io/gh/robertvazan/pushmode)

PushMode is a server-side Java library that streams web app's HTML output down to the browser
while user's actions are streamed back to the server.
The only code the browser ever sees is a tiny JavaScript file.
Application effectively runs all on the server,
but interactivity is almost the same as if it was running in the browser.
This architecture has its [advantages](https://pushmode.machinezoo.com/architecture-comparison).
PushMode is reactive by default as it is based on [Hookless](https://hookless.machinezoo.com/).

More on [homepage](https://pushmode.machinezoo.com/).

## Status

Experimental. [Stagean](https://stagean.machinezoo.com/) is used to track progress on class and method level.
Read-only streaming of HTML to the client is fairly stable. Streaming of user input to the server is early alpha.
Browser APIs (e.g., history manipulation) aren't implemented at all.

## Getting started

See [homepage](https://pushmode.machinezoo.com/).

## Documentation

* [Homepage](https://pushmode.machinezoo.com/)
* [Javadoc](https://pushmode.machinezoo.com/javadoc/com.machinezoo.pushmode/module-summary.html)

Some APIs are undocumented. You might have to peek in the [source code](src/main/java/com/machinezoo/pushmode).

## Feedback

Bug reports and pull requests are welcome. See [CONTRIBUTING.md](CONTRIBUTING.md).

## License

Distributed under [Apache License 2.0](LICENSE).
