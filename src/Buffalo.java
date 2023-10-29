import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Buffalo extends Herbivore {

    public Buffalo(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.buffaloMaxMovement + 1) - DefaultValues.buffaloMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.buffaloMaxMovement + 1) - DefaultValues.buffaloMaxMovement;

        if (newX >= 0 && newX < island.getSizeX() && newY >= 0 && newY < island.getSizeY()) {
            if (!isTooManyBuffaloInCell(newX, newY, island)) {
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

        int requiredGrassCount = (int) Math.ceil(DefaultValues.buffaloSatiety);
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

        int buffaloCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Buffalo) {
                buffaloCount++;
                Buffalo buffalo = (Buffalo) obj;
                if (buffalo.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (buffalo.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (buffaloCount < DefaultValues.buffaloMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.buffaloMaxAnimalsPerCell - buffaloCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newBuffaloCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newBuffaloCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Buffalo newBuffalo = new Buffalo(DefaultValues.allXpAnimal, DefaultValues.buffaloWeight, randomGender, x, y);

                cell.add(newBuffalo);
            }
        }
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    private boolean isTooManyBuffaloInCell(int x, int y, Island island) {
        int buffaloCount = 0;
        for (Object obj : island.getGrid().get(x).get(y)) {
            if (obj instanceof Buffalo) {
                buffaloCount++;
                if (buffaloCount >= DefaultValues.buffaloMaxAnimalsPerCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
