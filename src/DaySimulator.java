import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaySimulator {
    public static void simulateDay(Island island) {
        // Phase 1: Grass Growth
        Grass grass = new Grass();
        int grassAdded = 0;

        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                grassAdded += grass.growthGrass(island, DefaultValues.grassWeight, x, y);
            }
        }

        // Phase 2: Movement
        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                Map<Integer, List<Object>> xMap = island.getGrid().get(x);
                if (xMap != null) {
                    List<Object> cell = xMap.get(y);
                    if (cell != null) {
                        List<Rabbit> rabbitsToMove = new ArrayList<>();
                        for (Object obj : cell) {
                            if (obj instanceof Rabbit) {
                                rabbitsToMove.add((Rabbit) obj);
                            }
                        }

                        for (Rabbit rabbit : rabbitsToMove) {
                            rabbit.moveAnimal(island);
                        }
                    }
                }
            }
        }

        // Phase 3: Eating
        int grassEaten = 0;
        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                Map<Integer, List<Object>> xMap = island.getGrid().get(x);
                if (xMap != null) {
                    List<Object> cell = xMap.get(y);
                    if (cell != null) {
                        List<Rabbit> rabbitsToEat = new ArrayList<>();
                        for (Object obj : cell) {
                            if (obj instanceof Rabbit) {
                                rabbitsToEat.add((Rabbit) obj);
                            }
                        }

                        for (Rabbit rabbit : rabbitsToEat) {
                            grassEaten += rabbit.eatGrass(island);
                        }
                    }
                }
            }
        }
        // Phase 4: Dead
        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                Map<Integer, List<Object>> xMap = island.getGrid().get(x);
                if (xMap != null) {
                    List<Object> cell = xMap.get(y);
                    if (cell != null) {
                        List<Rabbit> rabbitsToRemove = new ArrayList<>();
                        for (Object obj : cell) {
                            if (obj instanceof Rabbit) {
                                Rabbit rabbit = (Rabbit) obj;
                                if (rabbit.getXpAnimal() < 0) {
                                    rabbitsToRemove.add(rabbit);
                                }
                            }
                        }

                        for (Rabbit rabbit : rabbitsToRemove) {
                            rabbit.dieAnimal(island);
                        }
                    }
                }
            }
        }


        // Update statistics
        IslandStatistics.countIslandObjects(island.getGrid());
        IslandStatistics.countTotalIslandObjects(island.getGrid(), grassAdded, grassEaten);
    }
}
