import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Goat extends Herbivore {

    public Goat(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.goatMaxMovement + 1) - DefaultValues.goatMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.goatMaxMovement + 1) - DefaultValues.goatMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyGoatsInCell(newX, newY, island)) {
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

        int requiredGrassCount = (int) Math.ceil(DefaultValues.goatSatiety);
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

        int goatCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Goat) {
                goatCount++;
                Goat goat = (Goat) obj;
                if (goat.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (goat.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (goatCount < DefaultValues.goatMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.goatMaxAnimalsPerCell - goatCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newGoatCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newGoatCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Goat newGoat = new Goat(DefaultValues.allXpAnimal, DefaultValues.goatWeight, randomGender, x, y);

                cell.add(newGoat);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManyGoatsInCell(int x, int y, Island island) {
        int goatCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Goat) {
                goatCount++;
                if (goatCount >= DefaultValues.goatMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
