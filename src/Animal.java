import java.util.Random;

public abstract class Animal{

//    private int idAnimal;
//    private String nameAnimal;
    private double weightAnimal;
    private double currentWeightAnimal;
    private int maxAnimalsPerCell;
    private int maxMovementAnimal;
    private double satietyOfTheAnimal;
    private int coordinateAnimalX;
    private int coordinateAnimalY;

    public Animal(double weightAnimal, double currentWeightAnimal, int maxAnimalsPerCell, int maxMovementAnimal, double satietyOfTheAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        this.weightAnimal = weightAnimal;
        this.currentWeightAnimal = currentWeightAnimal;
        this.maxAnimalsPerCell = maxAnimalsPerCell;
        this.maxMovementAnimal = maxMovementAnimal;
        this.satietyOfTheAnimal = satietyOfTheAnimal;
        this.coordinateAnimalX = coordinateAnimalX;
        this.coordinateAnimalY = coordinateAnimalY;
    }

    public double getWeightAnimal() {
        return weightAnimal;
    }

    public void setWeightAnimal(double weightAnimal) {
        this.weightAnimal = weightAnimal;
    }

    public double getCurrentWeightAnimal() {
        return currentWeightAnimal;
    }

    public void setCurrentWeightAnimal(double currentWeightAnimal) {
        this.currentWeightAnimal = currentWeightAnimal;
    }

    public int getMaxAnimalsPerCell() {
        return maxAnimalsPerCell;
    }

    public void setMaxAnimalsPerCell(int maxAnimalsPerCell) {
        this.maxAnimalsPerCell = maxAnimalsPerCell;
    }

    public int getMaxMovementAnimal() {
        return maxMovementAnimal;
    }

    public void setMaxMovementAnimal(int maxMovementAnimal) {
        this.maxMovementAnimal = maxMovementAnimal;
    }

    public double getSatietyOfTheAnimal() {
        return satietyOfTheAnimal;
    }

    public void setSatietyOfTheAnimal(double satietyOfTheAnimal) {
        this.satietyOfTheAnimal = satietyOfTheAnimal;
    }

    public int getCoordinateAnimalX() {
        return coordinateAnimalX;
    }

    public void setCoordinateAnimalX(int coordinateAnimalX) {
        this.coordinateAnimalX = coordinateAnimalX;
    }

    public int getCoordinateAnimalY() {
        return coordinateAnimalY;
    }

    public void setCoordinateAnimalY(int coordinateAnimalY) {
        this.coordinateAnimalY = coordinateAnimalY;
    }

    public void moveAnimal() {
    }
    public void reproductionAnimal(){}
    public void deathAnimal(){}

}