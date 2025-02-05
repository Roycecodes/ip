import jdk.jfr.Event;

import java.util.Scanner;

public class BinlaDan {
    static void byeText(){
        System.out.println("_________________________________________________________________________________________");
        System.out.println("Bye. Hope to see you again soon my brother"); // exit message
        System.out.println("_________________________________________________________________________________________");
    }
    static void printLine(){
        System.out.println("_________________________________________________________________________________________");
    }



    public static void main(String[] args) {

        String logo = "$$$$$$$\\  $$$$$$\\ $$\\   $$\\ $$\\        $$$$$$\\        $$$$$$$\\   $$$$$$\\  $$\\   $$\\  \n"
                + "$$  __$$\\ \\_$$  _|$$$\\  $$ |$$ |      $$  __$$\\       $$  __$$\\ $$  __$$\\ $$$\\  $$ | \n"
                + "$$ |  $$ |  $$ |  $$$$\\ $$ |$$ |      $$ /  $$ |      $$ |  $$ |$$ /  $$ |$$$$\\ $$ | \n"
                + "$$$$$$$\\ |  $$ |  $$ $$\\$$ |$$ |      $$$$$$$$ |      $$ |  $$ |$$$$$$$$ |$$ $$\\$$ | \n"
                + "$$  __$$\\   $$ |  $$ \\$$$$ |$$ |      $$  __$$ |      $$ |  $$ |$$  __$$ |$$ \\$$$$ | \n"
                + "$$ |  $$ |  $$ |  $$ |\\$$$ |$$ |      $$ |  $$ |      $$ |  $$ |$$ |  $$ |$$ |\\$$$ | \n"
                + "$$$$$$$  |$$$$$$\\ $$ | \\$$ |$$$$$$$$\\ $$ |  $$ |      $$$$$$$  |$$ |  $$ |$$ | \\$$ | \n"
                + "\\_______/ \\______|\\__|  \\__|\\________|\\__|  \\__|      \\_______/ \\__|  \\__|\\__|  \\__| \n";
        // this is my chatbot logo

        System.out.println(logo);
        printLine();
        System.out.println("Hello brother! I'm Binla dan");
        System.out.println("What can I do for you?");// welcome message
        printLine();
        ListState.callListState();


    }


}
class ListState{
    static private Task[] myList = new Task[100];
    static private int listIndex = 0;



    static void callListState(){
        Scanner scanner = new Scanner(System.in);
        String scannedText = "";
        while(!scannedText.equals("bye")) {     //bye breaks the while loop
            scannedText = scanner.nextLine();   //scanner scans console for strings
           if( !scannedText.equals("bye")) {

               decipherCommand(scannedText);
           }
        }
        BinlaDan.byeText();

    }
    static public void decipherCommand(String receivedText){//method to determine what kind of task to call

        if (receivedText.equals("list")){// show list
            ListState.displayList();
        }
        else if(receivedText.indexOf("done") == 0){
            int indexOfDone = receivedText.indexOf("done");
            String taskNumber = receivedText.substring(indexOfDone+5).trim();
            markAsDone(Integer.parseInt(taskNumber));
        }
        else if(receivedText.indexOf("undone") == 0){
            int indexOfUndone = receivedText.indexOf("undone");
            String taskNumber = receivedText.substring(indexOfUndone+7).trim();
            markAsUndone(Integer.parseInt(taskNumber));
        }
        else if(receivedText.contains("/by")){//create a deadlineTask
            String[] parsedDeadline = Parser.parseDeadline(receivedText);
            addDeadline(parsedDeadline[0],parsedDeadline[1]);
            listUpdateText();
            BinlaDan.printLine();
        }
        else if(receivedText.contains("/from")){//create a EventTask
            String[] parsedEvent = Parser.parseEvent(receivedText);
            addEvent(parsedEvent[0],parsedEvent[1],parsedEvent[2]);
            listUpdateText();
            BinlaDan.printLine();
        }
        else{
            addTodo(receivedText); //create Todo task
            listUpdateText();
            BinlaDan.printLine();
        }

    }
    static void listUpdateText(){
        System.out.println("your glorious list has grown to " + listIndex);
    }

    static void addTask(String description){
        Task task = new Task(description); //adds description as normal task
        myList[listIndex] = task; //adds item to list
        listIndex +=1;          //increases index

        BinlaDan.printLine();
        System.out.print("Added new target: ");
        System.out.println(description); // echos what is added to list

    }
    static void addTodo(String description){
        Todo todoTask = new Todo(description); //adds description as normal task
        myList[listIndex] = todoTask; //adds item to list
        listIndex +=1;          //increases index

        BinlaDan.printLine();
        System.out.print("Added new target: ");
        System.out.println(description); // echos what is added to list

    }
    static void addDeadline(String description,String deadline){
        Deadline deadlineTask = new Deadline(description,deadline); //adds description and deadline as a deadline task
        myList[listIndex] = deadlineTask; //adds item to list
        listIndex +=1;          //increases index

        BinlaDan.printLine();
        System.out.print("Added new Mission: ");
        System.out.println(description); // echos what is added to list
        System.out.print("Mission deadline: ");
        System.out.println(deadline);

    }
    static void addEvent(String description,String startTime,String endTime){
        EventTask eventTask = new EventTask(description,startTime,endTime); //adds description as normal task
        myList[listIndex] = eventTask; //adds item to list
        listIndex +=1;          //increases index

        BinlaDan.printLine();
        System.out.print("Added new Campaign: ");
        System.out.println(description); // echos what is added to list
        System.out.print("Campaign duration: ");
        System.out.println(startTime + " to " + endTime);

    }
    static void displayList(){
        char taskType = 'T';
        BinlaDan.printLine();
        System.out.println("Current targets: ");
        for (int i=0; i< listIndex; i++){
            if(myList[i] instanceof Deadline){
                taskType = 'D';
            }
            else if(myList[i] instanceof EventTask){
                taskType = 'E';
            }
            else taskType = 'T';

            System.out.print(i+1 + ": ["); // index of items
            System.out.print(taskType + "] "); //indicate type of task
            System.out.println(myList[i]); // print items in list

        }
        BinlaDan.printLine();

    }

    static void markAsDone(int index){
        Task task =  myList[index-1];
        if (index-1 >= listIndex || index-1<0){ //error of out of bounds
            BinlaDan.printLine();
            System.out.println("You donkey! What have you done? This isn't one of our targets ");
            BinlaDan.printLine();
            return;
        }
        if (task.isDone()){ //error of calling done twice
            BinlaDan.printLine();
            System.out.println("You Idiot! This has mission has already been completed! What do you mean you just completed it?");
            BinlaDan.printLine();
            return;
        }
        task.setDone(true);
        BinlaDan.printLine();
        System.out.println("Well Done Brother! The Resistance thanks you");
        displayList();

    }
    static void markAsUndone(int index){
        Task task =  myList[index-1];
        if (index-1 >= listIndex || index-1<0){ //error of out of bounds
            BinlaDan.printLine();
            System.out.println("You donkey! Are you crazy? This isn't one of our targets ");
            BinlaDan.printLine();
            return;
        }
        if (!task.isDone()){ //error of calling undone twice
            BinlaDan.printLine();
            System.out.println("You must have smoked too much Shisha! This has mission has not been completed!");
            BinlaDan.printLine();
            return;
        }
        task.setDone(false);
        BinlaDan.printLine();
        System.out.println("The resistance will not allow you to make anymore mistakes. Complete your mission now!");
        displayList();
    }

}
