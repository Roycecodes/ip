package BinlaDan.tasks;

import BinlaDan.exceptions.AlreadyDoneException;

/**
 * represents a task
 */
public abstract class Task {
    protected String description;

    public Task(String description) {
        this.description = description;
    }


    public String toString() {
        return this.description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void setDone(boolean done) throws AlreadyDoneException;

    public abstract boolean getIsDone();

}



