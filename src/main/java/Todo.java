public class Todo extends Task{ // subclass of Task
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