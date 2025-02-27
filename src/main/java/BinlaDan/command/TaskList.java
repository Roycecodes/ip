package BinlaDan.command;

import BinlaDan.exceptions.AlreadyDoneException;
import BinlaDan.exceptions.EmptyStringException;
import BinlaDan.exceptions.MissingDateException;
import BinlaDan.tasks.Deadline;
import BinlaDan.tasks.EventTask;
import BinlaDan.tasks.Task;
import BinlaDan.tasks.Todo;
import BinlaDan.ui.Ui;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class TaskList {
    static final protected int INDEX_OFFSET = 1;
    static final private ArrayList<Task> taskList = new ArrayList<>();
    static private int listSize = 0;


    public static int getListSize() {
        return listSize;
    }


    public static String generateTaskListString() {
        try {
            String returnedText = "Current saved Targets: \n";
            for (int i = 0; i < listSize; i++) {

                returnedText += Parser.getTaskAsSavedFormat(taskList.get(i)); //call parser function that formats tasks
                returnedText += ("\n"); //newline
            }
            return returnedText;
        } catch (NullPointerException e) {
            System.out.println("error");

        }
        return "";
    }


    static Todo addTodoFromString(String receivedText) throws EmptyStringException {
        String description = Parser.parseTodo(receivedText);

        return addTodo(description, false);


    }

    public static Todo addTodo(String description, Boolean isDone) {
        Todo todoTask = new Todo(description, isDone); //adds description as normal task
        taskList.add(todoTask); // add new todotask to list array
        listSize += 1;          //increases index to track size
        return todoTask;
    }

    public static Deadline addDeadlineFromString(String receivedText) throws EmptyStringException, MissingDateException {

        String[] parsedDeadline = Parser.parseDeadline(receivedText);
        String description = parsedDeadline[0];
        String deadline = parsedDeadline[1];
        return addDeadline(description, deadline, false);


    }

    public static Deadline addDeadline(String description, String deadline, Boolean isDone) {
        Deadline deadlineTask = new Deadline(description, deadline, isDone); //adds description and deadline as a deadline task
        taskList.add(deadlineTask); //add deadline task to array list
        listSize += 1;          //increases index
        return deadlineTask;
    }

    static EventTask addEventFromString(String receivedText) throws EmptyStringException, MissingDateException {

        String[] parsedEvent = Parser.parseEvent(receivedText);
        String description = parsedEvent[0];
        String startTime = parsedEvent[1];
        String endTime = parsedEvent[2];
        return addEvent(description, startTime, endTime, false);


    }

    public static EventTask addEvent(String description, String startTime, String endTime, Boolean isDone) {
        EventTask eventTask = new EventTask(description, startTime, endTime, isDone); //adds description as normal task
        taskList.add(eventTask); // add event task to array list
        listSize += 1;          //increases index
        return eventTask;
    }

    static void displayList() throws EmptyStackException {
        if (listSize == 0) {
            throw new EmptyStackException();
        }
        Ui.printLineDivider();
        System.out.println("Current targets: ");
        Ui.printTaskListView(taskList);
        Ui.printLineDivider();
    }



    static void markAsDone(int index) throws AlreadyDoneException {
        Task task = taskList.get(index - INDEX_OFFSET);
        task.setDone(true); // call setDone from Task class
    }

    static void markAsUndone(int index) throws AlreadyDoneException {

        Task task = taskList.get(index - 1);
        task.setDone(false); // call setDone from Task class


    }

    static Task deleteTask(int index) {

        Task task = taskList.get(index - INDEX_OFFSET); //save task for printing later
        taskList.remove(task); // try to remove from list
        listSize -= 1;
        return task;


    }

}