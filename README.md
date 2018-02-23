
# Voynich Transliterator

### Overview
This project's source code is for a Voynich Manuscript transliteration application. It is used to simplify and automate the transliteration process by quickly getting the transcription of the page and applying different table transliterations to text generated from user input or an online source. 
Users can create custom transliteration tables and apply their rules to the generated text, as well as use the provided default tables. 
The web source for getting transcribed pages is http://voynich.nu/

### Features

- Text transliteration
- Font change (Manuscript/Latin)
- Custom transliteration tables 
- Load transcribed pages from a web source 
  - Pick different transcription versions

### Improvements 

- More intuitive, user-friendlier design;
- Possible to get transcriptions of most quires and pages of the manuscript;
  - Additional buttons and page support added;
- User manual added to the application;
- No more JavaFX dependencies.

For improvements and fixes of an inherited JavaFX dependant project, please refer to [Source Investigation.md](https://github.com/beatrise/voynich-translator/blob/master/sourceInvestigation.md) file

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. ?See deployment for notes on how to deploy the project on a live system.?

### Prerequisites
To run inherited project class files with JavaFX dependencies you have to install Java FX libraries:
```
sudo apt-get install openjfx openjfx-source
```
For development use [e(fx)clipse](https://www.eclipse.org/efxclipse/install.html) or other tool, which supports Java FX development environment.

### Installing
Import GitHub Project into Eclipse [how-to](https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse)

or download archive file of the project and import it into Eclipse [how-to](http://help.eclipse.org/kepler/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Ftasks%2Ftasks-importproject.htm)
## Deployment

To deploy this application download the NAME?.jar file to you computer. 
And execute like this:
```
cd directoryOfJarFile
java -jar NAME?.jar
```
For instructions on how to use this application please check project folder helpFiles, or run the application and click on button "Help"
## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

There are no versions released.
For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

See the list of [contributors](https://github.com/beatrise/voynich-translator/contributors) who participated in this project.
[Source code](https://github.com/valdisvi/voynich-translator)
To see our notes on it go to [Source Investigation](https://github.com/beatrise/voynich-translator/blob/master/sourceInvestigation.md)

## Dependencies
This application uses source text from [voynich.nu.](http://www.voynich.nu/q01/)

## Developed using
[Eclipse IDE](https://www.eclipse.org/) (versions Neon and Oxygen)
[WindowBuilder plugin](https://www.eclipse.org/windowbuilder/)  for Eclipse
[JUnit 5](https://junit.org/junit5/) for tests

## License