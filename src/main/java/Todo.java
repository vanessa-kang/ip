/**
 * Inherits from Task, but does not add any new information fields.
 * @see Task
 */

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    // override method
    public String toString() {
        return "[T]" + super.toString();
    }
}
