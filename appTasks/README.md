# appTaches - Java task manager

This project is a simple task manager that allows the user to manage a to-do list. In particular, it allows users to add tasks, mark tasks as completed, delete all completed tasks and display a list of all tasks.

## Introduction
This project was carried out as part of the SDA (Advanced Data Structure) course at HEG-Neuchâtel. Its aim is to put into practice the basic concepts of object-oriented programming in Java. This application offers the following functionalities: 
1. Add a new task
2. Mark a task as completed
3. Delete all completed tasks
4. Display list of all tasks

## Project structure
- **ch.heg.ig.sda.app**: contains the main code of the appTaches application
  - Main.java : contains the main() method used to launch the application
  - TaskView.java : contains the TaskView class, which displays the application menu and manages user input
  - TaskInput.java : contains the TaskInput class for managing user input 
- **ch.heg.ig.sda.service**: provides a service layer for task management
  - ITaskService : defines the task management contract
  - TaskServiceImpl: implements ITaskService and interacts with TaskManager
  - TaskServiceException: exception handling for the service layer
- **ch.heg.ig.sda.business**: contains business logic for tasks
  - Task: represents a single task with a description and status
  - TaskManager: manages tasks such as addition, status and deletion
  - RecurrentTask: is a subclass of Task and represents a recurring task
  - BusinessTaskException: exception handling for the business layer

## Features
1. Add new task: select 1 and enter task description
2. Mark a task as completed: select 2, and enter the number of the task to be marked as completed.
3. Delete all completed tasks: select 3
4. Display list of all tasks: select 4
5. Quit application: select 5

## Running the project
1. Clone project
2. Open project in an IDE (IntelliJ, Eclipse, etc.)
3. Import the ConsoleMenuLibrary.jar library (located in the lib folder) into the project (in IntelliJ: File > Project Structure > Libraries > + > Java > select the ConsoleMenuLibrary.jar file).
4. Run the Main.java class
5. Follow the instructions displayed in the console
6. Enjoy!
