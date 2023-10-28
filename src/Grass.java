import java.util.Random;

public class Grass {

    private double weightGrass;
    private int coordinateGrassX;
    private int coordinateGrassY;

    public Grass() {
    }

    public Grass(double weightGrass, int coordinateGrassX, int coordinateGrassY) {
        this.weightGrass = weightGrass;
        this.coordinateGrassX = coordinateGrassX;
        this.coordinateGrassY = coordinateGrassY;
    }

    public int growthGrass(Island island, double weightAnimal, int x, int y) {
        int currentGrassCount = countGrassInCell(island, x, y);
        int maxGrassToAdd = DefaultValues.grassMaximumPerCell - 200;

        Random random = new Random();
        int grassCount = random.nextInt(maxGrassToAdd + 1);

        for (int i = 0; i < grassCount; i++) {
            Grass grass = new Grass(weightAnimal, x, y);
            island.getGrid().get(x).get(y).add(grass);
            IslandStatistics.incrementDailyGrassGenerated();
        }

        return grassCount;
    }


    private int countGrassInCell(Island island, int x, int y) {
        int grassCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Grass) {
                grassCount++;
            }
        }
        return grassCount;
    }

    public double getWeightGrass() {
        return weightGrass;
    }

    public void setWeightGrass(double weightAnimal) {
        this.weightGrass = weightAnimal;
    }

    public int getCoordinateGrassX() {
        return coordinateGrassX;
    }

    public void setCoordinateGrassX(int coordinateGrassX) {
        this.coordinateGrassX = coordinateGrassX;
    }

    public int getCoordinateGrassY() {
        return coordinateGrassY;
    }

    public void setCoordinateGrassY(int coordinateGrassY) {
        this.coordinateGrassY = coordinateGrassY;
    }
}
