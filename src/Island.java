import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Island {
    private int sizeX;
    private int sizeY;
    private Map<Integer, Map<Integer, List<Object>>> grid;

    public Island(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grid = new HashMap<>();

        for (int x = 0; x < sizeX; x++) {
            grid.put(x, new HashMap<>());
            for (int y = 0; y < sizeY; y++) {
                grid.get(x).put(y, new ArrayList<>());
                populateCellWithGrass(x, y);
                populateCellWithRabbits(x, y);
            }
        }
    }

    public void populateCellWithGrass(int x, int y) {
        int maxGrassPerCell = 200;
        Random random = new Random();
        int grassCount = random.nextInt(maxGrassPerCell + 1);

        for (int i = 0; i < grassCount; i++) {
//            int idGrass = i;

            Grass grass = new Grass(DefaultValues.grassWeight, DefaultValues.grassMaximumPerCell, x, y);
            grid.get(x).get(y).add(grass);
        }
    }

    public void populateCellWithRabbits(int x, int y) {
        int maxRabbitsPerCell = 150;
        Random random = new Random();
        int rabbitCount = random.nextInt(maxRabbitsPerCell + 1);

        for (int i = 0; i < rabbitCount; i++) {
//            int idAnimal = i;
            String nameAnimal = "Rabbit";

            Rabbit rabbit = new Rabbit(DefaultValues.rabbitWeight, DefaultValues.rabbitCurrentWeight, DefaultValues.rabbitMaxAnimalsPerCell, DefaultValues.rabbitMaxMovement, DefaultValues.rabbitSatiety, x, y);
            grid.get(x).get(y).add(rabbit);
        }
    }

    public void printIslandObjects() {
        IslandStatistics.countIslandObjects(grid);
        IslandStatistics.printAllObjects(grid);
    }
}
