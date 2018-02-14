# Inherited code initial issues
- Voynich.jar does not have the same source code as the one that has been commited. 
- The commited source code can not be built into a working application. 
- Compilation of the source code returns numerous errors in the console.
- Compilation with Eclipse Neon, IntelliJ IDEA or NetBeans gives the same results.
- Voynich.jar can only be executed from terminal. The execution creates a VoynichData folder.
- After the Voynich.jar file has been executed once,
in next execusions transliterations tables do not apper if the VoynichData folder is not deleted after every launch. 
:racehorse:

#### Source code fixes that were implemented (13.02):
- updated/fixed markup in add.fxml and application.fxml
- added initial .properties file and directory creation in controller
- implemented proper usage of files in controller
- fixed transliteration table loading and selection 
  - additional exception handling and fixing existing loading methods for comboBox and table
- fixed adding, creating and deleting transliteration tables 
  - fixed paths and exc. handling for deletion
- fixed text transliteration  (both web text and input)
  - fixed paths and exc. handling for selecting transliteration tables

