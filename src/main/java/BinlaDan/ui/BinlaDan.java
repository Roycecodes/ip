package BinlaDan.ui;

import BinlaDan.command.*;

public class BinlaDan {
    static final String LINE_DIVIDER = "_________________________________________________________________________________________";
    
    public static void printByeText(){
        printLineDivider();
        System.out.println("Bye. Hope to see you again soon my brother"); // exit message
        printLineDivider();
    }
    public static void printLineDivider(){
        System.out.println(LINE_DIVIDER);
    }
    public static void main(String[] args) {

        String logo = """
                $$$$$$$\\  $$$$$$\\ $$\\   $$\\ $$\\        $$$$$$\\        $$$$$$$\\   $$$$$$\\  $$\\   $$\\ \s
                $$  __$$\\ \\_$$  _|$$$\\  $$ |$$ |      $$  __$$\\       $$  __$$\\ $$  __$$\\ $$$\\  $$ |\s
                $$ |  $$ |  $$ |  $$$$\\ $$ |$$ |      $$ /  $$ |      $$ |  $$ |$$ /  $$ |$$$$\\ $$ |\s
                $$$$$$$\\ |  $$ |  $$ $$\\$$ |$$ |      $$$$$$$$ |      $$ |  $$ |$$$$$$$$ |$$ $$\\$$ |\s
                $$  __$$\\   $$ |  $$ \\$$$$ |$$ |      $$  __$$ |      $$ |  $$ |$$  __$$ |$$ \\$$$$ |\s
                $$ |  $$ |  $$ |  $$ |\\$$$ |$$ |      $$ |  $$ |      $$ |  $$ |$$ |  $$ |$$ |\\$$$ |\s
                $$$$$$$  |$$$$$$\\ $$ | \\$$ |$$$$$$$$\\ $$ |  $$ |      $$$$$$$  |$$ |  $$ |$$ | \\$$ |\s
                \\_______/ \\______|\\__|  \\__|\\________|\\__|  \\__|      \\_______/ \\__|  \\__|\\__|  \\__|\s
                """;
        // this is my chatbot logo

        System.out.println(logo);
        printLineDivider();
        System.out.println("Hello brother! I'm Binla dan");
        System.out.println("What can I do for you?");// welcome message
        printLineDivider();
        ListState.callListState();
    }
}

