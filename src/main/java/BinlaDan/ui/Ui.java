package BinlaDan.ui;

import BinlaDan.command.TaskList;
import BinlaDan.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import BinlaDan.command.Parser;

/**
 * Ui class holds methods relating to the User interface of the program
 * including accepting commands and printing outputs
 */
public class Ui {
    /**
     * Offset required to convert between 1-indexing and 0-indexing.
     */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    /**
     * Format of indexed list item
     */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    static final String LINE_DIVIDER = "_________________________________________________________________________________________";
    static final String LOGO = """
            $$$$$$$\\  $$$$$$\\ $$\\   $$\\ $$\\        $$$$$$\\        $$$$$$$\\   $$$$$$\\  $$\\   $$\\ \s
            $$  __$$\\ \\_$$  _|$$$\\  $$ |$$ |      $$  __$$\\       $$  __$$\\ $$  __$$\\ $$$\\  $$ |\s
            $$ |  $$ |  $$ |  $$$$\\ $$ |$$ |      $$ /  $$ |      $$ |  $$ |$$ /  $$ |$$$$\\ $$ |\s
            $$$$$$$\\ |  $$ |  $$ $$\\$$ |$$ |      $$$$$$$$ |      $$ |  $$ |$$$$$$$$ |$$ $$\\$$ |\s
            $$  __$$\\   $$ |  $$ \\$$$$ |$$ |      $$  __$$ |      $$ |  $$ |$$  __$$ |$$ \\$$$$ |\s
            $$ |  $$ |  $$ |  $$ |\\$$$ |$$ |      $$ |  $$ |      $$ |  $$ |$$ |  $$ |$$ |\\$$$ |\s
            $$$$$$$  |$$$$$$\\ $$ | \\$$ |$$$$$$$$\\ $$ |  $$ |      $$$$$$$  |$$ |  $$ |$$ | \\$$ |\s
            \\_______/ \\______|\\__|  \\__|\\________|\\__|  \\__|      \\_______/ \\__|  \\__|\\__|  \\__|\s
            """;

    public static void printLogo() {
        System.out.println(LOGO);
    }

    public static void printByeText() {
        printLineDivider();
        System.out.println("Bye. Hope to see you again soon my brother"); // exit message
        printLineDivider();
    }

    public static void printLineDivider() {
        System.out.println(LINE_DIVIDER);
    }

    public static void printHelloMessage() {
        printLogo();
        System.out.println("Hello brother! I'm Binla dan");
        System.out.println("What can I do for you?");// welcome message
        printLineDivider();
    }

    public static void printItems(String... messages) { //print items to console
        for (String s : messages) {
            System.out.println(s.replace("\n", System.lineSeparator()));
        }
    }

    /**
     * outputs taskList on console
     *
     * @param tasks is list of tasks to be outputt
     */
    public static void printTaskListView(List<Task> tasks) {
        //prints TaskList on console
        final List<String> formattedTasks = new ArrayList<>();
        for (Task t : tasks) {
            formattedTasks.add(t.toString());
        }
        printAsIndexedList(formattedTasks);
    }

    /**
     * print items as an indexed list
     */
    private static void printAsIndexedList(List<String> list) {
        printItems(getIndexedListForViewing(list));

    }

    /**
     * formats list into an indexed list
     */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        //sets offset so it starts from 1
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * extracts items from each list entry
     *
     * @return a formatted version of items
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    /**
     * waits for user input
     */
    public static void waitForCommand() {
        Scanner scanner = new Scanner(System.in);
        String scannedText;
        while (!(scannedText = scanner.nextLine()).equals("bye")) {     //bye breaks the while loop
            Parser.decipherCommand(scannedText);
        }

    }

    public static void printUpdateListText() {
        System.out.println("your glorious list has grown to " + TaskList.getListSize());
    }



}

