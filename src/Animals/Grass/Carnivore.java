package Animals.Grass;
import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

public abstract class Carnivore extends Animal{
    public Carnivore(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    public abstract void eatMeat(Island island);
    @Override
    public void moveAnimal(Island island) {

    }

    @Override
    public void reproductionAnimal(Island island) {

    }

    @Override
    public void dieAnimal(Island island) {

    }
}
