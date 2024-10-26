package TimeTableStuff;

import java.util.HashSet;
import java.util.Set;

/**
 * Wrappt mehrere Events für verschiedene Tage.
 * Damit TimeTable die Implementation selbst nicht hat,
 * und so Genetischer Algorithm Code und Scheduling Implementation getrennt sind.
 */
public class Schedule {

    // TODO Datenstruktur für Events
    //  wie flexibel soll es sein? reicht 7 tage

    private Set<Event> events;

    public Schedule() {
        this.events = new HashSet<Event>();
    }

    public Schedule(Set<Event> events) {
        this.events = new HashSet<Event>();

        for (Event e : events) {
            this.events.add(e.copy());
        }
    }

    /**
     * Methode zum Einsetzen eines Events zufällig in den Zeitplan.
     * Genutzt, um anfangs zufällig einen Stundenplan zu erstellen.
     * @param e Event
     */
    public void addRandomly(Event e) {
        events.add(e);
        randomizeTimeForEvent(e);
    }

    private void randomizeTimeForEvent(Event e) {
        Timepoint[] randomPair = Timepoint.getRandomTimepointPair();
        e.setTime(randomPair[0], randomPair[1]);
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Schedule copy() {
        return new Schedule(events);
    }
}
