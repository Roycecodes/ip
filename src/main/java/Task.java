public class Task {
    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public String toString() {
        return this.description;
    }


}
class Todo extends Task{ // subclass of Task
    protected boolean done; // to save done value
    public Todo(String description){
        // new constructor because constructors are not inherited
        super(description);

    }

    public boolean isDone(){// done value getter
        return done;
    }
    public void setDone(boolean args) { // done value setter
        this.done = args;
    }
    public String toString() {
        String addOutput = this.done? "[/]" : "[X]"; //give check if done cross if not
        return addOutput +" " + super.toString();
    }

}
class Deadline extends Todo{ // subclass of Todo
    private String deadline; // to save date

    public Deadline(String description, String deadline){ //new constructor
        super(description); // uses superclass constructor to deal with description
        this.deadline = deadline; // saves deadline
    }
    public String getBy(){ //deadline getter
        return this.deadline;
    }
    public void setBy(String deadline){ //deadline setter
        this.deadline = deadline;
    }
    public String toString() {
        String addOutput = "(by: " + this.deadline +")";

        return super.toString() + " " + addOutput;
    }
}

class EventTask extends Todo{ // subclass of Todo
    private String startTime; // to save start
    private String endTime;// to save End


    public EventTask(String description, String startTime, String endTime){ //new constructor
        super(description); // uses superclass constructor to deal with description
        this.startTime = startTime; // saves deadline
        this.endTime = endTime;
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

        return super.toString() + " " + addOutput;
    }
}

