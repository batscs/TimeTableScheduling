import TimeTableStuff.Constraint.Constraint;
import TimeTableStuff.TimeTable;
import TimeTableStuff.Event;

import java.util.*;

public class Population {

    private List<TimeTable> population;

    private static final float THRESHOLD_BEST_INDIVIDUUMS = 0.1f;

    private static final int POPULATION_SIZE = 20;

    public Population(Set<Constraint> hardConstraints, Set<Constraint> softConstraints, Event... events) {
        population = new LinkedList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new TimeTable(hardConstraints, softConstraints, events));
        }
    }

    public Population evolve() {
        // TODO Get best 10% oder so of current population
        //  -> THRESHOLD_BEST_INDIVIDUUMS

        // include them and generate offsprings

        // return new evolved population as a result
        return null;
    }

    public TimeTable getBest() {
        TimeTable best = population.stream().max(TimeTable::compareTo).orElse(null);

        if (best == null) {
            throw new RuntimeException("No TimeTable in Population");
        }

        return best;
    }

    public List<TimeTable> getPopulation() {
        List<TimeTable> result = new LinkedList<>();

        for (TimeTable t : population) {
            result.add(t.copy());
        }

        return result;
    }
}
