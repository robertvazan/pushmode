[![Maven Central](https://img.shields.io/maven-central/v/com.machinezoo.pushmode/pushmode)](https://search.maven.org/artifact/com.machinezoo.pushmode/pushmode)
[![Build Status](https://travis-ci.com/robertvazan/pushmode.svg?branch=master)](https://travis-ci.com/robertvazan/pushmode)
[![Coverage Status](https://codecov.io/gh/robertvazan/pushmode/branch/master/graph/badge.svg)](https://codecov.io/gh/robertvazan/pushmode)

# PushMode HTML streaming for Java #

PushMode is a server-side Java library that streams web app's HTML output down to the browser while user's actions are streamed back to the server. The only code the browser ever sees is a tiny JavaScript file. Application effectively runs all on the server, but interactivity is almost the same as if it was running in the browser. This architecture has its [advantages](https://pushmode.machinezoo.com/architecture-comparison). PushMode is reactive by default as it is based on [Hookless](https://hookless.machinezoo.com/).

## Status ##

Class-level progress is tracked using [Stagean annotations](https://stagean.machinezoo.com/). Read-only streaming of HTML to the client is fairly stable. Streaming of user input to the server is early alpha. Browser APIs (e.g., history manipulation) aren't implemented at all.

## Download ##

PushMode is available from [Maven Central](https://search.maven.org/artifact/com.machinezoo.pushmode/pushmode). Further setup instructions are on the [website](https://pushmode.machinezoo.com/). PushMode is distributed under [Apache License 2.0](LICENSE).

## Documentation ##

You can use [javadoc](https://pushmode.machinezoo.com/javadoc/overview-summary.html) for reference. Some classes are not documented, so you might have to peek in the [source code](src/main/java/com/machinezoo/pushmode). [Website](https://pushmode.machinezoo.com/) might contains some information.

## Contribute ##

Bug reports, feature suggestions, and pull requests are welcome. For major changes, open an issue first to discuss the change.

* Sources: [GitHub](https://github.com/robertvazan/pushmode), [Bitbucket](https://bitbucket.org/robertvazan/pushmode)
* Issues: [GitHub](https://github.com/robertvazan/pushmode/issues), [Bitbucket](https://bitbucket.org/robertvazan/pushmode/issues)
* License: [Apache License 2.0](LICENSE)

