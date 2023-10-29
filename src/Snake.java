import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Snake extends Carnivore {

    public Snake(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.snakeMaxMovement + 1) - DefaultValues.snakeMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.snakeMaxMovement + 1) - DefaultValues.snakeMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManySnakesInCell(newX, newY, island)) {
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

        int requiredRodentCount = (int) Math.ceil(DefaultValues.snakeSatiety);
        int eatenRodentCount = 0;
        double eatenWeight = 0.0;

        while (eatenRodentCount < requiredRodentCount && !priorityQueue.isEmpty()) {
            Animal animal = priorityQueue.poll();

            if (eatenWeight + animal.getWeightAnimal() <= DefaultValues.snakeSatiety) {
                cell.remove(animal);
                eatenWeight += animal.getWeightAnimal();
                eatenRodentCount++;
            } else {
                break;
            }
        }

        if (eatenRodentCount > 0) {
            setXpAnimal(DefaultValues.allXpAnimal);
        } else {
            setXpAnimal(getXpAnimal() - 1);
        }
    }

    private double getEatProbability(Animal animal) {
        if (animal instanceof Mouse) {
            return 0.4;
        } else if (animal instanceof Rabbit) {
            return 0.2;
        } else if (animal instanceof Fox) {
            return 0.15;
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

        int snakeCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Snake) {
                snakeCount++;
                Snake snake = (Snake) obj;
                if (snake.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (snake.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (snakeCount < DefaultValues.snakeMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.snakeMaxAnimalsPerCell - snakeCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newSnakeCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newSnakeCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Snake newSnake = new Snake(DefaultValues.allXpAnimal, DefaultValues.snakeWeight, randomGender, x, y);

                cell.add(newSnake);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManySnakesInCell(int x, int y, Island island) {
        int wolfCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Wolf) {
                wolfCount++;
                if (wolfCount >= DefaultValues.snakeMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}