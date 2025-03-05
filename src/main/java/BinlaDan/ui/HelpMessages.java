package BinlaDan.ui;

public class HelpMessages {

    /**
     * Todo command explanation
     */
    public static void printTodoHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("TODO COMMAND");
        System.out.println(" ");
        System.out.println("This command adds a todo task to your task list so you can always keep track of your todo tasks!");
        System.out.println(" ");
        System.out.println("todo {task}");
        System.out.println(" ");
        System.out.println("e.g. todo homework, todo cook, todo test out new IED");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Deadline command explanation
     */
    public static void printDeadlineHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("DEADLINE COMMAND");
        System.out.println(" ");
        System.out.println("This command adds a task to your task list that includes a deadline so you can keep track of your important timelines!");
        System.out.println(" ");
        System.out.println("deadline {task} /by {deadline as string or LocalDate format (YYYY-MM-DD)}");
        System.out.println(" ");
        System.out.println("e.g. deadline homework /by tomorrow, deadline cook /by 2025-12-25, deadline fly plane /by 2001-09-11");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Event command explanation
     */
    public static void printEventHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("EVENT COMMAND");
        System.out.println(" ");
        System.out.println("This command adds an event to your task list that includes a start and end time!");
        System.out.println(" ");
        System.out.println("event {task} /from {start time} /to {end time}");
        System.out.println(" ");
        System.out.println("e.g. event test /from tomorrow 4pm /to 6pm");
        System.out.println("     event exam period /from 2025-04-10 /to 2025-05-01");
        System.out.println("     event annex capital /from 2021-05-01 /to now");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Done command explanation
     */
    public static void printDoneHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("DONE COMMAND");
        System.out.println(" ");
        System.out.println("This command sets a task as done!");
        System.out.println(" ");
        System.out.println("done {index of task on most recent list}");
        System.out.println(" ");
        System.out.println("e.g. done 1, done 5");
        System.out.println(" ");
        System.out.println("This command can be used after find to quickly mark tasks as done from filtered list");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Undone command explanation
     */
    public static void printUndoneHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("UNDONE COMMAND");
        System.out.println(" ");
        System.out.println("This command sets a task as undone!");
        System.out.println(" ");
        System.out.println("undone {index of task on most recent list}");
        System.out.println(" ");
        System.out.println("e.g. undone 1, undone 5");
        System.out.println(" ");
        System.out.println("This command can be used after find to quickly mark tasks as undone from filtered list");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Find command explanation
     */
    public static void printFindHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("FIND COMMAND");
        System.out.println(" ");
        System.out.println("This command searches the list for tasks containing keywords of your search");
        System.out.println(" ");
        System.out.println("find {keywords/task type}");
        System.out.println(" ");
        System.out.println("e.g. find homework, find Dec, find todo, find event");
        System.out.println(" ");
        System.out.println("This command can be used before done and undone to quickly search and mark tasks");
        System.out.println("It can also be used before using delete command");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Delete command explanation
     */
    public static void printDeleteHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("DELETE COMMAND");
        System.out.println(" ");
        System.out.println("This command deletes a task");
        System.out.println(" ");
        System.out.println("delete {index of task on most recent list}/all");
        System.out.println(" ");
        System.out.println("e.g. delete 1, delete 5, delete all");
        System.out.println(" ");
        System.out.println("This command can be used after find to quickly delete tasks from filtered list");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Save command explanation
     */
    public static void printSaveHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("SAVE COMMAND");
        System.out.println(" ");
        System.out.println("This command saves tasks to data/localTaskBackups");
        System.out.println(" ");
        System.out.println("e.g. save");
        System.out.println("---------------------------------------------------------");
    }

    public static void printListHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("LIST COMMAND");
        System.out.println(" ");
        System.out.println("This command displays the task list");
        System.out.println(" ");
        System.out.println("e.g. list");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Displays a list of all available commands
     */
    public static void printCommandList() {
        System.out.println("---------------------------------------------------------");
        System.out.println("AVAILABLE COMMANDS");
        System.out.println("Use help {command name} to find out more");
        System.out.println("or help all to view all!");
        System.out.println("- help");
        System.out.println("- list");
        System.out.println("- todo");
        System.out.println("- deadline");
        System.out.println("- event");
        System.out.println("- done/undone");
        System.out.println("- find");
        System.out.println("- delete");
        System.out.println("- save");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Easter egg method for the "donkey" command
     */
    public static void printDonkeyHelp() {
        System.out.println("---------------------------------------------------------");
        System.out.println("HAHA you should be a comedian... \n \n \n \n \n \n \n");
        System.out.println("donkey");
        System.out.println("---------------------------------------------------------");
    }
}

