import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IslandStatistics {
    static String grassSymbol = "\uD83C\uDF31";
    static String rabbitSymbol = "\uD83D\uDC30";
    private static int dailyGrassGenerated = 0;
    private static int dailyGrassConsumed = 0;
    private static int totalGrassEaten = 0;
    private static int totalGrassGrown = 0;

    public static void countIslandObjects(Map<Integer, Map<Integer, List<Object>>> grid) {
        totalGrassEaten = 0;
        totalGrassGrown = 0;

        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.get(x).size(); y++) {
                int grassCount = 0;
                int maleRabbitCount = 0;
                int femaleRabbitCount = 0;

                for (Object obj : grid.get(x).get(y)) {
                    if (obj instanceof Grass) {
                        grassCount++;
                    } else if (obj instanceof Rabbit) {
                        Rabbit rabbit = (Rabbit) obj;
                        if (rabbit.getGenderAnimal() == Gender.MALE) {
                            maleRabbitCount++;
                        } else if (rabbit.getGenderAnimal() == Gender.FEMALE) {
                            femaleRabbitCount++;
                        }
                    }
                }

                System.out.println("In coordinates (" + x + ", " + y + ") created: " +
                        grassSymbol + " - " + grassCount + ", " +
                        rabbitSymbol + " - " + (maleRabbitCount + femaleRabbitCount) +
                        " (" + rabbitSymbol + "♂ - " + maleRabbitCount + ", " + rabbitSymbol + "♀ - " + femaleRabbitCount + ")");
            }
        }
    }

    public static void printAllObjects(Map<Integer, Map<Integer, List<Object>>> grid) {
        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.get(x).size(); y++) {
                System.out.println("In coordinates (" + x + ", " + y + ") located:");

                for (Object obj : grid.get(x).get(y)) {
                    if (obj instanceof Grass) {
                        Grass grass = (Grass) obj;
                        System.out.println("Grass (Weight: " + grass.getWeightGrass() + " Coordinates (X, Y): (" + grass.getCoordinateGrassX() + ", " + grass.getCoordinateGrassY() + ")");
                        totalGrassGrown++;
                    } else if (obj instanceof Rabbit) {
                        Rabbit rabbit = (Rabbit) obj;
                        System.out.println("Rabbit (Current weight: " + rabbit.getWeightAnimal() + ", Gender: " + rabbit.getGenderAnimal() + ", " + "Coordinates (X, Y): (" + rabbit.getCoordinateAnimalX() + ", " + rabbit.getCoordinateAnimalY() + ")");
                    }
                }
            }
        }
    }
        public static void countTotalIslandObjects(Map<Integer, Map<Integer, List<Object>>> grid, int grassGenerated, int grassEaten) {
            int totalGrassCount = 0;
            int totalMaleRabbitCount = 0;
            int totalFemaleRabbitCount = 0;

            for (int x = 0; x < grid.size(); x++) {
                for (int y = 0; y < grid.get(x).size(); y++) {
                    for (Object obj : grid.get(x).get(y)) {
                        if (obj instanceof Grass) {
                            totalGrassCount++;
                        } else if (obj instanceof Rabbit) {
                            Rabbit rabbit = (Rabbit) obj;
                            if (rabbit.getGenderAnimal() == Gender.MALE) {
                                totalMaleRabbitCount++;
                            } else if (rabbit.getGenderAnimal() == Gender.FEMALE) {
                                totalFemaleRabbitCount++;
                            }
                        }
                    }
                }
            }

            System.out.println("Total objects on the island: " +
                    grassSymbol + " - " + totalGrassCount + " (Grown: " + (totalGrassGrown + grassGenerated) + ", Eaten: " + (totalGrassEaten + grassEaten) + "), " +
                    rabbitSymbol + " - " + (totalMaleRabbitCount + totalFemaleRabbitCount) +
                    " (" + rabbitSymbol + "♂ - " + totalMaleRabbitCount + ", " + rabbitSymbol + "♀ - " + totalFemaleRabbitCount + ")");
        }

    public static void printDailyGrassStatistics() {
        System.out.println("Daily Grass Statistics:");
        System.out.println("Grass generated today: " + getDailyGrassGenerated());
        System.out.println("Grass consumed today: " + getDailyGrassConsumed());
    }

    public static int getDailyGrassGenerated() {
        return dailyGrassGenerated;
    }

    public static int getDailyGrassConsumed() {
        return dailyGrassConsumed;
    }
    public static void addEatenGrass(int eatenGrass) {
        totalGrassEaten += eatenGrass;
    }

    public static void incrementDailyGrassGenerated() {
        dailyGrassGenerated++;
    }

    public static void incrementDailyGrassConsumed() {
        dailyGrassConsumed++;
    }
    public static String getGrassSymbol() {
        return grassSymbol;
    }

    public static void setGrassSymbol(String grassSymbol) {
        IslandStatistics.grassSymbol = grassSymbol;
    }

    public static String getRabbitSymbol() {
        return rabbitSymbol;
    }

    public static void setRabbitSymbol(String rabbitSymbol) {
        IslandStatistics.rabbitSymbol = rabbitSymbol;
    }


    public static void setDailyGrassGenerated(int dailyGrassGenerated) {
        IslandStatistics.dailyGrassGenerated = dailyGrassGenerated;
    }

    public static void setDailyGrassConsumed(int dailyGrassConsumed) {
        IslandStatistics.dailyGrassConsumed = dailyGrassConsumed;
    }
}
