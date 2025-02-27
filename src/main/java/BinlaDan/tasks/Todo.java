package BinlaDan.tasks;

import BinlaDan.exceptions.AlreadyDoneException;

public class Todo extends Task { // subclass of Task
    protected boolean isDone; // to save done value

    public Todo(String description) {
        // new constructor because constructors are not inherited
        super(description);

    }

    public Todo(String description, Boolean isDone) {
        // new constructor because constructors are not inherited
        super(description);
        this.isDone = isDone;

    }

    @Override
    public boolean getIsDone() {// done value getter
        return isDone;
    }

    public void setDone(boolean args) throws AlreadyDoneException { // done value setter
        if (this.isDone == args) {
            throw new AlreadyDoneException();
        }
        this.isDone = args;
    }

    public String toString() {
        String addOutput = this.isDone ? "[/]" : "[X]"; //give check if done cross is not
        return "[T] " + addOutput + " " + super.toString();
    }

}