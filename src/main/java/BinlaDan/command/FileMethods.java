package BinlaDan.command;

import BinlaDan.ui.BinlaDan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.regex.Pattern;


public class FileMethods {
    final private static String FILE_PATH = "data/localTaskBackups.txt";

    static public void initialiseTaskList(){
        try{
            File file = new File(FILE_PATH); // create a File for the given file path
            Scanner fileString = new Scanner(file); // create a Scanner using the File as the source
            fileString.nextLine(); //ignore first line
            while (fileString.hasNext()) {
                addTaskFromSaved(fileString);
            }
            System.out.println("Synchronised Saved Targets");
        } catch (FileNotFoundException e) {
            checkAndCreateTaskFile();
            
        }

    }

    private static void addTaskFromSaved(Scanner s) {
        String[] arrayOfArguments =  s.nextLine().split(Pattern.quote("|"));
        int index;
        switch (arrayOfArguments[0]){
        case "D":
            index = ListState.addDeadline("deadline " + arrayOfArguments[2] + " /by " + arrayOfArguments[3]);
            if(arrayOfArguments[1].equals("true")){
                ListState.markAsDone(index+1);
            }
            break;
        case "E":
            index = ListState.addEvent("event " + arrayOfArguments[2] + " /from " + arrayOfArguments[3] + " /to " + arrayOfArguments[4]);
            if(arrayOfArguments[1].equals("true")){
                ListState.markAsDone(index+1);
            }
            break;
        case "T":
            index = ListState.addTodo("todo " + arrayOfArguments[2]);
//            System.out.println(arrayOfArguments[2]);
            if(arrayOfArguments[1].equals("true")){
                ListState.markAsDone(index+1);
            }
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
            System.out.println("An error occurred." +e.getMessage());

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


    public static void writeTaskFile() {

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.flush();
            file.close();

            String taskListString = ListState.generateTaskListString();
            writeToFile(FILE_PATH, taskListString);
            System.out.println("File successfully saved!");
            BinlaDan.printLineDivider();
            printTaskFile();
            BinlaDan.printLineDivider();
            System.out.println("Impressive Brother! Now we won't forget our targets!");

        } catch (IOException e) {
            System.out.println("Unable to write to the file: " + e.getMessage());
            System.out.println("Standby while I look for the file ...");
            checkAndCreateTaskFile();
            System.out.println("try again");

        }

    }
}

