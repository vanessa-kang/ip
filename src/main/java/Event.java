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

    @Override
    public String getTask() {
        return super.getTask() + " (at: " + at + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
