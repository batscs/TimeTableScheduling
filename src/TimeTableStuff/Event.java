package TimeTableStuff;

public class Event {

    private Timepoint start;

    private Timepoint end;

    private String name;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean overlaps(Event other) {
        return overlaps(other.start, other.end);
    }

    public boolean overlaps(Timepoint start, Timepoint end) {
        // TODO Implementation
        return true;
    }

    public void mutate() {

    }

    public Timepoint getStart() {
        return start;
    }

    public Timepoint getEnd() {
        return end;
    }

    public void setTime(Timepoint start, Timepoint end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return name + " [" + start + " - " + end + "]";
    }

    public Event copy() {
        Event result = new Event(name);
        result.setTime(start, end);
        return result;
    }
}
