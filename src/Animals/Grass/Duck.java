package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Duck extends Omnivorous {

    public Duck(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.duckMaxMovement + 1) - DefaultValues.duckMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.duckMaxMovement + 1) - DefaultValues.duckMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            int currentX = getCoordinateAnimalX();
            int currentY = getCoordinateAnimalY();

            island.getGrid().get(currentX).get(currentY).remove(this);

            setCoordinateAnimalX(newX);
            setCoordinateAnimalY(newY);

            island.getGrid().get(newX).get(newY).add(this);
        }
    }

    @Override
    public void eatEverything(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        double totalEatenWeight = 0.0;
        int requiredMeatCount = (int) Math.ceil(DefaultValues.duckSatiety);
        int eatenMeatCount = 0;

        Iterator<Object> iterator = cell.iterator();

        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof Caterpillar) {
                double random = Math.random();
                if (random <= 0.9 && totalEatenWeight + ((Caterpillar) obj).getWeightAnimal() <= DefaultValues.duckSatiety) {
                    totalEatenWeight += ((Caterpillar) obj).getWeightAnimal();
                    eatenMeatCount++;
                    iterator.remove();
                }
            } else if (obj instanceof Grass) {
                if (totalEatenWeight + DefaultValues.grassWeight <= DefaultValues.duckSatiety) {
                    totalEatenWeight += DefaultValues.grassWeight;
                    eatenMeatCount++;
                    iterator.remove();
                }
            }

            if (eatenMeatCount >= requiredMeatCount && totalEatenWeight >= DefaultValues.duckSatiety) {
                break;
            }
        }

        if (eatenMeatCount > 0) {
            setXpAnimal(DefaultValues.allXpAnimal);
        } else {
            setXpAnimal(getXpAnimal() - 1);
        }
    }


    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int duckCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Duck) {
                duckCount++;
                Duck duck = (Duck) obj;
                if (duck.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (duck.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (duckCount < DefaultValues.duckMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.duckMaxAnimalsPerCell - duckCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newDuckCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newDuckCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Duck newDuck = new Duck(DefaultValues.allXpAnimal, DefaultValues.duckWeight, randomGender, x, y);

                cell.add(newDuck);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }
}
