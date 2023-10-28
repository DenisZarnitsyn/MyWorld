public abstract class Animal{

    private int xpAnimal;
    private double weightAnimal;
    private Gender genderAnimal;
    private int coordinateAnimalX;
    private int coordinateAnimalY;

    public Animal(int xpAnimal, double weightAnimal, Gender genderAnimal, int coordinateAnimalX, int coordinateAnimalY) {
        this.xpAnimal = xpAnimal;
        this.weightAnimal = weightAnimal;
        this.genderAnimal = genderAnimal;
        this.coordinateAnimalX = coordinateAnimalX;
        this.coordinateAnimalY = coordinateAnimalY;
    }

    public abstract void moveAnimal(Island island);

    public abstract void dieAnimal(Island island);

    protected void reduceWeight(double amount) {
        double animalWeight = getWeightAnimal();
        animalWeight -= amount;
        setWeightAnimal(animalWeight);
    }

    public double getWeightAnimal() {
        return weightAnimal;
    }

    public void setWeightAnimal(double weightAnimal) {
        this.weightAnimal = weightAnimal;
    }

    public Gender getGenderAnimal() {
        return genderAnimal;
    }

    public void setGenderAnimal(Gender genderAnimal) {
        this.genderAnimal = genderAnimal;
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

    public int getXpAnimal() {
        return xpAnimal;
    }

    public void setXpAnimal(int xpAnimal) {
        this.xpAnimal = xpAnimal;
    }
}