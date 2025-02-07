public class Parser {
static final int LENGTH_OF_DEADLINE = 9;
static final int LENGTH_OF_TODO = 5;
static final int LENGTH_OF_EVENT = 6;
static final int LENGTH_OF_BY = 4;
static final int LENGTH_OF_TO = 4;
static final int LENGTH_OF_FROM = 6;


    static public String[] parseDeadline(String receivedText){
        String[] returnedArray= new String[2];
        int indexOfBy = receivedText.indexOf("/by"); // obtaining index of first /by
        returnedArray[0] = receivedText.substring(LENGTH_OF_DEADLINE,indexOfBy).trim();
        // saving text in front of /by as description, starting from after "deadline"
        returnedArray[1] = receivedText.substring(indexOfBy+LENGTH_OF_BY);//saving everything after /by as deadline
        return returnedArray;

    }
    static public String[] parseEvent(String receivedText){
        String[] returnedArray= new String[3];
        int indexOfFrom = receivedText.indexOf("/from"); // obtaining index of first /from
        int indexOfTo =   receivedText.indexOf("/to"); // obtaining index of first /to
        returnedArray[0] = receivedText.substring(LENGTH_OF_EVENT,indexOfFrom).trim();
        // saving text in front of /from as description after cutting out first word "event"
        returnedArray[1] = receivedText.substring(indexOfFrom+LENGTH_OF_FROM,indexOfTo).trim();
        //saving everything in between /from and /to as startTime
        returnedArray[2] = receivedText.substring(indexOfTo+LENGTH_OF_TO).trim();
        return returnedArray;

    }
    static public String parseTodo(String receivedText){
        return receivedText.substring(LENGTH_OF_TODO).trim(); // cut out todo command

    }

}