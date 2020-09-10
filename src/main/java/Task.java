public class Task {
    private boolean isDone;
    private String taskDesc;

    public Task(String desc) {
        this.isDone = false;
        setTask(desc);
    }

    public void setTask(String desc) {
        taskDesc = desc;
    }

    public String getTask() {
        return this.taskDesc;
    }

    public void setIsDone(boolean val) {
        this.isDone = val;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String toString() {
        String symbol = isDone? Duke.TICK : Duke.CROSS;
        return "[" + symbol + "] " + taskDesc;
    }

}