/**
 * Represents a task item, with two information fields:
 *   1. isDone - whether the task is completed or not.
 *   2. taskDesc - description of the task.
 * Task is the parent class that Todo, Deadline and Event inherits from.
 */

public class Task {
    private boolean isDone;
    private String taskDesc;

    public Task(String desc) {
        setIsDone(false);
        setTaskDesc(desc);
    }

    public void setTaskDesc(String desc) {
        taskDesc = desc;
    }

    public String getTaskDesc() {
        return this.taskDesc;
    }

    public void setIsDone(boolean val) {
        this.isDone = val;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String toString() {
        String symbol = isDone? Ui.TICK : Ui.CROSS;
        return "[" + symbol + "] " + taskDesc;
    }

}