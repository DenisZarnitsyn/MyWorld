import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Bear extends Carnivore {

    public Bear(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.bearMaxMovement + 1) - DefaultValues.bearMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.bearMaxMovement + 1) - DefaultValues.bearMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyBearsInCell(newX, newY, island)) {
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

        int requiredMeatCount = (int) Math.ceil(DefaultValues.bearSatiety);
        int eatenMeatCount = 0;
        double eatenWeight = 0.0;

        while (eatenMeatCount < requiredMeatCount && !priorityQueue.isEmpty()) {
            Animal animal = priorityQueue.poll();

            if (eatenWeight + animal.getWeightAnimal() <= DefaultValues.bearSatiety) {
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
        } else if (animal instanceof Snake || animal instanceof Rabbit || animal instanceof Deer) {
            return 0.8;
        } else if (animal instanceof Sheep || animal instanceof Goat) {
            return 0.7;
        } else if (animal instanceof Boar) {
            return 0.5;
        } else if (animal instanceof Horse) {
            return 0.4;
        } else if (animal instanceof Buffalo) {
            return 0.2;
        } else if (animal instanceof Duck) {
            return 0.1;
        }
        return 0.0;
    }

    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int bearCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Bear) {
                bearCount++;
                Bear bear = (Bear) obj;
                if (bear.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (bear.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (bearCount < DefaultValues.bearMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.bearMaxAnimalsPerCell - bearCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newBearCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newBearCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Bear newBear = new Bear(DefaultValues.allXpAnimal, DefaultValues.bearWeight, randomGender, x, y);

                cell.add(newBear);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManyBearsInCell(int x, int y, Island island) {
        int bearCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Bear) {
                bearCount++;
                if (bearCount >= DefaultValues.bearMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
