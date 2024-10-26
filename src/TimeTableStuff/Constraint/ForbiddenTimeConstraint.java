package TimeTableStuff.Constraint;

import TimeTableStuff.Event;
import TimeTableStuff.TimeTable;
import TimeTableStuff.Timepoint;

import java.sql.Time;

/**
 * Definiert einen Zeitraum, in welchem das Event nicht vorkommen darf.
 * (Könnte mit AllowedTimeConstraint eine gemeinsame abstrakte Oberklasse teilen?)
 */
public class ForbiddenTimeConstraint implements Constraint {

    private final Timepoint start;

    private final Timepoint end;

    private final String event;

    public ForbiddenTimeConstraint(String event, Timepoint start, Timepoint end) {
        // TODO Das ist nicht gut programmiert, aber erstmal ist der Tag von
        //  start und end Timepoints hier irrelevant
        this.event = event;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean isSatisfied(TimeTable t) {
        Event event = t.getEventByName(this.event);

        int eventStart = event.getStart().getHour() * 60 + event.getStart().getMinute();
        int startTime = start.getHour() * 60 + start.getMinute();
        int endTime = end.getHour() * 60 + end.getMinute();

        // Return true if the event start time is outside the [start, end] range
        return eventStart < startTime || eventStart > endTime;
    }
}
