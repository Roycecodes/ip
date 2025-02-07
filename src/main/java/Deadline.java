public class Deadline extends Todo{ // subclass of Todo
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