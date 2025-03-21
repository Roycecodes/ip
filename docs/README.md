# Project Binla
This is a project for a CS2113 java project. Here are the instructions to use it!

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Ello from
   /$$$$$$$  /$$$$$$ /$$   /$$ /$$        /$$$$$$                    /$$$$$$$   /$$$$$$  /$$   /$$      
   | $$__  $$|_  $$_/| $$$ | $$| $$       /$$__  $$                  | $$__  $$ /$$__  $$| $$$ | $$      
   | $$  \ $$  | $$  | $$$$| $$| $$      | $$  \ $$                  | $$  \ $$| $$  \ $$| $$$$| $$      
   | $$$$$$$   | $$  | $$ $$ $$| $$      | $$$$$$$$                  | $$  | $$| $$$$$$$$| $$ $$ $$      
   | $$__  $$  | $$  | $$  $$$$| $$      | $$__  $$                  | $$  | $$| $$__  $$| $$  $$$$      
   | $$  \ $$  | $$  | $$\  $$$| $$      | $$  | $$                  | $$  | $$| $$  | $$| $$\  $$$      
   | $$$$$$$/ /$$$$$$| $$ \  $$| $$$$$$$$| $$  | $$                  | $$$$$$$/| $$  | $$| $$ \  $$      
   |_______/ |______/|__/  \__/|________/|__/  |__/                  |_______/ |__/  |__/|__/  \__/      
   ```
**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Features

### Viewing help : help
Shows a message listing available commands.  
If a command is added after the word help it displays text on how to use command.

Format: `help, help {command}`

e.g. `help, help todo, help delete`

### Adding a task : todo/deadline/event
Adds a task to task collection.  
__todo__ adds a basic task that can be checked done or undone  
__deadline__ adds a todo task that contains a deadline  
__event__ adds a todo task that contains a start and end time  
*dates can be saved as LocalDate in the form of YYYY-MM-DD or else as a String*

Format: `todo {task}, deadline {task} /by {deadline}, event {task} /from {start time} /to {end time}`

e.g. `todo play fifa, deadline fly plane /by 2001-09-11, event party /from today /to tomorrow`

### Listing all tasks : list
Displays task list

Format: `list`

e.g. `list`

### Locating tasks by keyword: find
Searches task list for keyword  
Displays a filtered list containing tasks with keyword  
*able to display by task type as well by typing task type after find*

Format: `find {keyword/date}, find {task type}`

e.g. `find fifa, find 2001, find event`


### marking and unmarking a task as done : done/undone
Marks a task as done or undone  
*follows the index of last displayed list (able to be used after using command __find__)*

Format: `done {index}, undone {index}`

e.g. `done 1, undone 2`


### Deleting a task : delete
Deletes a task by index
Able to delete all tasks if keyword all is used after delete
*follows the index of last displayed list (able to be used after using command __find__)*

Format: `delete {index}, delete all`

e.g. `delete 1, delete all`

### Saving the data : save
Saves task list into a local folder in file path *data/localFileBackups.txt*  
*if folder does not exist one will be created*  
*the program auto saves as you exit the program*

Format: `save`

e.g. `save`

### Exiting the program : bye
Exits the program
Auto saves Tasks

Format: `bye`

e.g. `bye`


