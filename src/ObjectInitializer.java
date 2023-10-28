import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectInitializer {
    public static void initializeIsland(Island island) {
        Grass grass = new Grass();

        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                if (!island.getGrid().containsKey(x)) {
                    island.getGrid().put(x, new HashMap<>());
                }
                island.getGrid().get(x).put(y, new ArrayList<>());

                for (int i = 0; i < 10; i++) {
                    grass.growthGrass(island, DefaultValues.grassWeight, x, y);
                }
                populateCellWithRabbits(island, x, y);
            }
        }
    }

    private static void populateCellWithRabbits(Island island, int x, int y) {
        int maxRabbitsToAdd = DefaultValues.rabbitMaxAnimalsPerCell;
        maxRabbitsToAdd = Math.min(maxRabbitsToAdd, 10);

        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                for (int i = 0; i < maxRabbitsToAdd; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    Rabbit rabbit = new Rabbit(DefaultValues.allXpAnimal, DefaultValues.rabbitWeight, randomGender, x, y);
                    cell.add(rabbit);
                }
            }
        }
    }
}

//    public static void initializeIsland(Island island) {
//        Grass grass = new Grass();
//
//        for (int x = 0; x < island.getSizeX(); x++) {
//            for (int y = 0; y < island.getSizeY(); y++) {
//                if (!island.getGrid().containsKey(x)) {
//                    island.getGrid().put(x, new HashMap<>());
//                }
//                island.getGrid().get(x).put(y, new ArrayList<>());
//
//                grass.growthGrass(island, DefaultValues.grassWeight, x, y);
//                populateCellWithRabbits(island, x, y);
//            }
//        }
//    }

//    private static void populateCellWithRabbits(Island island, int x, int y) {
//        Random random = new Random();
//        int maxRabbitsToAdd = DefaultValues.rabbitMaxAnimalsPerCell;
//        int rabbitCount = random.nextInt(maxRabbitsToAdd + 1);
//
//        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
//        if (xMap != null) {
//            List<Object> cell = xMap.get(y);
//            if (cell != null) {
//                for (int i = 0; i < rabbitCount; i++) {
//                    Gender randomGender = Gender.getRandomGender();
//                    Rabbit rabbit = new Rabbit(DefaultValues.allXpAnimal, DefaultValues.rabbitWeight, randomGender, x, y);
//                    cell.add(rabbit);
//                }
//            }
//        }
//    }

