import java.util.List;
import java.util.Random;

public class Caterpillar extends Herbivore {

    public Caterpillar(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    // Caterpillars do not move
    @Override
    public void moveAnimal(Island island) {
    }

    // Caterpillars do not eat grass
    @Override
    public int eatGrass(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int eatenGrassCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Grass) {
                eatenGrassCount++;
            }
        }

        if (eatenGrassCount > 0) {
            setXpAnimal(DefaultValues.allXpAnimal);
        } else {
            setXpAnimal(getXpAnimal() - 1);
        }

        return eatenGrassCount;
    }

    @Override
    public void dieAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        island.getGrid().get(x).get(y).remove(this);
    }

    @Override
    public void reproductionAnimal(Island island) {
        int x = getCoordinateAnimalX();
        int y = getCoordinateAnimalY();
        List<Object> cell = island.getGrid().get(x).get(y);

        int caterpillarCount = 0;
        int maleCount = 0;
        int femaleCount = 0;

        for (Object obj : cell) {
            if (obj instanceof Caterpillar) {
                caterpillarCount++;
                Caterpillar caterpillar = (Caterpillar) obj;
                if (caterpillar.getGenderAnimal() == Gender.MALE) {
                    maleCount++;
                } else if (caterpillar.getGenderAnimal() == Gender.FEMALE) {
                    femaleCount++;
                }
            }
        }

        if (caterpillarCount < DefaultValues.caterpillarMaxAnimalsPerCell) {
            int remainingSpace = DefaultValues.caterpillarMaxAnimalsPerCell - caterpillarCount;
            int minGenderCount = Math.min(maleCount, femaleCount);
            int newCaterpillarCount = Math.min(remainingSpace, minGenderCount);

            for (int i = 0; i < newCaterpillarCount; i++) {
                Gender randomGender = Gender.getRandomGender();
                Caterpillar newCaterpillar = new Caterpillar(DefaultValues.allXpAnimal, DefaultValues.caterpillarWeight, randomGender, x, y);

                cell.add(newCaterpillar);
            }
        }
    }
}
