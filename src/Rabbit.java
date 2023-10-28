import java.util.List;
import java.util.Random;

public class Rabbit extends Herbivore {

    public Rabbit(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.rabbitMaxMovement + 1) - DefaultValues.rabbitMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.rabbitMaxMovement + 1) - DefaultValues.rabbitMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyRabbitsInCell(newX, newY, island)) {
                int currentX = getCoordinateAnimalX();
                int currentY = getCoordinateAnimalY();

                island.getGrid().get(currentX).get(currentY).remove(this);

                setCoordinateAnimalX(newX);
                setCoordinateAnimalY(newY);

                island.getGrid().get(newX).get(newY).add(this);
            }
        }
    }

    private boolean isTooManyRabbitsInCell(int x, int y, Island island) {
        int rabbitCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Rabbit) {
                rabbitCount++;
                if (rabbitCount >= DefaultValues.rabbitMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }

        @Override
        public int eatGrass(Island island) {
            int x = getCoordinateAnimalX();
            int y = getCoordinateAnimalY();
            List<Object> cell = island.getGrid().get(x).get(y);

            int requiredGrassCount = (int) Math.ceil(DefaultValues.rabbitSatiety);
            int eatenGrassCount = 0;

            for (int i = 0; i < requiredGrassCount; i++) {
                boolean foundGrassToEat = false;

                for (Object obj : cell) {
                    if (obj instanceof Grass) {
                        Grass grass = (Grass) obj;

                        eatenGrassCount++;
                        foundGrassToEat = true;

                        cell.remove(obj);

                        if (eatenGrassCount == requiredGrassCount) {
                            setXpAnimal(DefaultValues.allXpAnimal);
                            return eatenGrassCount;
                        }
                    }
                }

                if (!foundGrassToEat) {
                    setXpAnimal(getXpAnimal() - 1);
                }
            }

            return eatenGrassCount;
        }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }
}
