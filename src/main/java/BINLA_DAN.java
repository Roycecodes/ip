import java.util.Scanner;

public class BINLA_DAN {
    static void byeText(){
        System.out.println("_________________________________________________________________________________________");
        System.out.println("Bye. Hope to see you again soon my brother"); // exit message
        System.out.println("_________________________________________________________________________________________");
    }
    static void echoState(){
        Scanner scanner = new Scanner(System.in);
        String arg = "";
        while(!arg.equals("bye")) {
            arg = scanner.nextLine();
            echo(arg);
        }
        byeText();

    }

    static void echo(String s){
        System.out.println("_________________________________________________________________________________________");
        System.out.println(s); // echo
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
        echoState();


    }


}
