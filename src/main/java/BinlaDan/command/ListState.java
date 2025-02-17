package BinlaDan.command;

import BinlaDan.exception.AlreadyDoneException;
import BinlaDan.exception.EmptyStringException;
import BinlaDan.exception.MissingDateException;
import BinlaDan.tasks.Deadline;
import BinlaDan.tasks.EventTask;
import BinlaDan.tasks.Task;
import BinlaDan.tasks.Todo;
import BinlaDan.ui.BinlaDan;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class ListState {
    static final int LENGTH_OF_DONE = 5;
    static final int LENGTH_OF_UNDONE = 7;
    static final int LENGTH_OF_DELETE = 7;
    static final private ArrayList<Task> myList = new ArrayList<>();
    static private int listIndex = 0;

    public static void callListState() {
        Scanner scanner = new Scanner(System.in);
        String scannedText;
        while (!(scannedText = scanner.nextLine()).equals("bye")) {     //bye breaks the while loop
            decipherCommand(scannedText);
        }
        BinlaDan.printByeText();
    }


    public static String generateTaskListString() {
        try {
            String returnedText = "Current saved Targets: \n";
            for (int i = 0; i < listIndex; i++) {

                returnedText += Parser.getTaskAsSavedFormat(myList.get(i)); //call parser function that formats tasks
                returnedText += ("\n"); //newline
            }
            return returnedText;
        } catch (NullPointerException e) {
            System.out.println("error");

        }
        return "";
    }


    static public void decipherCommand(String receivedText) {//method to determine what kind of task to call

        if (receivedText.equals("list")) {// show list
            ListState.displayList();
        } else if (receivedText.indexOf("done") == 0) {

            String taskNumber = receivedText.substring(LENGTH_OF_DONE).trim();
            markAsDone(Integer.parseInt(taskNumber));
        } else if (receivedText.indexOf("undone") == 0) {

            String taskNumber = receivedText.substring(LENGTH_OF_UNDONE).trim();
            markAsUndone(Integer.parseInt(taskNumber));
        } else if (receivedText.indexOf("deadline") == 0) {//create a deadlineTask
            addDeadline(receivedText);
            BinlaDan.printLineDivider();
        } else if (receivedText.indexOf("event") == 0) {//create a EventTask
            addEvent(receivedText);
            BinlaDan.printLineDivider();
        } else if (receivedText.indexOf("todo") == 0) {
            addTodo(receivedText); //create Todo task
            BinlaDan.printLineDivider();
        } else if (receivedText.indexOf("delete") == 0) {
            String taskNumber = receivedText.substring(LENGTH_OF_DELETE).trim();
            deleteTask(Integer.parseInt(taskNumber)); //create Todo task
            BinlaDan.printLineDivider();
        } else if (receivedText.indexOf("save") == 0) {
            FileMethods.writeTaskFile();
            BinlaDan.printLineDivider();
        }

    }

    static void printUpdateListText() {
        System.out.println("your glorious list has grown to " + listIndex);
    }

    static int addTodo(String receivedText) {
        try {
            String description = Parser.parseTodo(receivedText);
            Todo todoTask = new Todo(description); //adds description as normal task
            myList.add(todoTask); // add new todotask to list array
            listIndex += 1;          //increases index

            BinlaDan.printLineDivider();
            System.out.print("Added new target: ");
            System.out.println(description); // echos what is added to list
            printUpdateListText();
            return listIndex; //return index of item
        } catch (IndexOutOfBoundsException | EmptyStringException e) {
            System.out.println("Todo..... Todo what? Tell me now!");
        }
        return -1;// indicate error



    }

    static int addDeadline(String receivedText) {
        try {
            String[] parsedDeadline = Parser.parseDeadline(receivedText);
            String description = parsedDeadline[0];
            String deadline = parsedDeadline[1];
            Deadline deadlineTask = new Deadline(description, deadline); //adds description and deadline as a deadline task
            myList.add(deadlineTask); //add deadline task to array list
            listIndex += 1;          //increases index

            BinlaDan.printLineDivider();
            System.out.print("Added new Mission: ");
            System.out.println(description); // echos what is added to list
            System.out.print("Mission deadline: ");
            System.out.println(deadline);
            printUpdateListText();
            return listIndex; //return index of item
        } catch (EmptyStringException e) {
            System.out.println("What is your Task? Tell me Now! Don't you dare leave it empty again.");
        } catch (MissingDateException e) {
            System.out.println("what is the deadline? use \"deadline {task} /by {deadline}\"  to specify.");
            System.out.println("try again, don't mess up.");
        }
        return -1;

    }

    static int addEvent(String receivedText) {
        try {
            String[] parsedEvent = Parser.parseEvent(receivedText);
            String description = parsedEvent[0];
            String startTime = parsedEvent[1];
            String endTime = parsedEvent[2];


            EventTask eventTask = new EventTask(description, startTime, endTime); //adds description as normal task
            myList.add(eventTask); // add event task to array list
            listIndex += 1;          //increases index

            BinlaDan.printLineDivider();
            System.out.print("Added new Campaign: ");
            System.out.println(description); // echos what is added to list
            System.out.print("Campaign duration: ");
            System.out.println(startTime + " to " + endTime);
            printUpdateListText();
            return listIndex; //return index of item
        } catch (MissingDateException e) {
            System.out.println("You imbecile your event details are missing! use \"event {task} /from {start time} /to {end time}\"  to specify.");
            System.out.println("try again, don't mess up.");

        } catch (EmptyStringException e) {
            System.out.println("Brother please... indicate your task");
        }
        return -1;

    }

    static void displayList() {
        try {

            if (listIndex == 0) {
                throw new EmptyStackException();
            }
            BinlaDan.printLineDivider();
            System.out.println("Current targets: ");
            for (int i = 0; i < listIndex; i++) {

                System.out.print(i + 1 + ": "); // index of items
                System.out.println(myList.get(i)); // print items in list

            }
            BinlaDan.printLineDivider();
        } catch (EmptyStackException | IndexOutOfBoundsException e) {
            System.out.println("No Targets today!");
            BinlaDan.printLineDivider();
        }

    }

    static void markAsDone(int index) {
        try {
            Task task = myList.get(index - 1);
            task.setDone(true); // call setDone from Task class
            BinlaDan.printLineDivider();
            System.out.println("Well Done Brother! The Resistance thanks you");
            displayList();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            BinlaDan.printLineDivider();
            System.out.println("You donkey! What have you done? This isn't one of our targets ");
            BinlaDan.printLineDivider();
        } catch (AlreadyDoneException e) {
            BinlaDan.printLineDivider();
            System.out.println("You Idiot! This has mission has already been completed! What do you mean you just completed it?");
            BinlaDan.printLineDivider();
        }
    }

    static void markAsUndone(int index) {
        try {
            Task task = myList.get(index - 1);
            task.setDone(false); // call setDone from Task class
            BinlaDan.printLineDivider();
            System.out.println("The resistance will not allow you to make anymore mistakes. Complete your mission now!");
            displayList();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            BinlaDan.printLineDivider();
            System.out.println("You donkey! Are you crazy? This isn't one of our targets ");
            BinlaDan.printLineDivider();
        } catch (AlreadyDoneException e) {
            BinlaDan.printLineDivider();
            System.out.println("You must have smoked too much Shisha! This has mission has not been completed!");
            BinlaDan.printLineDivider();
        }
    }

    static void deleteTask(int index) {
        try {
            Task task = myList.get(index - 1); //save task for printing later
            myList.remove(task); // try to remove from list
            System.out.print("Target removed: ");
            System.out.println(task);
            listIndex -=1;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Are you stupid? This Task doesn't exist!");
        }

    }

}