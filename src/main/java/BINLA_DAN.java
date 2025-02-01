import java.util.Scanner;

public class BINLA_DAN {
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
    static private String[] myList = new String[1024];
    static private boolean[] doneList = new boolean[1024];
    static private int listIndex = 0;



    static void callListState(){
        Scanner scanner = new Scanner(System.in);
        String arg = "";
        while(!arg.equals("bye")) {     //bye breaks the while loop
            arg = scanner.nextLine();   //scanner scans console for strings
            String[] words = arg.split(" "); // split with a space
            if(words[0].equals("done")){
             markAsDone(Integer.parseInt(words[1])); // parse the supposed integer into Mark as done(only works if integer is right after
            }
            else if(words[0].equals("undone")){
                markAsUndone(Integer.parseInt(words[1])); // parse the supposed integer into Mark as done(only works if integer is right after
            }
            else if(arg.equals("list")){     //if list is read then call displayList which will display list
                displayList();
            }
            else addToList(arg);
        }
        BINLA_DAN.byeText();

    }

    static void addToList(String s){
        myList[listIndex] = s; //adds item to list
        listIndex +=1;          //increases index

        BINLA_DAN.printLine();
        System.out.print("Added new target: ");
        System.out.println(s); // echos what is added to list
        BINLA_DAN.printLine();

    }
    static void displayList(){
        BINLA_DAN.printLine();
        System.out.println("Current targets: ");
        for (int i=0; i< listIndex; i++){
            System.out.print(i+1 + ": "); // index of items
            System.out.print(myList[i]); // print items in list
            if(doneList[i]) {
                System.out.println(" [/]"); // print items in list
            }
            else  System.out.println(" [X]");
        }
        BINLA_DAN.printLine();

    }
    static void markAsDone(int index){
        if (index-1 >= listIndex || index-1<0){ //error of out of bounds
            BINLA_DAN.printLine();
            System.out.println("You donkey! What have you done? This isn't one of our targets ");
            BINLA_DAN.printLine();
            return;
        }
        if (doneList[index-1]){ //error of calling done twice
            BINLA_DAN.printLine();
            System.out.println("You Idiot! This has mission has already been completed! What do you mean you just completed it?");
            BINLA_DAN.printLine();
            return;
        }
        doneList[index-1] = true;
        BINLA_DAN.printLine();
        System.out.println("Well Done Brother! The Resistance thanks you");
        displayList();

    }
    static void markAsUndone(int index){
        if (index-1 >= listIndex || index-1<0){ //error of out of bounds
            BINLA_DAN.printLine();
            System.out.println("You donkey! Are you crazy? This isn't one of our targets ");
            BINLA_DAN.printLine();
            return;
        }
        if (!doneList[index-1]){ //error of calling done twice
            BINLA_DAN.printLine();
            System.out.println("You must have smoked too much Shisha! This has mission has not been completed!");
            BINLA_DAN.printLine();
            return;
        }
        doneList[index-1] = false;
        BINLA_DAN.printLine();
        System.out.println("The resistance will not allow you to make anymore mistakes. Complete your mission now!");
        displayList();
    }

}
