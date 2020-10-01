/**
 * Represents a task item with a time.
 * Inherits from Task, and adds one new information field:
 *   3. at - time of the event..
 * @see Task
 */

public class Event extends Task {

    // added attribute
    private String at;

    public Event(String desc, String at) {
        super(desc);
        setAt(at);
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String getTaskDesc() {
        return super.getTaskDesc() + " (at: " + at + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
