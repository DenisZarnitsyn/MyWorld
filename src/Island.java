import java.util.*;

public class Island {
    private int sizeX;
    private int sizeY;
    private Map<Integer, Map<Integer, List<Object>>> grid;

    public Island(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grid = new HashMap<>();

        initializeIsland();
    }

    public void initializeIsland() {
        ObjectInitializer.initializeIsland(this);
    }

    public void simulateDay() {
        DaySimulator.simulateDay(this);
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Map<Integer, Map<Integer, List<Object>>> getGrid() {
        return grid;
    }

    public void setGrid(Map<Integer, Map<Integer, List<Object>>> grid) {
        this.grid = grid;
    }
}
