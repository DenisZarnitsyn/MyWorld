package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Horse extends Herbivore {

    public Horse(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.horseMaxMovement + 1) - DefaultValues.horseMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.horseMaxMovement + 1) - DefaultValues.horseMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyHorsesInCell(newX, newY, island)) {
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

        int requiredGrassCount = (int) Math.ceil(DefaultValues.horseSatiety);
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
                    }
                }
            }

            if (!foundGrassToEat) {
                setXpAnimal(getXpAnimal() - 1);
            }
        }
    }

    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int horseCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Horse) {
                horseCount++;
                Horse horse = (Horse) obj;
                if (horse.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (horse.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (horseCount < DefaultValues.horseMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.horseMaxAnimalsPerCell - horseCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newHorseCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newHorseCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Horse newHorse = new Horse(DefaultValues.allXpAnimal, DefaultValues.horseWeight, randomGender, x, y);

                cell.add(newHorse);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManyHorsesInCell(int x, int y, Island island) {
        int horseCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Horse) {
                horseCount++;
                if (horseCount >= DefaultValues.horseMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
