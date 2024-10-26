import TimeTableStuff.Constraint.Constraint;
import TimeTableStuff.Constraint.ForbiddenDayConstraint;
import TimeTableStuff.Constraint.ForbiddenTimeConstraint;
import TimeTableStuff.TimeTable;
import TimeTableStuff.Event;
import TimeTableStuff.Timepoint;

import java.util.Set;

public class Main_TimeTable {
    public static void main(String[] args) {

        // FlyweightFactory für Timepoints und so?

        Event mathe = new Event("mathe");
        Event bwl = new Event("bwl");

        Timepoint start1 = new Timepoint(1, 8, 0);
        Timepoint end1 = new Timepoint(1, 12, 0);
        Constraint matheNichtMorgens = new ForbiddenTimeConstraint("mathe", start1, end1);
        Constraint matheNichtMontags = new ForbiddenDayConstraint("mathe", 1);

        Set<Constraint> HC = Set.of(matheNichtMorgens);
        Set<Constraint> SC = Set.of(matheNichtMontags);

        TimeTable tt = new TimeTable(HC, SC, mathe, bwl);

        System.out.println(tt.toString());
    }
}
