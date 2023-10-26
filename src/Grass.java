import java.util.Random;

public class Grass {

    //    private int idGrass;
    private double weightGrass;
    private int maximumPerCell;

    private int coordinateGrassX;
    private int coordinateGrassY;

    public Grass(){}
    public Grass(double weightGrass, int maximumPerCell, int coordinateGrassX, int coordinateGrassY) {
        this.weightGrass = weightGrass;
        this.maximumPerCell = maximumPerCell;
        this.coordinateGrassX = coordinateGrassX;
        this.coordinateGrassY = coordinateGrassY;
    }

    public void growthGrass(Island island, int x, int y) {
        int currentGrassCount = countGrassInCell(island, x, y);
        int maxGrassToAdd = DefaultValues.grassMaximumPerCell - currentGrassCount;

        Random random = new Random();
        int grassCount = random.nextInt(maxGrassToAdd + 1);

        for (int i = 0; i < grassCount; i++) {
            Grass grass = new Grass(DefaultValues.grassWeight, DefaultValues.grassMaximumPerCell, x, y);
            island.getGrid().get(x).get(y).add(grass);
        }
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

    public void setWeightGrass(double weightGrass) {
        this.weightGrass = weightGrass;
    }

    public int getMaximumPerCell() {
        return maximumPerCell;
    }

    public void setMaximumPerCell(int maximumPerCell) {
        this.maximumPerCell = maximumPerCell;
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