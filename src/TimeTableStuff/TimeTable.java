package TimeTableStuff;

import TimeTableStuff.Constraint.Constraint;

import java.util.HashSet;
import java.util.Set;

public class TimeTable implements Comparable<TimeTable> {

    public static float mutation_rate = 0.05f;

    public static float mutation_add_instruction_rate = 0.01f;

    // dont allow more than x mutations at once
    public static int mutation_limit = 10;

    public static int default_instruction_size = 10;

    private Float fitness = null;

    private final Schedule schedule;

    private final Set<Constraint> hardConstraints;

    private final Set<Constraint> softConstraints;

    public TimeTable(Set<Constraint> hardConstraints, Set<Constraint> softConstraints, Event... events) {
        this.schedule = new Schedule();
        for (Event e : events) {
            this.schedule.addRandomly(e.copy());
        }

        this.hardConstraints = hardConstraints;
        this.softConstraints = softConstraints;
    }

    public TimeTable(Schedule schedule, Set<Constraint> hardConstraints, Set<Constraint> softConstraints) {
        this.schedule = schedule.copy();
        this.hardConstraints = hardConstraints;
        this.softConstraints = softConstraints;
    }

    public void addHardConstraint(Constraint c) {
        this.hardConstraints.add(c);
    }

    public void addSoftConstraints(Constraint c) {
        this.softConstraints.add(c);
    }

    public TimeTable getOffspring() {
        TimeTable copy = copy();

        // TODO mutate

        return copy;
    }

    public float getFitness() {
        if (fitness != null) {
            return fitness;
        }

        int hc_size = getHardConstraints().size();
        int hc_satisfied = getSatisfiedHardConstraints().size();

        int sc_size = getSoftConstraints().size();
        int sc_satisfied = getSatisfiedSoftConstraints().size();

        // TODO Abfangen falls hardConstraints oder softConstraints leere liste ist
        //  da dann division durch 0 -> NaN -> Fitness sagt nichts aus

        float hc_percentage = (float) hc_satisfied / hc_size;
        float sc_percantage = (float) sc_satisfied / sc_size;

        // Führt zu großen Zahlen für hc_valued wenn es viele softConstraints gibt
        // eventuell geschickter lösen
        float hc_valued = hc_percentage * (sc_size + 1);
        float sc_valued = sc_percantage;

        fitness = hc_valued + sc_valued;

        return fitness;
    }

    @Override
    public String toString() {
        Set<Event> events = schedule.getEvents();
        StringBuilder result = new StringBuilder();

        for (Event e : events) {
            result.append(e.toString()).append("\n");
        }

        int amountHC = getHardConstraints().size();
        int satisfiedHC = getSatisfiedHardConstraints().size();

        int amountSC = getSoftConstraints().size();
        int satisfiedSC = getSatisfiedSoftConstraints().size();

        result.append("HardConstraints: ").append(satisfiedHC).append("/").append(amountHC).append("\n");
        result.append("SoftConstraints: ").append(satisfiedSC).append("/").append(amountSC).append("\n");
        result.append("Fitness: ").append(getFitness()).append("\n");


        return result.toString();
    }

    public Set<Constraint> getHardConstraints() {
        return hardConstraints;
    }

    public Set<Constraint> getSatisfiedHardConstraints() {
        return getSatisfiedConstraints(hardConstraints);
    }

    public Set<Constraint> getSoftConstraints() {
        return softConstraints;
    }

    public Set<Constraint> getSatisfiedSoftConstraints() {
        return getSatisfiedConstraints(softConstraints);
    }

    public Set<Constraint> getSatisfiedConstraints(Set<Constraint> constraints) {
        Set<Constraint> result = new HashSet<>();

        for (Constraint c : constraints) {
            if (c.isSatisfied(this)) {
                result.add(c);
            }
        }

        return result;
    }

    @Override
    public int compareTo(TimeTable o) {
        float me = getFitness();
        float other = o.getFitness();

        if (me == other) {
            return 0;
        } else {
            return me < other ? -1 : 1;
        }
    }

    public TimeTable copy() {
        return new TimeTable(schedule, hardConstraints, softConstraints);
    }

    public Event getEventByName(String name) {
        Event result = null;

        for (Event e : schedule.getEvents()) {
            if (e.getName() == name) {
                return e;
            }
        }

        return result;
    }
}
