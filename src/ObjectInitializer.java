import java.util.*;

public class ObjectInitializer {

    public static void initializeIsland(Island island) {
        Grass grass = new Grass();

        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                if (!island.getGrid().containsKey(x)) {
                    island.getGrid().put(x, new HashMap<>());
                }
                island.getGrid().get(x).put(y, new ArrayList<>());

                grass.growthGrass(island, DefaultValues.grassWeight, x, y);

                populateCellWithRabbits(island, x, y);
                populateCellWithDeers(island, x, y);
                populateCellWithGoats(island, x, y);
                populateCellWithSheeps(island, x, y);
                populateCellWithBuffalos(island, x, y);
                populateCellWithCaterpillars(island, x, y);
            }
        }
    }

    private static void populateCellWithRabbits(Island island, int x, int y) {
        Random random = new Random();
        int maxAnimalsToAdd = DefaultValues.rabbitMaxAnimalsPerCell;
        int animalCount = random.nextInt(maxAnimalsToAdd + 1);

        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                for (int i = 0; i < animalCount; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    Rabbit rabbit = new Rabbit(DefaultValues.allXpAnimal, DefaultValues.rabbitWeight, randomGender, x, y);
                    cell.add(rabbit);
                }
            }
        }
    }

    private static void populateCellWithDeers(Island island, int x, int y) {
        Random random = new Random();
        int maxAnimalsToAdd = DefaultValues.deerMaxAnimalsPerCell;
        int animalCount = random.nextInt(maxAnimalsToAdd + 1);

        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                for (int i = 0; i < animalCount; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    Deer deer = new Deer(DefaultValues.allXpAnimal, DefaultValues.deerWeight, randomGender, x, y);
                    cell.add(deer);
                }
            }
        }
    }

    private static void populateCellWithGoats(Island island, int x, int y) {
        Random random = new Random();
        int maxAnimalsToAdd = DefaultValues.goatMaxAnimalsPerCell;
        int animalCount = random.nextInt(maxAnimalsToAdd + 1);

        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                for (int i = 0; i < animalCount; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    Goat goat = new Goat(DefaultValues.allXpAnimal, DefaultValues.goatWeight, randomGender, x, y);
                    cell.add(goat);
                }
            }
        }
    }

    private static void populateCellWithSheeps(Island island, int x, int y) {
        Random random = new Random();
        int maxAnimalsToAdd = DefaultValues.sheepMaxAnimalsPerCell;
        int animalCount = random.nextInt(maxAnimalsToAdd + 1);

        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                for (int i = 0; i < animalCount; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    Sheep sheep = new Sheep(DefaultValues.allXpAnimal, DefaultValues.sheepWeight, randomGender, x, y);
                    cell.add(sheep);
                }
            }
        }
    }

    private static void populateCellWithBuffalos(Island island, int x, int y) {
        Random random = new Random();
        int maxAnimalsToAdd = DefaultValues.buffaloMaxAnimalsPerCell;
        int animalCount = random.nextInt(maxAnimalsToAdd + 1);

        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                for (int i = 0; i < animalCount; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    Buffalo buffalo = new Buffalo(DefaultValues.allXpAnimal, DefaultValues.buffaloWeight, randomGender, x, y);
                    cell.add(buffalo);
                }
            }
        }
    }

    private static void populateCellWithCaterpillars(Island island, int x, int y) {
        Random random = new Random();
        int maxAnimalsToAdd = DefaultValues.caterpillarMaxAnimalsPerCell;
        int animalCount = random.nextInt(maxAnimalsToAdd + 1);

        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                for (int i = 0; i < animalCount; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    Caterpillar caterpillar = new Caterpillar(DefaultValues.allXpAnimal, DefaultValues.caterpillarWeight, randomGender, x, y);
                    cell.add(caterpillar);
                }
            }
        }
    }
}
