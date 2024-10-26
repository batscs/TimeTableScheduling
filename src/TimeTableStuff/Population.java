package TimeTableStuff;

import TimeTableStuff.Constraint.Constraint;

import java.util.*;
import java.util.stream.Collectors;

public class Population {

    private final List<TimeTable> population;

    public Population(Set<Constraint> hardConstraints, Set<Constraint> softConstraints, Event... events) {
        population = new LinkedList<>();

        for (int i = 0; i < Settings.POPULATION_SIZE; i++) {
            population.add(new TimeTable(hardConstraints, softConstraints, events));
        }
    }

    public Population(List<TimeTable> newGeneration) {
        this.population = newGeneration;
    }

    public Population evolve() {
        // Schritt 1: Hole die sortierte Liste der TimeTables
        List<TimeTable> sortedTimeTables = getSortedTimeTables();

        // Schritt 2: Bestimme die Anzahl der besten Individuen basierend auf dem THRESHOLD
        int numberOfBestIndividuals = (int) (sortedTimeTables.size() * Settings.POPULATION_THRESHOLD_BEST_INDIVIDUUMS);

        // Schritt 3: Hole die besten Individuen
        List<TimeTable> bestIndividuals = sortedTimeTables.subList(0, numberOfBestIndividuals);

        // Schritt 4: Erstelle eine neue Liste für die nächste Generation
        List<TimeTable> newGeneration = new ArrayList<>();

        // Füge Kopien der besten Individuen hinzu
        for (TimeTable timeTable : bestIndividuals) {
            newGeneration.add(timeTable.copy());
        }

        // Erzeuge Nachkommen, bis die neue Generation die POPULATION_SIZE erreicht
        while (newGeneration.size() < Settings.POPULATION_SIZE) {
            // Single Parent Approach (TODO: Compare Result with Two Parents Cross Over)

            // Wähle zufällig ein Individuum als Eltern
            TimeTable parent = bestIndividuals.get((int) (Math.random() * bestIndividuals.size()));

            // Generiere einen Nachkommen
            TimeTable offspring = parent.getOffspring();

            // Füge den Nachkommen zur neuen Generation hinzu
            newGeneration.add(offspring);
        }

        // Schritt 5: Erstelle eine neue TimeTableStuff.Population mit der neuen Generation und gib diese zurück
        Population evolvedPopulation = new Population(newGeneration);
        return evolvedPopulation;
    }

    public TimeTable getBest() {
        TimeTable best = population.stream().max(TimeTable::compareTo).orElse(null);

        if (best == null) {
            throw new RuntimeException("No TimeTable in TimeTableStuff.Population");
        }

        return best;
    }

    public List<TimeTable> getSortedTimeTables() {
        return population.stream()
                .sorted(TimeTable::compareTo)
                .collect(Collectors.toList());
    }

    public List<TimeTable> getPopulation() {
        List<TimeTable> result = new LinkedList<>();

        for (TimeTable t : population) {
            result.add(t.copy());
        }

        return result;
    }
}
