public abstract class Animals {
    private String nameAnimal;
    private int weight;
    private int maxAnimalsPerCell;
    private int speedOfMovement;
    private int mountOfFoodPerDay;

    private boolean gender;

    public abstract void eat();
    public abstract void movement();
    public abstract void reproduction();
    public abstract void toDie();

}
