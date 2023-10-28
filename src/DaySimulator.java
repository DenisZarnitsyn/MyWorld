import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaySimulator {
    public static void simulateDay(Island island) {
        // Phase 1: Grass Growth
        Grass grass = new Grass();

        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                grass.growthGrass(island, DefaultValues.grassWeight, x, y);
            }
        }

        // Phase 2: Movement
        List<Animal> animalsToMove = new ArrayList<>();
        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                Map<Integer, List<Object>> xMap = island.getGrid().get(x);
                if (xMap != null) {
                    List<Object> cell = xMap.get(y);
                    if (cell != null) {
                        for (Object obj : cell) {
                            if (obj instanceof Animal) {
                                animalsToMove.add((Animal) obj);
                            }
                        }
                    }
                }
            }
        }

        for (Animal animal : animalsToMove) {
            animal.moveAnimal(island);
        }

        // Phase 3: Eating
        List<Herbivore> herbivoresToEat = new ArrayList<>();
        for (Animal animal : animalsToMove) {
            if (animal instanceof Herbivore) {
                herbivoresToEat.add((Herbivore) animal);
            }
        }

        for (Herbivore herbivore : herbivoresToEat) {
            herbivore.eatGrass(island);
        }

        // Phase 4: Reproduction
        List<Animal> animalsToReproduce = new ArrayList<>();
        for (Animal animal : animalsToMove) {
            animalsToReproduce.add(animal);
        }

        for (Animal animal : animalsToReproduce) {
            animal.reproductionAnimal(island);
        }

        // Phase 5: Dead
        List<Animal> animalsToRemove = new ArrayList<>();
        for (Animal animal : animalsToMove) {
            if (animal.getXpAnimal() < 0) {
                animalsToRemove.add(animal);
            }
        }

        for (Animal animal : animalsToRemove) {
            animal.dieAnimal(island);
        }

        // Update statistics
        IslandStatistics.countIslandObjectsWithAnimals(island.getGrid());
    }
}
