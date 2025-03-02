package BinlaDan.command;

import BinlaDan.exceptions.AlreadyDoneException;
import BinlaDan.exceptions.EmptyStringException;
import BinlaDan.exceptions.MissingDateException;
import BinlaDan.tasks.Deadline;
import BinlaDan.tasks.EventTask;
import BinlaDan.tasks.Task;
import BinlaDan.tasks.Todo;
import BinlaDan.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Holds methods of commands that user can call from CLI
 */
public class Command {
    static final public String ADD_TODO_COMMAND = "todo";
    static final public String ADD_DEADLINE_COMMAND = "deadline";
    static final public String ADD_EVENT_COMMAND = "event";
    static final public String LIST_COMMAND = "list";
    static final public String DONE_COMMAND = "done";
    static final public String UNDONE_COMMAND = "undone";
    static final public String DELETE_COMMAND = "delete";
    static final public String SAVE_COMMAND = "save";
    static final public String FIND_COMMAND = "find";

    /**
     * searches for tasks containing keyword in any fields
     * then displays a filtered list containing said tasks
     * if unable to find any display an error message
     *
     * @param receivedText entire command input received
     */
    public static void executeFind(String receivedText) {
        String[] keywords = Parser.parseKeywords(receivedText);

        try {
            ArrayList<Task> filteredList = TaskList.searchForTaskWithKeyword(keywords);
            System.out.println("Found tasks:");
            Ui.printTaskListView(filteredList);
            TaskList.selectFilteredList(); //toggle selected list to filtered so that done, delete undone affects produced results


        } catch (EmptyStackException e) {
            System.out.println("There seems to be no related targets");
        }

    }

    /**
     * saves current taskList to file in filepath
     * contents are parsed to a more efficient form before saved
     */
    public static void executeSave() {

        try {
            Storage.saveTasksToFile();
            System.out.println("File successfully saved!");
            Ui.printLineDivider();
            Storage.printTaskFile();
            Ui.printLineDivider();
            System.out.println("Impressive Brother! Now we won't forget our targets!");

        } catch (IOException e) {
            System.out.println("Standby while I look for the file ...");
            Storage.checkAndCreateTaskFile();
            System.out.println("try again brother");

        }

    }


    /**
     * Marks a particular task as done
     * if unable to mark done display a unique error message
     *
     * @param receivedText entire command input received
     */
    public static void executeMarkAsDone(String receivedText) {
        try {
            int taskNumber = Parser.getTaskNumber(receivedText);
            TaskList.markAsDone(taskNumber);
            Ui.printLineDivider();
            System.out.println("Well Done Brother! The Resistance thanks you");
            TaskList.displayList();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Ui.printLineDivider();
            System.out.println("You donkey! What have you done? This isn't one of our targets ");
            Ui.printLineDivider();
        } catch (AlreadyDoneException e) {
            Ui.printLineDivider();
            System.out.println("You Idiot! This has mission has already been completed! What do you mean you just completed it?");
            Ui.printLineDivider();
        }
    }

    /**
     * Marks a particular task as undone
     * if unable to mark undone display a unique error message
     *
     * @param receivedText entire command input received
     */
    public static void executeMarkAsUndone(String receivedText) {
        try {
            int taskNumber = Parser.getTaskNumber(receivedText);
            TaskList.markAsUndone(taskNumber);
            Ui.printLineDivider();
            System.out.println("The resistance will not allow you to make anymore mistakes. Complete your mission now!");
            TaskList.displayList();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Ui.printLineDivider();
            System.out.println("You donkey! Are you crazy? This isn't one of our targets ");
            Ui.printLineDivider();
        } catch (AlreadyDoneException e) {
            Ui.printLineDivider();
            System.out.println("You must have smoked too much Shisha! This has mission has not been completed!");
            Ui.printLineDivider();
        }
    }

    /**
     * deletes a task from taskList
     * if unable to delete display a unique error message
     *
     * @param receivedText entire command input received
     */
    public static void executeDeleteTask(String receivedText) {
        try {
            int taskNumber = Parser.getTaskNumber(receivedText);
            Task task = TaskList.deleteTask(taskNumber);
            System.out.print("Target removed: ");
            System.out.println(task);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Are you stupid? This Task doesn't exist!");
        }
    }

    /**
     * displays entire taskList as indexed list
     * if unable, display a unique error message
     */
    public static void executeDisplayList() {

        try {
            TaskList.displayList();
            TaskList.selectTaskList();


        } catch (EmptyStackException | IndexOutOfBoundsException e) {
            System.out.println("No Targets today!");
            Ui.printLineDivider();
        }
    }

    /**
     * Creates a new todo task
     * if unable to create display a unique error message
     *
     * @param receivedString entire command input received
     */
    public static void executeAddTodo(String receivedString) {
        try {
            Todo task = TaskList.addTodoFromString(receivedString);
            Ui.printLineDivider();
            System.out.print("Added new target: ");
            System.out.println(task.getDescription()); // echos what is added to list
            Ui.printUpdateListText();
        } catch (IndexOutOfBoundsException | EmptyStringException e) {
            System.out.println("Todo..... Todo what? Tell me now!");
        }

    }

    /**
     * Creates a new event task
     * if unable to create display a unique error message
     *
     * @param receivedString entire command input received
     */
    public static void executeAddEvent(String receivedString) {
        try {
            EventTask task = TaskList.addEventFromString(receivedString);
            Ui.printLineDivider();
            System.out.print("Added new Campaign: ");
            System.out.println(task.getDescription()); // echos what is added to list
            System.out.print("Campaign duration: ");
            System.out.println(task.getStartTimeAsString() + " to " + task.getEndTimeAsString());
            Ui.printUpdateListText();
        } catch (MissingDateException e) {
            System.out.println("You imbecile your event details are missing! use \"event {task} /from {start time} /to {end time}\"  to specify.");
            System.out.println("try again, don't mess up.");

        } catch (EmptyStringException e) {
            System.out.println("Brother please... indicate your task");
        }

    }

    /**
     * Creates a new deadline task
     * if unable to create display a unique error message
     *
     * @param receivedString entire command input received
     */
    public static void executeAddDeadline(String receivedString) {
        try {
            Deadline task = TaskList.addDeadlineFromString(receivedString);
            Ui.printLineDivider();
            System.out.print("Added new Mission: ");
            System.out.println(task.getDescription()); // echos what is added to list
            System.out.print("Mission deadline: ");
            System.out.println(task.getDeadlineAsString());
            Ui.printUpdateListText();
        } catch (EmptyStringException e) {
            System.out.println("What is your Task? Tell me Now! Don't you dare leave it empty again.");
        } catch (MissingDateException e) {
            System.out.println("what is the deadline? use \"deadline {task} /by {deadline}\"  to specify.");
            System.out.println("try again, don't mess up.");
        }

    }
}
