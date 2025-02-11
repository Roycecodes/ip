import java.util.EmptyStackException;

public class Parser {
    static final int LENGTH_OF_DEADLINE = 9;
    static final int LENGTH_OF_TODO = 5;
    static final int LENGTH_OF_EVENT = 6;
    static final int LENGTH_OF_BY = 4;
    static final int LENGTH_OF_TO = 4;
    static final int LENGTH_OF_FROM = 6;


    static public String[] parseDeadline(String receivedText) throws EmptyStringException, MissingDateException {
        String[] returnedArray= new String[2];
        receivedText = receivedText.trim();
        if (receivedText.split(" ").length==1){ //if only command word throw empty string statement
            throw new EmptyStringException();
        }
        int indexOfBy = receivedText.indexOf("/by");
        if (indexOfBy == -1) { // if by does not exist throw missing Date exception
            throw new MissingDateException();

        }

        returnedArray[0] = receivedText.substring(LENGTH_OF_DEADLINE, indexOfBy).trim();
        // saving text in front of /by as description, starting from after "deadline"

        returnedArray[1] = receivedText.substring(indexOfBy + LENGTH_OF_BY);
        //saving everything after /by as deadline

        // obtaining index of first /by

        if (returnedArray[0].isEmpty()) {
            throw new EmptyStringException();
        }
        if (returnedArray[1].isEmpty()) {
            throw new MissingDateException();
        }

        return returnedArray;

    }

    static public String[] parseEvent(String receivedText) throws MissingDateException, EmptyStringException{

        receivedText = receivedText.trim();
        String[] words = receivedText.split("\\s+");
        if (words.length <= 1) {  // If there's only the command word or less
            throw new EmptyStringException();
        }
        String[] returnedArray = new String[3];
        int indexOfFrom = receivedText.indexOf("/from"); // obtaining index of first /from
        int indexOfTo = receivedText.indexOf("/to"); // obtaining index of first /to
        if (indexOfFrom == -1 || indexOfTo == -1) { // if any of the keywords do not exist throw missing Date exception
            throw new MissingDateException();
        }


        returnedArray[0] = receivedText.substring(LENGTH_OF_EVENT, indexOfFrom).trim();
        // saving text in front of /from as description after cutting out first word "event"

        returnedArray[1] = receivedText.substring(indexOfFrom + LENGTH_OF_FROM, indexOfTo).trim();
        //saving everything in between /from and /to as startTime

        returnedArray[2] = receivedText.substring(indexOfTo + LENGTH_OF_TO).trim();
        //saving everything after /to as endTime

        if (returnedArray[0].isEmpty()){

            throw new EmptyStringException();
        }
        if (returnedArray[1].isEmpty()|| returnedArray[2].isEmpty()){

            throw new MissingDateException();
        }
        return returnedArray;

    }

    static public String parseTodo(String receivedText) throws EmptyStringException {
        String parsedTodo = receivedText.substring(LENGTH_OF_TODO).trim(); // cut out todo command
        if (parsedTodo.isEmpty()) {
            throw new EmptyStringException();
        }
        return receivedText.substring(LENGTH_OF_TODO).trim();
    }
}