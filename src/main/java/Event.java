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

    // override method
    public String getTask() {
        return super.getTask() + " (at: " + at + ")";
    }

    // override method
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
