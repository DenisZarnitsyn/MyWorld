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
        int grassToAdd = DefaultValues.grassMaximumPerCell - currentGrassCount;

        if (grassToAdd > 0) {
            for (int i = 0; i < grassToAdd; i++) {
                Grass grass = new Grass(weightAnimal, x, y);
                island.getGrid().get(x).get(y).add(grass);
            }
        }

        return grassToAdd;
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
}
