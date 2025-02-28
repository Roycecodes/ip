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
import java.util.EmptyStackException;



public class Command {
    static final public String ADD_TODO_COMMAND = "todo";
    static final public String ADD_DEADLINE_COMMAND = "deadline";
    static final public String ADD_EVENT_COMMAND = "event";
    static final public String LIST_COMMAND = "list";
    static final public String DONE_COMMAND = "done";
    static final public String UNDONE_COMMAND = "undone";
    static final public String DELETE_COMMAND = "delete";
    static final public String SAVE_COMMAND = "save";


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

    public static void executeMarkAsDone(String receivedText) {
        try {
            int taskNumber = Parser.getTaskNumber(receivedText);
            TaskList.markAsDone(taskNumber);
            Ui.printLineDivider();
            System.out.println("Well Done Brother! The Resistance thanks you");
            TaskList.displayList();
        }catch (IndexOutOfBoundsException | NullPointerException e) {
            Ui.printLineDivider();
            System.out.println("You donkey! What have you done? This isn't one of our targets ");
            Ui.printLineDivider();
        } catch (AlreadyDoneException e) {
            Ui.printLineDivider();
            System.out.println("You Idiot! This has mission has already been completed! What do you mean you just completed it?");
            Ui.printLineDivider();
        }
    }

        public static void executeMarkAsUndone (String receivedText) {
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
        public static void executeDeleteTask (String receivedText) {
            try {
                int taskNumber = Parser.getTaskNumber(receivedText);
                Task task = TaskList.deleteTask(taskNumber);
                System.out.print("Target removed: ");
                System.out.println(task);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Are you stupid? This Task doesn't exist!");
            }
        }

        public static void executeDisplayList () {

            try {
                TaskList.displayList();

            } catch (EmptyStackException | IndexOutOfBoundsException e) {
                System.out.println("No Targets today!");
                Ui.printLineDivider();
            }
        }

        public static void executeAddTodo (String receivedString){
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

        public static void executeAddEvent (String receivedString){
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

        public static void executeAddDeadline (String receivedString){
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
