import java.util.Scanner;

public class ListState {
    static final int LENGTH_OF_DONE = 5;
    static final int LENGTH_OF_UNDONE = 7;
    static private Task[] myList = new Task[100];
    static private int listIndex = 0;

    static void callListState() {
        Scanner scanner = new Scanner(System.in);
        String scannedText;
        while (!(scannedText = scanner.nextLine()).equals("bye")) {     //bye breaks the while loop
            decipherCommand(scannedText);
        }
        BinlaDan.printByeText();
    }

    static public void decipherCommand(String receivedText) {//method to determine what kind of task to call

        if (receivedText.equals("list")) {// show list
            ListState.displayList();
        } else if (receivedText.indexOf("done") == 0) {
            int indexOfDone = receivedText.indexOf("done");
            String taskNumber = receivedText.substring(indexOfDone + LENGTH_OF_DONE).trim();
            markAsDone(Integer.parseInt(taskNumber));
        } else if (receivedText.indexOf("undone") == 0) {
            int indexOfUndone = receivedText.indexOf("undone");
            String taskNumber = receivedText.substring(indexOfUndone + LENGTH_OF_UNDONE).trim();
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
        }

    }

    static void printUpdateListText() {
        System.out.println("your glorious list has grown to " + listIndex);
    }

    static void addTodo(String receivedText) {
        try {
            String description = Parser.parseTodo(receivedText);
            Todo todoTask = new Todo(description); //adds description as normal task
            myList[listIndex] = todoTask; //adds item to list
            listIndex += 1;          //increases index

            BinlaDan.printLineDivider();
            System.out.print("Added new target: ");
            System.out.println(description); // echos what is added to list
            printUpdateListText();
        } catch (IndexOutOfBoundsException | EmptyStringException e) {
            System.out.println("Todo..... Todo what? Tell me now!");
        }

    }

    static void addDeadline(String receivedText) {
        try {
            String[] parsedDeadline = Parser.parseDeadline(receivedText);
            String description = parsedDeadline[0];
            String deadline = parsedDeadline[1];
            Deadline deadlineTask = new Deadline(description, deadline); //adds description and deadline as a deadline task
            myList[listIndex] = deadlineTask; //adds item to list
            listIndex += 1;          //increases index

            BinlaDan.printLineDivider();
            System.out.print("Added new Mission: ");
            System.out.println(description); // echos what is added to list
            System.out.print("Mission deadline: ");
            System.out.println(deadline);
            printUpdateListText();
        } catch (EmptyStringException e) {
            System.out.println("What is your Task? Tell me Now! Don't you dare leave it empty again.");
        } catch (MissingDateException e) {
            System.out.println("what is the deadline? use \"deadline {task} /by {deadline}\"  to specify.");
            System.out.println("try again, don't mess up.");
        }

    }

    static void addEvent(String receivedText) {
        try {
            String[] parsedEvent = Parser.parseEvent(receivedText);
            String description = parsedEvent[0];
            String startTime = parsedEvent[1];
            String endTime = parsedEvent[2];


            EventTask eventTask = new EventTask(description, startTime, endTime); //adds description as normal task
            myList[listIndex] = eventTask; //adds item to list
            listIndex += 1;          //increases index

            BinlaDan.printLineDivider();
            System.out.print("Added new Campaign: ");
            System.out.println(description); // echos what is added to list
            System.out.print("Campaign duration: ");
            System.out.println(startTime + " to " + endTime);
            printUpdateListText();
        }
        catch (MissingDateException e){
            System.out.println("You imbecile your event details are missing! use \"event {task} /from {start time} /to {end time}\"  to specify.");
            System.out.println("try again, don't mess up.");

        }
        catch (EmptyStringException e){
            System.out.println("Brother please... indicate your task");
        }

    }

    static void displayList() {
        char taskType;
        BinlaDan.printLineDivider();
        System.out.println("Current targets: ");
        for (int i = 0; i < listIndex; i++) {
            if (myList[i] instanceof Deadline) { //checks if task is a Deadline class
                taskType = 'D';
            } else if (myList[i] instanceof EventTask) {
                taskType = 'E';
            } else taskType = 'T';

            System.out.print(i + 1 + ": ["); // index of items
            System.out.print(taskType + "] "); //indicate type of task
            System.out.println(myList[i]); // print items in list

        }
        BinlaDan.printLineDivider();

    }

    static void markAsDone(int index) {
        Task task = myList[index - 1];
        if (index <= 0 || index > listIndex) { //error of out of bounds
            BinlaDan.printLineDivider();
            System.out.println("You donkey! What have you done? This isn't one of our targets ");
            BinlaDan.printLineDivider();
            return;
        }
        if (task.getIsDone()) { //error of calling done twice
            BinlaDan.printLineDivider();
            System.out.println("You Idiot! This has mission has already been completed! What do you mean you just completed it?");
            BinlaDan.printLineDivider();
            return;
        }
        task.setDone(true); // call setDone from Task class
        BinlaDan.printLineDivider();
        System.out.println("Well Done Brother! The Resistance thanks you");
        displayList();

    }

    static void markAsUndone(int index) {
        Task task = myList[index - 1];
        if (index <= 0 || index > listIndex) { //error of out of bounds
            BinlaDan.printLineDivider();
            System.out.println("You donkey! Are you crazy? This isn't one of our targets ");
            BinlaDan.printLineDivider();
            return;
        }
        if (!task.getIsDone()) { //error of calling undone twice
            BinlaDan.printLineDivider();
            System.out.println("You must have smoked too much Shisha! This has mission has not been completed!");
            BinlaDan.printLineDivider();
            return;
        }
        task.setDone(false); // call setDone from Task class
        BinlaDan.printLineDivider();
        System.out.println("The resistance will not allow you to make anymore mistakes. Complete your mission now!");
        displayList();
    }

}