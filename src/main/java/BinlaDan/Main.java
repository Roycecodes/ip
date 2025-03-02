package BinlaDan;

import BinlaDan.command.Storage;
import BinlaDan.command.TaskList;
import BinlaDan.ui.Ui;

import java.io.IOException;

public class Main {

    public static void main(String[] launchArgs) {
        new Main().run(launchArgs);
    }

    /**
     * Runs the program until termination.
     */
    public void run(String[] launchArgs) {
        start(launchArgs);
        Ui.waitForCommand();
        exit();
    }

    private void start(String[] launchArgs) {
        Storage.initialiseTaskList();
        TaskList.selectTaskList();
        Ui.printHelloMessage();

    }
    private void exit() {
        try{
        Storage.saveTasksToFile();}
        catch (IOException e){
            System.out.println("error saving occurred");
            }
        Ui.printByeText();
        System.exit(0);
    }



}

