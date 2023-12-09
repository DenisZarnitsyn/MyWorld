package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.List;
import java.util.Random;

public class Mouse extends Omnivorous {

    public Mouse(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    @Override
    public void moveAnimal(Island island) {
        Random random = new Random();

        int newX = getCoordinateAnimalX() + random.nextInt(2 * DefaultValues.mouseMaxMovement + 1) - DefaultValues.mouseMaxMovement;
        int newY = getCoordinateAnimalY() + random.nextInt(2 * DefaultValues.mouseMaxMovement + 1) - DefaultValues.mouseMaxMovement;

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
        int requiredMeatCount = (int) Math.ceil(DefaultValues.mouseSatiety);
        int eatenMeatCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Caterpillar) {
                double random = Math.random();
                if (random <= 0.9 && totalEatenWeight + ((Caterpillar) obj).getWeightAnimal() <= DefaultValues.mouseSatiety) {
                    cell.remove(obj);
                    totalEatenWeight += ((Caterpillar) obj).getWeightAnimal();
                    eatenMeatCount++;
                }
            } else if (obj instanceof Grass) {
                if (totalEatenWeight + DefaultValues.grassWeight <= DefaultValues.mouseSatiety) {
                    cell.remove(obj);
                    totalEatenWeight += DefaultValues.grassWeight;
                    eatenMeatCount++;
                }
            }

            if (eatenMeatCount >= requiredMeatCount && totalEatenWeight >= DefaultValues.mouseSatiety) {
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

        int mouseCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Mouse) {
                mouseCount++;
                Mouse mouse = (Mouse) obj;
                if (mouse.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (mouse.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (mouseCount < DefaultValues.mouseMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.mouseMaxAnimalsPerCell - mouseCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newMouseCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newMouseCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Mouse newMouse = new Mouse(DefaultValues.allXpAnimal, DefaultValues.mouseWeight, randomGender, x, y);

                cell.add(newMouse);
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
