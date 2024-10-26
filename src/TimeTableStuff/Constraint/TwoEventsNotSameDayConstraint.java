package TimeTableStuff.Constraint;

import TimeTableStuff.Event;
import TimeTableStuff.TimeTable;

import java.sql.Time;

/**
 * Constraint zum Vermeiden davon, dass zwei Events am selben Tag stattfinden.
 * (z.B. für Iwanowski mit diskreter Mathematik)
 */
public class TwoEventsNotSameDayConstraint implements Constraint {

    private final String left;
    private final String right;

    public TwoEventsNotSameDayConstraint(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfied(TimeTable t) {
        Event left = t.getEventByName(this.left);
        Event right = t.getEventByName(this.right);

        return left.getEnd().getDay() != right.getStart().getDay();
    }
}
