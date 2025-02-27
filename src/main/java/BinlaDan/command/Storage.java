package BinlaDan.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.regex.Pattern;


public class Storage {
    final private static String FILE_PATH = "data/localTaskBackups.txt";

    static public void initialiseTaskList() {
        try {
            File file = new File(FILE_PATH); // create a File for the given file path
            Scanner fileString = new Scanner(file); // create a Scanner using the File as the source
            if (fileString.hasNext()) {
                fileString.nextLine();
            }//ignore first line
            while (fileString.hasNext()) {
                addTaskFromSaved(fileString);
            }
            System.out.println("Synchronised Saved Targets");
        } catch (FileNotFoundException e) {
            checkAndCreateTaskFile();

        }

    }

    private static void addTaskFromSaved(Scanner s) {
        String[] arrayOfArguments = s.nextLine().split(Pattern.quote("|"));
        int index;
        switch (arrayOfArguments[0]) {
        case "D":
            TaskList.addDeadline(arrayOfArguments[2], arrayOfArguments[3],arrayOfArguments[1].equals("true"));

            break;
        case "E":
            TaskList.addEvent(arrayOfArguments[2], arrayOfArguments[3],arrayOfArguments[4],arrayOfArguments[1].equals("true"));

            break;
        case "T":
            TaskList.addTodo(arrayOfArguments[2],arrayOfArguments[1].equals("true"));
            break;

        }
    }

    public static void checkAndCreateTaskFile() {

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs(); // Creates all necessary parent directories
                }
                file.createNewFile();

                System.out.println("Target list saved to: " + file.getName());

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());

        }


    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void printTaskFile() {
        try {
            printFileContents(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close(); // need to close to complete
    }



    public static void saveTasksToFile() throws IOException {

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.flush();
            file.close();

            String taskListString = TaskList.generateTaskListString();
            writeToFile(FILE_PATH, taskListString);

        } catch (IOException e) {
            System.out.println("Unable to write to the file: " + e.getMessage());
            throw new IOException();
        }

    }
}

