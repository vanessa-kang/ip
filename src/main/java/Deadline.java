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

    // override method
    public String getTask() {
        return super.getTask() + " (by: " + by + ")";
    }

    // override method
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
