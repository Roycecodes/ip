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

/**
 * Represents the array of tasks to be stored by chatbot
 * holds CRUD methods that manipulate the task list
 */
public class TaskList {
    static final protected int INDEX_OFFSET = 1;
    static final private ArrayList<Task> taskList = new ArrayList<>();
    static final private ArrayList<Task> filteredList = new ArrayList<>();
    static private ArrayList<Task> selectedList = new ArrayList<>();
    static private int listSize = 0;

    public static int getListSize() {
        return listSize;
    }

    public static void selectTaskList() {

        selectedList = taskList;

    }

    public static void selectFilteredList() {

        selectedList = filteredList;

    }

    /**
     * checks all tasks in taskList for keywords and populates filteredLists with found tasks
     *
     * @return the filtered list after done
     * @throws EmptyStackException if no tasks are found
     */
    public static ArrayList<Task> searchForTaskWithKeyword(String... keywords) {
        filteredList.clear();

        // If only one keyword and it matches a task type
        if (keywords.length == 1) {
            String keyword = keywords[0].toLowerCase();
            switch (keyword) {
            case "todo":
                for (Task t : taskList) {
                    if (t instanceof Todo) {
                        filteredList.add(t);
                    }
                }
                break;
            case "deadline":
                for (Task t : taskList) {
                    if (t instanceof Deadline) {
                        filteredList.add(t);
                    }
                }
                break;
            case "event":
                for (Task t : taskList) {
                    if (t instanceof EventTask) {
                        filteredList.add(t);
                    }
                }
                break;
            default:
                // If not a task type, proceed with original keyword search
                for (Task t : taskList) {
                    if (checkTaskForKeyword(keywords, t)) {
                        filteredList.add(t);
                    }
                }
            }
        } else {
            // Original keyword search for multiple keywords
            for (Task t : taskList) {
                if (checkTaskForKeyword(keywords, t)) {
                    filteredList.add(t);
                }
            }
        }

        if (filteredList.isEmpty()) throw new EmptyStackException();
        return filteredList;
    }

    /**
     * checks all fields in particular task for keyword
     *
     * @return is true if keyword exists
     */
    private static Boolean checkTaskForKeyword(String[] keywords, Task t) {
        for (String keyword : keywords) {
            keyword = keyword.toLowerCase();
            if (t.getDescription().toLowerCase().contains(keyword)) {

                return true; // Once we've found one keyword match, no need to check others
            }
            if (isDeadlineContainingKey(t, keyword)) {

                return true;

            }
            if (isEventContainingKey(t, keyword)) {
                return true;
            }


        }
        return false;
    }

    /**
     * returns true if tasks deadline contains keyword
     */
    private static boolean isDeadlineContainingKey(Task t, String keyword) {
        return t instanceof Deadline && ((Deadline) t).getDeadlineAsString().toLowerCase().contains(keyword);
    }

    /**
     * returns true if tasks event boundaries contains keyword
     */
    private static boolean isEventContainingKey(Task t, String keyword) {
        return t instanceof EventTask && (((EventTask) t).getStartTimeAsString().toLowerCase().contains(keyword) || ((EventTask) t).getEndTimeAsString().toLowerCase().contains(keyword));
    }

    /**
     * generates whole String of tasks after they have been converted into saved format
     *
     * @return String to be written into storage file
     */
    public static String generateTaskListString() {

        try {
            StringBuilder returnedText = new StringBuilder("Current saved Targets: \n");
            for (int i = 0; i < listSize; i++) {

                returnedText.append(Parser.getTaskAsSavedFormat(taskList.get(i))); //call parser function that formats tasks
                returnedText.append("\n"); //newline
            }
            return returnedText.toString();
        } catch (NullPointerException e) {
            System.out.println("error");

        }
        return "";
    }

    /**
     * accepts user input to call addTodo inorder to create a todo task
     *
     * @return the task that is created
     */
    static Todo addTodoFromString(String receivedText) throws EmptyStringException {
        String description = Parser.parseTodo(receivedText);

        return addTodo(description, false);


    }

    /**
     * creates and adds todo Task to taskList
     *
     * @return the task that is created
     */
    public static Todo addTodo(String description, Boolean isDone) {
        Todo todoTask = new Todo(description, isDone); //adds description as normal task
        taskList.add(todoTask); // add new todotask to list array
        listSize += 1;          //increases index to track size
        return todoTask;
    }

    /**
     * accepts user input as string to call addDeadline inorder to create a deadline task
     *
     * @return the task that is created
     */
    public static Deadline addDeadlineFromString(String receivedText) throws EmptyStringException, MissingDateException {

        String[] parsedDeadline = Parser.parseDeadline(receivedText);
        String description = parsedDeadline[0];
        String deadline = parsedDeadline[1];
        return addDeadline(description, deadline, false);
    }

    /**
     * creates and adds deadline Task to taskList
     *
     * @return the task that is created
     */
    public static Deadline addDeadline(String description, String deadline, Boolean isDone) {
        Deadline deadlineTask = new Deadline(description, deadline, isDone); //adds description and deadline as a deadline task
        taskList.add(deadlineTask); //add deadline task to array list
        listSize += 1;          //increases index
        return deadlineTask;
    }

    /**
     * accepts user input as string to call addEvent inorder to create a event task
     *
     * @return the task that is created
     */
    static EventTask addEventFromString(String receivedText) throws EmptyStringException, MissingDateException {

        String[] parsedEvent = Parser.parseEvent(receivedText);
        String description = parsedEvent[0];
        String startTime = parsedEvent[1];
        String endTime = parsedEvent[2];
        return addEvent(description, startTime, endTime, false);


    }

    /**
     * creates and adds event Task to taskList
     *
     * @return the task that is created
     */
    public static EventTask addEvent(String description, String startTime, String endTime, Boolean isDone) {
        EventTask eventTask = new EventTask(description, startTime, endTime, isDone); //adds description as normal task
        taskList.add(eventTask); // add event task to array list
        listSize += 1;          //increases index
        return eventTask;
    }

    /**
     * displays list
     *
     * @throws EmptyStackException if list is empty
     */
    static void displayList() throws EmptyStackException {
        if (listSize == 0) {
            throw new EmptyStackException();
        }

        Ui.printLineDivider();
        System.out.println("Current targets: ");
        Ui.printTaskListView(taskList);
        Ui.printLineDivider();

    }

    /**
     * displays selected list (either filtered or task)
     *
     * @throws EmptyStackException if list is empty
     */
    static void displaySelectedList() throws EmptyStackException {
        if (listSize == 0) {
            throw new EmptyStackException();
        }

        Ui.printLineDivider();
        System.out.println("selected targets: ");
        Ui.printTaskListView(selectedList);
        Ui.printLineDivider();


    }

    /**
     * marks task as done
     *
     * @param index is index of the target task in taskList
     * @throws AlreadyDoneException if it has already been marked
     */
    static void markAsDone(int index) throws AlreadyDoneException {
        Task task = selectedList.get(index - INDEX_OFFSET);
        task.setDone(true); // call setDone from Task class
    }

    /**
     * marks task as undone
     *
     * @param index is index of the target task in taskList
     * @throws AlreadyDoneException if it has already been marked
     */
    static void markAsUndone(int index) throws AlreadyDoneException {

        Task task = selectedList.get(index - 1);
        task.setDone(false); // call setDone from Task class


    }


    /**
     * deletes task in taskList
     *
     * @param index is index of the target task in taskList
     */
    static Task deleteTask(int index) {

        Task task = selectedList.get(index - INDEX_OFFSET); //save task for printing later
        taskList.remove(task); // try to remove from list
        selectedList.remove(task);
        listSize -= 1;
        return task;


    }

    static public ArrayList<Task> deleteAllTaskFromList() {

        ArrayList<Task> returnedList = new ArrayList<>(selectedList);
        listSize -= selectedList.size();
        taskList.removeAll(selectedList);

        selectedList.clear();
        return returnedList;

    }


}