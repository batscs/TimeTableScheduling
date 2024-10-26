import TimeTableStuff.Constraint.Constraint;
import TimeTableStuff.Constraint.ForbiddenDayConstraint;
import TimeTableStuff.Constraint.ForbiddenTimeConstraint;
import TimeTableStuff.Event;
import TimeTableStuff.TimeTable;
import TimeTableStuff.Timepoint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_Population {
    public static void main(String[] args) {

        Event mathe = new Event("mathe");
        Event bwl = new Event("bwl");

        Timepoint start1 = new Timepoint(1, 8, 0);
        Timepoint end1 = new Timepoint(1, 13, 0);
        Constraint matheNichtMorgens = new ForbiddenTimeConstraint("mathe", start1, end1);

        Constraint bwlNichtMontags = new ForbiddenDayConstraint("bwl", 1);
        Constraint bwlNichtDienstags = new ForbiddenDayConstraint("bwl", 2);
        Constraint bwlNichtMittwochs = new ForbiddenDayConstraint("bwl", 3);
        Constraint bwlNichtDonnerstags = new ForbiddenDayConstraint("bwl", 4);

        Set<Constraint> hardConstraints = Set.of(matheNichtMorgens);
        Set<Constraint> softConstraints = Set.of(bwlNichtMontags, bwlNichtDienstags, bwlNichtMittwochs, bwlNichtDonnerstags);

        Population population = new Population(hardConstraints, softConstraints, mathe, bwl);

        TimeTable b = population.getBest();

        List<TimeTable> tables = population.getPopulation();

        for (TimeTable t : tables) {
            System.out.println(t);
            System.out.println("---------");
        }

        System.out.println("BEST::");
        System.out.println(b);
    }
}
