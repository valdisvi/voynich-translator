# Voynich Transliterator

### Overview
This project's source code is for a Voynich Manuscript transliteration application. It is used to simplify and automate the transliteration process by quickly getting the transcription of the page and applying different table transliterations to text generated from user input or an online source. 
Users can create custom transliteration tables and apply their rules to the generated text, as well as use the provided default tables. 
The web source for getting transcribed pages is http://voynich.nu/

### Features

- Text transliteration
- Custom transliteration tables
- Load transcribed pages from a web source
  - Pick different transcription versions

![Screenshot](https://github.com/valdisvi/voynich-translator/blob/master/src/main/resources/voynich-translator.png)

### General improvements

- More intuitive, user-friendlier design
- Possible to get transcriptions of most quires and pages of the manuscript
  - Additional buttons and page support added
- Documentation added
- User manual added to the application
- No more JavaFX dependencies

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. ?See deployment for notes on how to deploy the project on a live system.?

For simplifying Swing GUI development we recommend you use [WindowBuilder plugin](https://www.eclipse.org/windowbuilder/) or a similar tool.


## Deployment

To deploy this application download the voynich-translator.jar file to you computer.
And execute like this:
```
git clone https://github.com/valdisvi/voynich-translator.git
cd voynich-translator
mvn mvn clean compile assembly:single
cd target
java -jar voynich-translator.jar
```
Or to deploy directly with Java JDK Runtime Environment on Linux, change the .jar file permissions with terminal command:
```
$chmod +x voynich-translator.jar
```

For instructions on how to use this application please check project folder helpFiles, or run the application and click on button "Help"


## Versioning

There are no versions released.
For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

See the list of [contributors](https://github.com/valdisvi/voynich-translator/contributors) who participated in this project.
[Source code](https://github.com/valdisvi/voynich-translator)
To see our notes on it go to [Source Investigation](https://github.com/valdisvi/voynich-translator/blob/master/sourceInvestigation.md)

## Dependencies
This application uses source text from [voynich.nu.](http://www.voynich.nu/q01/)

## Developed using
[Eclipse IDE](https://www.eclipse.org/) (versions Neon and Oxygen)
[WindowBuilder plugin](https://www.eclipse.org/windowbuilder/) for Eclipse
[JUnit4](https://junit.org/junit4/) for tests

## License
Source code of this project is licenced under [LGPL 3.0 or later](https://spdx.org/licenses/LGPL-3.0-or-later.html) license.
Documentation and other materials are licenced under [Creative Commons — Attribution 3.0](https://spdx.org/licenses/CC-BY-3.0.html) license.

