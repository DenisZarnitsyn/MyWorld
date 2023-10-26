import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Island {
    private int sizeX;
    private int sizeY;
    private Map<Integer, Map<Integer, List<Object>>> grid;

    public Island() {}
    public Island(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grid = new HashMap<>();
        initializeIsland();
    }

    public void initializeIsland() {
        Grass grass = new Grass();
        for (int x = 0; x < sizeX; x++) {
            grid.put(x, new HashMap<>());
            for (int y = 0; y < sizeY; y++) {
                grid.get(x).put(y, new ArrayList<>());
                grass.growthGrass(this, x, y);
                populateCellWithRabbits(x, y);
            }
        }
    }


    public void simulateDay() {
        Grass grass = new Grass();
        Rabbit rabbit
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                grass.growthGrass(this, x, y);
            }
        }
         moveRabbits();
//        eatGrass();
//        reproduceRabbits();
//        removeDeadAnimals();
    }
        public void populateCellWithRabbits(int x, int y) {
        int currentRabbitsCount = countRabbitsInCell(x, y);
        int maxGrassToAdd = DefaultValues.rabbitMaxAnimalsPerCell - currentRabbitsCount;

        Random random = new Random();
        int rabbitCount = random.nextInt(maxGrassToAdd + 1);

        for (int i = 0; i < rabbitCount; i++) {

            Rabbit rabbit = new Rabbit(DefaultValues.rabbitWeight, DefaultValues.rabbitCurrentWeight, DefaultValues.rabbitMaxAnimalsPerCell, DefaultValues.rabbitMaxMovement, DefaultValues.rabbitSatiety, x, y);
            grid.get(x).get(y).add(rabbit);
        }
    }

    private int countRabbitsInCell(int x, int y) {
        int RabbitsCount = 0;
        for (Object obj : grid.get(x).get(y)) {
            if (obj instanceof Rabbit) {
                RabbitsCount++;
            }
        }
        return RabbitsCount;
    }

    public void printIslandObjects() {
        IslandStatistics.countIslandObjects(grid);
//        IslandStatistics.printAllObjects(grid);
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Map<Integer, Map<Integer, List<Object>>> getGrid() {
        return grid;
    }

    public void setGrid(Map<Integer, Map<Integer, List<Object>>> grid) {
        this.grid = grid;
    }
}
