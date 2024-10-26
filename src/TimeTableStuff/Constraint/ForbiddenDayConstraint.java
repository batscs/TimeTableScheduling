package TimeTableStuff.Constraint;

import TimeTableStuff.Event;
import TimeTableStuff.TimeTable;
import TimeTableStuff.Timepoint;

/**
 * Definiert einen Tag, in welchem das Event nicht vorkommen darf.
 * (Könnte mit AllowedTimeConstraint eine gemeinsame abstrakte Oberklasse teilen?)
 */
public class ForbiddenDayConstraint implements Constraint {

    private final int day;

    private final String event;

    public ForbiddenDayConstraint(String event, int day) {
        // TODO Das ist nicht gut programmiert, aber erstmal ist der Tag von
        //  start und end Timepoints hier irrelevant
        this.event = event;
        this.day = day;
    }

    @Override
    public boolean isSatisfied(TimeTable t) {
        Event event = t.getEventByName(this.event);

        return event.getStart().getDay() != day && event.getEnd().getDay() != day;
    }
}
