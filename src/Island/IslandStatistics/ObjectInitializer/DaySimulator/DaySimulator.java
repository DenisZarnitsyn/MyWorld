package Island.IslandStatistics.ObjectInitializer.DaySimulator;

import Animals.Grass.*;
import DefaultValues.Gender.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaySimulator {

    public static void simulateDay(Island island) {

        List<Animal> animalsToMove = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            // Phase 1: Grass Growth
            Runnable grassGrowthTask = () -> {
                synchronized (island) {
                    for (int x = 0; x < island.getSizeX(); x++) {
                        for (int y = 0; y < island.getSizeY(); y++) {
                            Grass grass = new Grass();
                            grass.growthGrass(island, DefaultValues.grassWeight, x, y);
                        }
                    }
                }
            };
            executor.execute(grassGrowthTask);

            // Phase 2: Movement
            Runnable movementTask = () -> {
                synchronized (island) {
                    animalsToMove.clear(); // Clear the list before populating it

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
                }
            };
            executor.execute(movementTask);

            // Phase 3: Eating
            Runnable eatingTask = () -> {
                synchronized (island) {
                    List<Herbivore> herbivoresToEat = new ArrayList<>();
                    List<Omnivorous> omnivoresToEat = new ArrayList<>();
                    List<Carnivore> carnivoresToEat = new ArrayList<>();

                    for (Animal animal : animalsToMove) {
                        if (animal instanceof Herbivore) {
                            herbivoresToEat.add((Herbivore) animal);
                        } else if (animal instanceof Omnivorous) {
                            omnivoresToEat.add((Omnivorous) animal);
                        } else if (animal instanceof Carnivore) {
                            carnivoresToEat.add((Carnivore) animal);
                        }
                    }

                    for (Herbivore herbivore : herbivoresToEat) {
                        herbivore.eatGrass(island);
                    }

                    for (Omnivorous omnivore : omnivoresToEat) {
                        omnivore.eatEverything(island);
                    }

                    for (Carnivore carnivore : carnivoresToEat) {
                        carnivore.eatMeat(island);
                    }
                }
            };
            executor.execute(eatingTask);

            // Phase 4: Reproduction
            Runnable reproductionTask = () -> {
                synchronized (island) {
                    List<Animal> animalsToReproduce = new ArrayList<>(animalsToMove);
                    for (Animal animal : animalsToReproduce) {
                        animal.reproductionAnimal(island);
                    }
                }
            };
            executor.execute(reproductionTask);

            // Phase 5: Dead
            Runnable removalTask = () -> {
                synchronized (island) {
                    List<Animal> animalsToRemove = new ArrayList<>();
                    for (Animal animal : animalsToMove) {
                        if (animal.getXpAnimal() < 0) {
                            animalsToRemove.add(animal);
                        }
                    }

                    for (Animal animal : animalsToRemove) {
                        animal.dieAnimal(island);
                    }
                }
            };
            executor.execute(removalTask);

            executor.shutdown();

            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        } finally {
            executor.shutdownNow();
        }

        // Update statistics
        IslandStatistics.countIslandObjectsWithAnimals(island.getGrid());
        IslandStatistics.countTotalIslandObjects(island.getGrid());
    }
}
