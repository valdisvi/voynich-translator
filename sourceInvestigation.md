## Inherited code initial issues
- Voynich.jar does not have the same source code as the one that has been commited. 
- The commited source code can not be built into a working application. 
- Compilation of the source code returns numerous errors in the console.
- Compilation with Eclipse Neon, IntelliJ IDEA or NetBeans gives the same negative results.
- Voynich.jar can only be executed from the terminal. 
- After the Voynich.jar file has been executed once, in next executions the transliterations tables are not loaded unless VoynichData folder is not deleted manually after every launch. 
:racehorse:
- Only one half of the first quire from 20 quires of the manuscript could be loaded into the application, the rest were completely unsupported.
- Java bug prevented deletion of added tables on Windows.

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
