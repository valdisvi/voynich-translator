# Voynich Transliterator

### Overview
This project's is for a Voynich Manuscript transliteration application. It is used to simplify and automate the transliteration process by quickly getting the transcription of the page and applying different table transliterations to text generated from user input or an online source.
Users can create custom transliteration tables and apply their rules to the generated text, as well as use the provided default tables.
The web source for getting transcribed pages is http://voynich.nu/

### Features

- Text transliteration
- Custom transliteration tables
- Load transcribed pages from a web source
  - Pick different transcription versions
  - Supported quires - from 1 and only "r" marked pages, to all (1-20) and both "r" and "v" marked pages
- Simple user manual from Help button

![Screenshot](https://github.com/valdisvi/voynich-translator/blob/master/src/main/resources/voynich-translator.png)

## Getting Started

1. Ensure your computer supports Java runtime
- [On Windows or Mac](https://www.java.com/en/download/help/version_manual.xml)
- [On Ubuntu](https://www.howtogeek.com/191427/how-to-find-out-if-java-is-installed-in-ubuntu-and-how-to-install-it/)

2. Download and save [voynich-translator.jar](https://github.com/valdisvi/voynich-translator/blob/master/bin/voynich-translator.jar?raw=true)
3. Run application with command in the terminal from Downloads folder
```
java -jar voynich-translator.jar
```
For instructions on how to use this application please check project folder helpFiles, or run the application and click on button "Help"

## Development

1. Set up necessary tools:
  - [Eclipse IDE](https://www.eclipse.org/) (versions Neon and Oxygen)
  - [WindowBuilder plugin](https://www.eclipse.org/windowbuilder/) for Eclipse
  - [JUnit4](https://junit.org/junit4/) for tests
4. Clone project
```
git clone https://github.com/valdisvi/voynich-translator.git
cd voynich-translator
```
5. Build project for Eclipse:
```
mvn clean compile eclipse:eclipse
```
6. Build executable *.jar file:
```
mvn assembly:single
```
7. Run newly built jar file:
```
cd target
java -jar voynich-translator.jar
```

## Authors
See the list of [contributors](https://github.com/valdisvi/voynich-translator/contributors)
who participated in [this project](https://github.com/valdisvi/voynich-translator).

## Dependencies
Project is built with [Maven](https://en.wikipedia.org/wiki/Apache_Maven)

Application uses source text from [voynich.nu.](http://www.voynich.nu/q01/)

## License
Source code of this project is licenced under [LGPL 3.0 or later](https://spdx.org/licenses/LGPL-3.0-or-later.html) license.

Documentation and other materials are licenced under [Creative Commons — Attribution 3.0](https://spdx.org/licenses/CC-BY-3.0.html) license.

