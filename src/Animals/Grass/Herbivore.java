package Animals.Grass;

import DefaultValues.Gender.*;
import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

public abstract class Herbivore extends Animal {
    public Herbivore(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        super(xpAnimal, weightAnimal, genderAnimal, coordinateAnimalX, coordinateAnimalY);
    }

    public abstract void eatGrass(Island island);
}