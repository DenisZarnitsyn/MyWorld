import java.util.Random;

public class Rabbit extends Herbivore{

    public Rabbit(double weightAnimal, double currentWeightAnimal, int maxAnimalsPerCell, int maxMovementAnimal, double satietyOfTheAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(weightAnimal, currentWeightAnimal, maxAnimalsPerCell, maxMovementAnimal, satietyOfTheAnimal, coordinateAnimalX, coordinateAnimalY);
    }
    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();
        int xMovement = random.nextInt(2 * maxMovementAnimal + 1) - maxMovementAnimal;
        int yMovement = random.nextInt(2 * maxMovementAnimal + 1) - maxMovementAnimal;

        int newX = coordinateAnimalX + xMovement;
        int newY = coordinateAnimalY + yMovement;

        if (newX < 0) {
            newX = 0;
        } else if (newX >= island.getSizeX()) {
            newX = island.getSizeX() - 1;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY >= island.getSizeY()) {
            newY = island.getSizeY() - 1;
        }

        // Check if the target cell is already at max capacity for rabbits
        if (countRabbitsInCell(island, newX, newY) < DefaultValues.rabbitMaxAnimalsPerCell) {
            // Update the coordinates of the animal
            coordinateAnimalX = newX;
            coordinateAnimalY = newY;
        }
    }

}