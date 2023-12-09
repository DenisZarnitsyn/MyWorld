package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.Iterator;
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

    @Override
    public void eatGrass(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int requiredGrassCount = (int) Math.ceil(DefaultValues.rabbitSatiety);
        int eatenGrassCount = 0;
        Iterator<Object> iterator = cell.iterator();

        while (iterator.hasNext() && eatenGrassCount < requiredGrassCount) {
            Object obj = iterator.next();
            if (obj instanceof Grass) {
                Grass grass = (Grass) obj;

                iterator.remove();
                eatenGrassCount++;

                if (eatenGrassCount == requiredGrassCount) {
                    setXpAnimal(DefaultValues.allXpAnimal);
                }
            }
        }

        if (eatenGrassCount < requiredGrassCount) {
            setXpAnimal(getXpAnimal() - 1);
        }
    }

    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int rabbitCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Rabbit) {
                rabbitCount++;
                Rabbit rabbit = (Rabbit) obj;
                if (rabbit.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (rabbit.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (rabbitCount < DefaultValues.rabbitMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.rabbitMaxAnimalsPerCell - rabbitCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newRabbitCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newRabbitCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Rabbit newRabbit = new Rabbit(DefaultValues.allXpAnimal, DefaultValues.rabbitWeight, randomGender, x, y);

                cell.add(newRabbit);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
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
}
