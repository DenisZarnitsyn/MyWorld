package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
public class Deer extends Herbivore {

    public Deer(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.deerMaxMovement + 1) - DefaultValues.deerMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.deerMaxMovement + 1) - DefaultValues.deerMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyDeersInCell(newX, newY, island)) {
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
    public void eatGrass(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int requiredGrassCount = (int) Math.ceil(DefaultValues.deerSatiety);
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
                }
            }
        }
    }


    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int deerCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Deer) {
                deerCount++;
                Deer deer = (Deer) obj;
                if (deer.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (deer.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (deerCount < DefaultValues.deerMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.deerMaxAnimalsPerCell - deerCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newDeerCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newDeerCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Deer newDeer = new Deer(DefaultValues.allXpAnimal, DefaultValues.deerWeight, randomGender, x, y);

                cell.add(newDeer);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManyDeersInCell(int x, int y, Island island) {
        int deerCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Deer) {
                deerCount++;
                if (deerCount >= DefaultValues.deerMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
