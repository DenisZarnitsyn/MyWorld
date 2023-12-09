package Island.IslandStatistics.ObjectInitializer.DaySimulator;

import Animals.Grass.*;
import DefaultValues.Gender.*;
import java.util.*;

public class ObjectInitializer {


    public static void initializeIsland(Island island) {
        Grass grass = new Grass();
        Random random = new Random();

        for (int x = 0; x < island.getSizeX(); x++) {
            for (int y = 0; y < island.getSizeY(); y++) {
                if (!island.getGrid().containsKey(x)) {
                    island.getGrid().put(x, new HashMap<>());
                }
                island.getGrid().get(x).put(y, new ArrayList<>());

                grass.growthGrass(island, DefaultValues.grassWeight, x, y);

                initializeAnimalsInCell(island, x, y, Rabbit.class, DefaultValues.rabbitMaxAnimalsPerCell, DefaultValues.rabbitWeight, random);
                initializeAnimalsInCell(island, x, y, Deer.class, DefaultValues.deerMaxAnimalsPerCell, DefaultValues.deerWeight, random);
                initializeAnimalsInCell(island, x, y, Goat.class, DefaultValues.goatMaxAnimalsPerCell, DefaultValues.goatWeight, random);
                initializeAnimalsInCell(island, x, y, Sheep.class, DefaultValues.sheepMaxAnimalsPerCell, DefaultValues.sheepWeight, random);
                initializeAnimalsInCell(island, x, y, Buffalo.class, DefaultValues.buffaloMaxAnimalsPerCell, DefaultValues.buffaloWeight, random);
                initializeAnimalsInCell(island, x, y, Caterpillar.class, DefaultValues.caterpillarMaxAnimalsPerCell, DefaultValues.caterpillarWeight, random);
                initializeAnimalsInCell(island, x, y, Wolf.class, DefaultValues.wolfMaxAnimalsPerCell, DefaultValues.wolfWeight, random);
                initializeAnimalsInCell(island, x, y, Snake.class, DefaultValues.snakeMaxAnimalsPerCell, DefaultValues.snakeWeight, random);
                initializeAnimalsInCell(island, x, y, Fox.class, DefaultValues.foxMaxAnimalsPerCell, DefaultValues.foxWeight, random);
                initializeAnimalsInCell(island, x, y, Bear.class, DefaultValues.bearMaxAnimalsPerCell, DefaultValues.bearWeight, random);
                initializeAnimalsInCell(island, x, y, Eagle.class, DefaultValues.eagleMaxAnimalsPerCell, DefaultValues.eagleWeight, random);
                initializeAnimalsInCell(island, x, y, Mouse.class, DefaultValues.mouseMaxAnimalsPerCell, DefaultValues.mouseWeight, random);
                initializeAnimalsInCell(island, x, y, Boar.class, DefaultValues.boarMaxAnimalsPerCell, DefaultValues.boarWeight, random);
                initializeAnimalsInCell(island, x, y, Duck.class, DefaultValues.duckMaxAnimalsPerCell, DefaultValues.duckWeight, random);
            }
        }
    }

    private static void initializeAnimalsInCell(Island island, int x, int y, Class<?> animalClass, int maxAnimalsPerCell, double animalWeight, Random random) {
        Map<Integer, List<Object>> xMap = island.getGrid().get(x);
        if (xMap != null) {
            List<Object> cell = xMap.get(y);
            if (cell != null) {
                int maxAnimalsToAdd = maxAnimalsPerCell;
                int animalCount = random.nextInt(maxAnimalsToAdd + 1);

                for (int i = 0; i < animalCount; i++) {
                    Gender randomGender = Gender.getRandomGender();
                    try {
                        Object animal = animalClass.getDeclaredConstructor(int.class, double.class, Gender.class, int.class, int.class)
                                .newInstance(DefaultValues.allXpAnimal, animalWeight, randomGender, x, y);
                        cell.add(animal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
