import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Wolf extends Carnivore {

    public Wolf(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.wolfMaxMovement + 1) - DefaultValues.wolfMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.wolfMaxMovement + 1) - DefaultValues.wolfMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyWolvesInCell(newX, newY, island)) {
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

        int requiredMeatCount = (int) Math.ceil(DefaultValues.wolfSatiety);
        int eatenMeatCount = 0;
        double eatenWeight = 0.0;

        while (eatenMeatCount < requiredMeatCount && !priorityQueue.isEmpty()) {
            Animal animal = priorityQueue.poll();

            if (eatenWeight + animal.getWeightAnimal() <= DefaultValues.wolfSatiety) {
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
            return 0.8;
        } else if (animal instanceof Sheep) {
            return 0.7;
        } else if (animal instanceof Rabbit || animal instanceof Goat ) {
            return 0.6;
        } else if (animal instanceof Duck) {
            return 0.4;
        } else if (animal instanceof Deer || animal instanceof Boar) {
            return 0.15;
        } else if (animal instanceof Buffalo || animal instanceof Horse) {
            return 0.1;
        }
        return 0.0;
    }

    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int wolfCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Wolf) {
                wolfCount++;
                Wolf wolf = (Wolf) obj;
                if (wolf.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (wolf.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (wolfCount < DefaultValues.wolfMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.wolfMaxAnimalsPerCell - wolfCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newWolfCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newWolfCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Wolf newWolf = new Wolf(DefaultValues.allXpAnimal, DefaultValues.wolfWeight, randomGender, x, y);

                cell.add(newWolf);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManyWolvesInCell(int x, int y, Island island) {
        int wolfCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Wolf) {
                wolfCount++;
                if (wolfCount >= DefaultValues.wolfMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
