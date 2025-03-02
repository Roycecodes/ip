package BinlaDan.tasks;

import BinlaDan.command.TaskDate;

import java.time.LocalDate;


/**
 * represents a task that has a deadline
 */
public class Deadline extends Todo{ // subclass of Todo
//    private String deadline; // to save date
    private final TaskDate deadline;




    public Deadline(String description, String deadline){ //new constructor
        super(description); // uses superclass constructor to deal with description
        this.deadline = new TaskDate(deadline); // saves deadline
    }
    public Deadline(String description, String deadline,Boolean isDone){ //new constructor
        super(description); // uses superclass constructor to deal with description
        this.deadline = new TaskDate(deadline); // saves deadline
        this.isDone = isDone;
    }
    public LocalDate getDeadlineAsLocalDate(){ //deadline getter
        return this.deadline.getDateLocalDate();
    }
    public String getDeadlineAsString(){
        return this.deadline.toString();
    }

    public void setDeadline(String deadline){ //deadline setter
        this.deadline.setDate(deadline);
    }

    @Override public String toString() {
        String addOutput = "(by: " + this.deadline.toString() +")";

        return "[D] " +super.toString().substring(4) + " " + addOutput;
    }
}