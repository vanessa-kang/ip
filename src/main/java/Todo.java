public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public String getTask() {
        return super.getTask();
    }

    // override method
    public String toString() {
        return "[T]" + super.toString();
    }
}
