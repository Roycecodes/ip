package BinlaDan.tasks;

import BinlaDan.command.TaskDate;

/**
 * represents a task that has a start and end date
 */
public class EventTask extends Todo { // subclass of Todo
    private TaskDate startTime; // to save start
    private TaskDate endTime;// to save End


    public EventTask(String description, String startTime, String endTime) { //new constructor
        super(description); // uses superclass constructor to deal with description
        this.startTime = new TaskDate(startTime); // saves deadline
        this.endTime = new TaskDate(endTime);
    }

    public EventTask(String description, String startTime, String endTime, Boolean isDone) { //new constructor
        super(description); // uses superclass constructor to deal with description
        this.startTime = new TaskDate(startTime); // saves deadline
        this.endTime = new TaskDate(endTime);
        this.isDone = isDone;
    }

    public String getStartTimeAsString() { //deadline getter
        return this.startTime.toString();
    } // start time getter

    public String getEndTimeAsString() { //deadline getter
        return this.endTime.toString();
    }   // end time getter

    public void setStartTime(String startTime) { //deadline setter
        this.startTime.setDate(startTime);
    } //start time setter

    public void setEndTime(String endTime) { //deadline setter
        this.endTime.setDate(endTime);
    } //end time setter

    public String toString() {
        String addOutput = "(From: " + getStartTimeAsString() + " to " + getEndTimeAsString() + ")";

        return "[E] " + super.toString().substring(4) + " " + addOutput;
    }
}

