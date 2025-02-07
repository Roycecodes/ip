


public class BinlaDan {
    static final String LINE_DIVIDER = "_________________________________________________________________________________________";
    
    static void byeText(){
        printLineDivider();
        System.out.println("Bye. Hope to see you again soon my brother"); // exit message
        printLineDivider();
    }
    static void printLineDivider(){
        System.out.println(LINE_DIVIDER");
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
        printLineDivider();
        System.out.println("Hello brother! I'm Binla dan");
        System.out.println("What can I do for you?");// welcome message
        printLineDivider();
        ListState.callListState();
    }
}

