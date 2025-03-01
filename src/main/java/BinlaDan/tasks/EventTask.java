package BinlaDan.tasks;
/**
 * represents a task that has a start and end date
 */
public class EventTask extends Todo{ // subclass of Todo
    private String startTime; // to save start
    private String endTime;// to save End


    public EventTask(String description, String startTime, String endTime){ //new constructor
        super(description); // uses superclass constructor to deal with description
        this.startTime = startTime; // saves deadline
        this.endTime = endTime;
    }
    public EventTask(String description, String startTime, String endTime, Boolean isDone){ //new constructor
        super(description); // uses superclass constructor to deal with description
        this.startTime = startTime; // saves deadline
        this.endTime = endTime;
        this.isDone = isDone;
    }
    public String getStartTime(){ //deadline getter
        return this.startTime;
    } // start time getter
    public String getEndTime(){ //deadline getter
        return this.endTime;
    }   // end time getter
    public void setStartTime(String startTime){ //deadline setter
        this.startTime = startTime;
    } //start time setter
    public void setEndTime(String endTime){ //deadline setter
        this.endTime = endTime;
    } //end time setter
    public String toString() {
        String addOutput = "(From: " + this.startTime + " to " + this.endTime+")";

        return "[E] "+ super.toString().substring(4) + " " + addOutput;
    }
}

