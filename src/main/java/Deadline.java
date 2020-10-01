/**
 * Represents a task item with a deadline.
 * Inherits from Task, and adds one new information field:
 *   3. by - deadline for the task.
 * @see Task
 */

public class Deadline extends Task {

    // added attribute
    private String by;

    public Deadline(String desc, String by) {
        super(desc);
        setBy(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getTaskDesc() {
        return super.getTaskDesc() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
