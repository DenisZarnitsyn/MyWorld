public class Grass {

    //    private int idGrass;
    private double weightGrass;
    private int maximumPerCell;

    private int coordinateGrassX;
    private int coordinateGrassY;

    public Grass(double weightGrass, int maximumPerCell, int coordinateGrassX, int coordinateGrassY) {
        this.weightGrass = weightGrass;
        this.maximumPerCell = maximumPerCell;
        this.coordinateGrassX = coordinateGrassX;
        this.coordinateGrassY = coordinateGrassY;
    }

    public double getWeightGrass() {
        return weightGrass;
    }

    public void setWeightGrass(double weightGrass) {
        this.weightGrass = weightGrass;
    }

    public int getMaximumPerCell() {
        return maximumPerCell;
    }

    public void setMaximumPerCell(int maximumPerCell) {
        this.maximumPerCell = maximumPerCell;
    }

    public int getCoordinateGrassX() {
        return coordinateGrassX;
    }

    public void setCoordinateGrassX(int coordinateGrassX) {
        this.coordinateGrassX = coordinateGrassX;
    }

    public int getCoordinateGrassY() {
        return coordinateGrassY;
    }

    public void setCoordinateGrassY(int coordinateGrassY) {
        this.coordinateGrassY = coordinateGrassY;
    }
}