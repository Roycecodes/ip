package BinlaDan.command;

import BinlaDan.exceptions.EmptyStringException;
import BinlaDan.exceptions.MissingDateException;
import BinlaDan.tasks.Deadline;
import BinlaDan.tasks.EventTask;
import BinlaDan.tasks.Task;
import BinlaDan.tasks.Todo;
import BinlaDan.ui.Ui;

public class Parser {
    static final int LENGTH_OF_DEADLINE = 9;
    static final int LENGTH_OF_TODO = 5;
    static final int LENGTH_OF_EVENT = 6;
    static final int LENGTH_OF_BY = 4;
    static final int LENGTH_OF_TO = 4;
    static final int LENGTH_OF_FROM = 6;

    public static void decipherCommand(String receivedText) {//method to determine what kind of task to call

        String command = receivedText.strip().split(" ")[0];
        switch (command) {
        case Command.ADD_TODO_COMMAND:
            Command.executeAddTodo(receivedText); //create Todo task
            Ui.printLineDivider();
            break;

        case Command.ADD_DEADLINE_COMMAND:
            Command.executeAddDeadline(receivedText); //create deadlineTask
            Ui.printLineDivider();
            break;

        case Command.ADD_EVENT_COMMAND:
            Command.executeAddEvent(receivedText); //create EventTask
            Ui.printLineDivider();
            break;

        case Command.LIST_COMMAND:
            Command.executeDisplayList();
            break;

        case Command.SAVE_COMMAND:
            Command.executeSave();
            Ui.printLineDivider();
            break;
        case Command.DELETE_COMMAND:
            Command.executeDeleteTask(receivedText);
            Ui.printLineDivider();
            break;

        case Command.DONE_COMMAND:
            Command.executeMarkAsDone(receivedText);
            break;
        case Command.UNDONE_COMMAND:
            Command.executeMarkAsUndone(receivedText);
            break;
        default:
            System.out.println("I DO NOT UNDERSTAND " + receivedText);
            System.out.println("use commands list,done/undone,todo,event,deadline,save,delete");

        }


    }

    static public String[] parseDeadline(String receivedText) throws EmptyStringException, MissingDateException {
        String[] returnedArray = new String[2];
        receivedText = receivedText.trim();
        if (receivedText.split(" ").length == 1) { //if only command word throw empty string statement
            throw new EmptyStringException();
        }
        int indexOfBy = receivedText.indexOf("/by");
        if (indexOfBy == -1) { // if by does not exist throw missing Date exception
            throw new MissingDateException();

        }

        returnedArray[0] = receivedText.substring(LENGTH_OF_DEADLINE, indexOfBy).trim();
        // saving text in front of /by as description, starting from after "deadline"

        returnedArray[1] = receivedText.substring(indexOfBy + LENGTH_OF_BY);
        //saving everything after /by as deadline

        // obtaining index of first /by

        if (returnedArray[0].isEmpty()) {
            throw new EmptyStringException();
        }
        if (returnedArray[1].isEmpty()) {
            throw new MissingDateException();
        }

        return returnedArray;

    }

    static public String[] parseEvent(String receivedText) throws MissingDateException, EmptyStringException {

        receivedText = receivedText.trim();
        String[] words = receivedText.split("\\s+");
        if (words.length <= 1) {  // If there's only the command word or less
            throw new EmptyStringException();
        }
        String[] returnedArray = new String[3];
        int indexOfFrom = receivedText.indexOf("/from"); // obtaining index of first /from
        int indexOfTo = receivedText.indexOf("/to"); // obtaining index of first /to
        if (indexOfFrom == -1 || indexOfTo == -1) { // if any of the keywords do not exist throw missing Date exception
            throw new MissingDateException();
        }


        returnedArray[0] = receivedText.substring(LENGTH_OF_EVENT, indexOfFrom).trim();
        // saving text in front of /from as description after cutting out first word "event"

        returnedArray[1] = receivedText.substring(indexOfFrom + LENGTH_OF_FROM, indexOfTo).trim();
        //saving everything in between /from and /to as startTime

        returnedArray[2] = receivedText.substring(indexOfTo + LENGTH_OF_TO).trim();
        //saving everything after /to as endTime

        if (returnedArray[0].isEmpty()) {

            throw new EmptyStringException();
        }
        if (returnedArray[1].isEmpty() || returnedArray[2].isEmpty()) {

            throw new MissingDateException();
        }
        return returnedArray;

    }

    static char checkTaskType(Task task) {
        char taskType = 'T';
        if (task instanceof EventTask) {
            taskType = 'E';
        } else if (task instanceof Deadline) {
            taskType = 'D';
        } else if (task instanceof Todo) {
            taskType = 'T';
        }

        return taskType;
    }

    public static int getTaskNumber(String receivedText) {

        return Integer.parseInt(receivedText.split(" ")[1]);
    }

    public static String getTaskAsSavedFormat(Task task) {
        String returnedText = "";
        char taskType = checkTaskType(task);
        returnedText += taskType;
        returnedText += "|" + task.getIsDone();
        returnedText += "|" + task.getDescription(); //add tasks to String
        switch (taskType) {
        case 'D':
            Deadline deadlineTask = (Deadline) task; // class cast to Deadline to extract Deadline

            returnedText += "|" + deadlineTask.getDeadlineAsString();// extract Deadline
            break;
        case 'E':
            EventTask eventTask = (EventTask) task; // class cast to Event to extract startTime
            returnedText += "|" + eventTask.getStartTimeAsString();// extract startTime
            returnedText += "|" + eventTask.getEndTimeAsString();// extract startTime
            break;
        }
        return returnedText;
    }

    static public String parseTodo(String receivedText) throws EmptyStringException {
        String parsedTodo = receivedText.substring(LENGTH_OF_TODO).trim(); // cut out todo command
        if (parsedTodo.isEmpty()) {
            throw new EmptyStringException();
        }
        return receivedText.substring(LENGTH_OF_TODO).trim();
    }
}