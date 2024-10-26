import TimeTableStuff.Constraint.Constraint;
import TimeTableStuff.Constraint.ForbiddenDayConstraint;
import TimeTableStuff.Constraint.ForbiddenTimeConstraint;
import TimeTableStuff.Event;
import TimeTableStuff.Population;
import TimeTableStuff.TimeTable;
import TimeTableStuff.Timepoint;

import java.util.Set;

public class Main_Evolution {
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

        Constraint matheNichtMontags = new ForbiddenDayConstraint("mathe", 1);

        Set<Constraint> hardConstraints = Set.of(matheNichtMorgens, matheNichtMontags, bwlNichtMontags);
        Set<Constraint> softConstraints = Set.of(bwlNichtDienstags, bwlNichtMittwochs, bwlNichtDonnerstags);

        Population population = new Population(hardConstraints, softConstraints, mathe, bwl);

        int evolutions = 10000;

        for (int i = 0; i < evolutions; i++) {
            TimeTable b = population.getBest();
            System.out.println(b);
            System.out.println("------");

            if (!b.isPerfect())  {
                population = population.evolve();
            } else {
                System.out.println("PERFECT after " + i + " evolutions");
                break;
            }
        }

    }
}
