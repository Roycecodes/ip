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
    public void setDone(boolean done){}
    public boolean getIsDone(){return false;}
    public void setStartTime(String startTime){}
    public void setEndTime(String endTime){}
    public void setDeadline(String deadline){}


}



