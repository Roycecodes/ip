import java.util.Scanner;

public class BINLA_DAN {
    static void byeText(){
        System.out.println("_________________________________________________________________________________________");
        System.out.println("Bye. Hope to see you again soon my brother"); // exit message
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
        System.out.println("_________________________________________________________________________________________");
        System.out.println("Hello brother! I'm Binla dan");
        System.out.println("What can I do for you?");// welcome message
        ListState.callListState();


    }


}
class ListState{
    static private String[] myList = new String[1024];
    static private int listIndex = 0;



    static void callListState(){
        Scanner scanner = new Scanner(System.in);
        String arg = "";
        while(!arg.equals("bye")) {     //bye breaks the while loop
            arg = scanner.nextLine();   //scanner scans console for strings
            if(arg.equals("list")){     //if list is read then call displayList which will display list
                displayList();
            }
            else addToList(arg);
        }
        BINLA_DAN.byeText();

    }

    static void addToList(String s){
        myList[listIndex] = s;
        listIndex +=1;

        System.out.println("_________________________________________________________________________________________");
        System.out.print("Added new target: ");
        System.out.println(s); // echos what is added to list
        System.out.println("_________________________________________________________________________________________");

    }
    static void displayList(){
        System.out.println("_________________________________________________________________________________________");
        System.out.println("Current targets: ");
        for (int i=0; i< listIndex; i++){
            System.out.print(i+1 + ": "); // index of items
            System.out.println(myList[i]); // print items in list
        }
        System.out.println("_________________________________________________________________________________________");

    }

}
