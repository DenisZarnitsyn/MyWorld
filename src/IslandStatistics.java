import java.util.List;
import java.util.Map;

public class IslandStatistics {

    static String grassSymbol = "\uD83C\uDF31";
    static String rabbitSymbol = "\uD83D\uDC30";
    public static void countIslandObjects(Map<Integer, Map<Integer, List<Object>>> grid) {
        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.get(x).size(); y++) {
                int grassCount = 0;
                int rabbitCount = 0;

                for (Object obj : grid.get(x).get(y)) {
                    if (obj instanceof Grass) {
                        grassCount++;
                    } else if (obj instanceof Rabbit) {
                        rabbitCount++;
                    }
                }

                System.out.println("В клетке (" + x + ", " + y + ") создано " + grassCount + " " + grassSymbol + " травы, " + rabbitCount + " " + rabbitSymbol + " кроликов.");
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
                        System.out.println("Grass (Weight: " + grass.getWeightGrass() + " Coordinates (X, Y): (" + grass.getCoordinateGrassX() + ", "
                                + grass.getCoordinateGrassX() + ")");
                    } else if (obj instanceof Rabbit) {
                        Rabbit rabbit = (Rabbit) obj;
                        System.out.println("Rabbit (Weight: " + rabbit.getWeightAnimal() + "; Current weight: "
                                + rabbit.getCurrentWeightAnimal() + " Max Movement: " + rabbit.getMaxMovementAnimal() + " Satiety: "
                                + rabbit.getSatietyOfTheAnimal()+ " Coordinates (X, Y): (" + rabbit.getCoordinateAnimalX() + ", "
                                + rabbit.getCoordinateAnimalY() + ")");
                    }
                }
            }
        }
    }

}