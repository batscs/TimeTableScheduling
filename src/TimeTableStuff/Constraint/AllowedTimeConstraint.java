package TimeTableStuff.Constraint;

import TimeTableStuff.Event;
import TimeTableStuff.TimeTable;
import TimeTableStuff.Timepoint;

/**
 * Definiert einen Zeitraum, in welchem das Event vorkommen muss.
 */
public class AllowedTimeConstraint implements Constraint {

    // Bequem gemacht, da das hier immer das inverse zu ForbiddenTimeConstraint ist
    private final ForbiddenTimeConstraint forbiddenTimeConstraint;

    public AllowedTimeConstraint(String event, Timepoint start, Timepoint end) {
        this.forbiddenTimeConstraint = new ForbiddenTimeConstraint(event, start, end);
    }

    @Override
    public boolean isSatisfied(TimeTable t) {
        return !forbiddenTimeConstraint.isSatisfied(t);
    }
}
