package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Boar extends Omnivorous {

    public Boar(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.boarMaxMovement + 1) - DefaultValues.boarMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.boarMaxMovement + 1) - DefaultValues.boarMaxMovement;

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
        int requiredMeatCount = (int) Math.ceil(DefaultValues.boarSatiety);
        int eatenMeatCount = 0;

        Iterator<Object> iterator = cell.iterator();

        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (obj instanceof Caterpillar) {
                double random = Math.random();
                if (random <= 0.9 && totalEatenWeight + ((Caterpillar) obj).getWeightAnimal() <= DefaultValues.boarSatiety) {
                    totalEatenWeight += ((Caterpillar) obj).getWeightAnimal();
                    eatenMeatCount++;
                    iterator.remove();
                }
            } else if (obj instanceof Mouse) {
                double random = Math.random();
                if (random <= 0.5 && totalEatenWeight + ((Mouse) obj).getWeightAnimal() <= DefaultValues.boarSatiety) {
                    totalEatenWeight += ((Mouse) obj).getWeightAnimal();
                    eatenMeatCount++;
                    iterator.remove();
                }
            } else if (obj instanceof Grass) {
                if (totalEatenWeight + DefaultValues.grassWeight <= DefaultValues.boarSatiety) {
                    totalEatenWeight += DefaultValues.grassWeight;
                    eatenMeatCount++;
                    iterator.remove();
                }
            }

            if (eatenMeatCount >= requiredMeatCount && totalEatenWeight >= DefaultValues.boarSatiety) {
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

        int boarCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Boar) {
                boarCount++;
                Boar boar = (Boar) obj;
                if (boar.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (boar.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (boarCount < DefaultValues.boarMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.boarMaxAnimalsPerCell - boarCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newBoarCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newBoarCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Boar newBoar = new Boar(DefaultValues.allXpAnimal, DefaultValues.boarWeight, randomGender, x, y);

                cell.add(newBoar);
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
