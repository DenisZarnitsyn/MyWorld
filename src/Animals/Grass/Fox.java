package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Fox extends Carnivore {

    public Fox(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.foxMaxMovement + 1) - DefaultValues.foxMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.foxMaxMovement + 1) - DefaultValues.foxMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyFoxesInCell(newX, newY, island)) {
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
    public void eatMeat(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        PriorityQueue<Animal> priorityQueue = new PriorityQueue<>((animal1, animal2) -> {
            double probability1 = getEatProbability(animal1);
            double probability2 = getEatProbability(animal2);
            return Double.compare(probability2, probability1);
        });

        for (Object obj : cell) {
            if (obj instanceof Animal) {
                priorityQueue.add((Animal) obj);
            }
        }

        int requiredMeatCount = (int) Math.ceil(DefaultValues.foxSatiety);
        int eatenMeatCount = 0;
        double eatenWeight = 0.0;

        while (eatenMeatCount < requiredMeatCount && !priorityQueue.isEmpty()) {
            Animal animal = priorityQueue.poll();

            if (eatenWeight + animal.getWeightAnimal() <= DefaultValues.foxSatiety) {
                cell.remove(animal);
                eatenWeight += animal.getWeightAnimal();
                eatenMeatCount++;
            } else {
                break;
            }
        }

        if (eatenMeatCount > 0) {
            setXpAnimal(DefaultValues.allXpAnimal);
        } else {
            setXpAnimal(getXpAnimal() - 1);
        }
    }

    private double getEatProbability(Animal animal) {
        if (animal instanceof Mouse) {
            return 0.9;
        } else if (animal instanceof Rabbit) {
            return 0.7;
        } else if (animal instanceof Duck) {
            return 0.6;
        } else if (animal instanceof Caterpillar) {
            return 0.40;
        }
        return 0.0;
    }

    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int foxCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Fox) {
                foxCount++;
                Fox fox = (Fox) obj;
                if (fox.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (fox.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (foxCount < DefaultValues.foxMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.foxMaxAnimalsPerCell - foxCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newFoxCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newFoxCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Fox newFox = new Fox(DefaultValues.allXpAnimal, DefaultValues.foxWeight, randomGender, x, y);

                cell.add(newFox);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManyFoxesInCell(int x, int y, Island island) {
        int foxCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Fox) {
                foxCount++;
                if (foxCount >= DefaultValues.foxMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
