public class Parser {


    static public String[] parseDeadline(String receivedText){
        String[] returnedArray= new String[2];
        int indexOfBy = receivedText.indexOf("/by"); // obtaining index of first /by
        returnedArray[0] = receivedText.substring(0,indexOfBy).trim();
        // saving text in front of /by as description
        returnedArray[1] = receivedText.substring(indexOfBy+4);//saving everything after /by as deadline
        return returnedArray;

    }
    static public String[] parseEvent(String receivedText){
        String[] returnedArray= new String[3];
        int indexOfFrom = receivedText.indexOf("/from"); // obtaining index of first /from
        int indexOfTo =   receivedText.indexOf("/to"); // obtaining index of first /to
        returnedArray[0] = receivedText.substring(0,indexOfFrom).trim();
        // saving text in front of /from as description
        returnedArray[1] = receivedText.substring(indexOfFrom+6,indexOfTo).trim();
        //saving everything in between /from and /to as startTime
        returnedArray[2] = receivedText.substring(indexOfTo+4).trim();
        return returnedArray;

    }

}