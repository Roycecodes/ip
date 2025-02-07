public class Deadline extends Todo{ // subclass of Todo
    private String deadline; // to save date

    public Deadline(String description, String deadline){ //new constructor
        super(description); // uses superclass constructor to deal with description
        this.deadline = deadline; // saves deadline
    }
    public String getDeadline(){ //deadline getter
        return this.deadline;
    }
    public void setDeadline(String deadline){ //deadline setter
        this.deadline = deadline;
    }

    @Override public String toString() {
        String addOutput = "(by: " + this.deadline +")";

        return super.toString() + " " + addOutput;
    }
}