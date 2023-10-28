import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Sheep extends Herbivore {

    public Sheep(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.sheepMaxMovement + 1) - DefaultValues.sheepMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.sheepMaxMovement + 1) - DefaultValues.sheepMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManySheepInCell(newX, newY, island)) {
                int currentX = getCoordinateAnimalX();
                int currentY = getCoordinateAnimalY();

                island.getGrid().get(currentX).get(currentY).remove(this);

                setCoordinateAnimalX(newX);
                setCoordinateAnimalY(newY);

                island.getGrid().get(newX).get(newY).add(this);
            }
        }
    }

    @Override
    public int eatGrass(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int requiredGrassCount = (int) Math.ceil(DefaultValues.sheepSatiety);
        int eatenGrassCount = 0;

        Iterator<Object> iterator = cell.iterator();
        while (iterator.hasNext() && eatenGrassCount < requiredGrassCount) {
            Object obj = iterator.next();
            if (obj instanceof Grass) {
                Grass grass = (Grass) obj;

                eatenGrassCount++;
                iterator.remove();

                if (eatenGrassCount == requiredGrassCount) {
                    setXpAnimal(DefaultValues.allXpAnimal);
                    return eatenGrassCount;
                }
            }
        }

        return eatenGrassCount;
    }


    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int sheepCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Sheep) {
                sheepCount++;
                Sheep sheep = (Sheep) obj;
                if (sheep.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (sheep.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (sheepCount < DefaultValues.sheepMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.sheepMaxAnimalsPerCell - sheepCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newSheepCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newSheepCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Sheep newSheep = new Sheep(DefaultValues.allXpAnimal, DefaultValues.sheepWeight, randomGender, x, y);

                cell.add(newSheep);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManySheepInCell(int x, int y, Island island) {
        int sheepCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Sheep) {
                sheepCount++;
                if (sheepCount >= DefaultValues.sheepMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
