public class Task {
    private boolean isDone;
    private String taskDesc;

    //constructor
    public Task(String desc) {
        this.isDone = false;
        this.taskDesc = desc;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public String getTask() {
        return this.taskDesc;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

}