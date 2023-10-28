import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IslandStatistics {
    static String grassSymbol = "🌱";
    static String rabbitSymbol = "🐰";
    static String deerSymbol = "🦌";
    static String goatSymbol = "🐐";
    static String sheepSymbol = "🐑";
    static String buffaloSymbol = "🐃";
    static String caterpillarSymbol = "🐛";

    private static int dailyGrassGenerated = 0;
    private static int dailyGrassConsumed = 0;
    private static int totalGrassGrown = 0;

    private static int dailyRabbitGenerated = 0;
    private static int dailyRabbitConsumed = 0;
    private static int totalRabbitEaten = 0;
    private static int totalRabbitGrown = 0;

    private static int dailyDeerGenerated = 0;
    private static int dailyDeerConsumed = 0;
    private static int totalDeerEaten = 0;
    private static int totalDeerGrown = 0;

    private static int dailyGoatGenerated = 0;
    private static int dailyGoatConsumed = 0;
    private static int totalGoatEaten = 0;
    private static int totalGoatGrown = 0;

    private static int dailySheepGenerated = 0;
    private static int dailySheepConsumed = 0;
    private static int totalSheepEaten = 0;
    private static int totalSheepGrown = 0;

    private static int dailyBuffaloGenerated = 0;
    private static int dailyBuffaloConsumed = 0;
    private static int totalBuffaloEaten = 0;
    private static int totalBuffaloGrown = 0;

    public static void countIslandObjectsWithAnimals(Map<Integer, Map<Integer, List<Object>>> grid) {
        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.get(x).size(); y++) {
                int grassCount = 0;
                int totalRabbitCount = 0;
                int maleRabbitCount = 0;
                int femaleRabbitCount = 0;
                int totalDeerCount = 0;
                int maleDeerCount = 0;
                int femaleDeerCount = 0;
                int totalGoatCount = 0;
                int maleGoatCount = 0;
                int femaleGoatCount = 0;
                int totalSheepCount = 0;
                int maleSheepCount = 0;
                int femaleSheepCount = 0;
                int totalBuffaloCount = 0;
                int maleBuffaloCount = 0;
                int femaleBuffaloCount = 0;
                int totalCaterpillarCount = 0;
                int maleCaterpillarCount = 0;
                int femaleCaterpillarCount = 0;

                for (Object obj : grid.get(x).get(y)) {
                    if (obj instanceof Grass) {
                        grassCount++;
                    } else if (obj instanceof Rabbit) {
                        totalRabbitCount++;
                        Rabbit rabbit = (Rabbit) obj;
                        if (rabbit.getGenderAnimal() == Gender.MALE) {
                            maleRabbitCount++;
                        } else if (rabbit.getGenderAnimal() == Gender.FEMALE) {
                            femaleRabbitCount++;
                        }
                    } else if (obj instanceof Deer) {
                        totalDeerCount++;
                        Deer deer = (Deer) obj;
                        if (deer.getGenderAnimal() == Gender.MALE) {
                            maleDeerCount++;
                        } else if (deer.getGenderAnimal() == Gender.FEMALE) {
                            femaleDeerCount++;
                        }
                    } else if (obj instanceof Goat) {
                        totalGoatCount++;
                        Goat goat = (Goat) obj;
                        if (goat.getGenderAnimal() == Gender.MALE) {
                            maleGoatCount++;
                        } else if (goat.getGenderAnimal() == Gender.FEMALE) {
                            femaleGoatCount++;
                        }
                    } else if (obj instanceof Sheep) {
                        totalSheepCount++;
                        Sheep sheep = (Sheep) obj;
                        if (sheep.getGenderAnimal() == Gender.MALE) {
                            maleSheepCount++;
                        } else if (sheep.getGenderAnimal() == Gender.FEMALE) {
                            femaleSheepCount++;
                        }
                    } else if (obj instanceof Buffalo) {
                        totalBuffaloCount++;
                        Buffalo buffalo = (Buffalo) obj;
                        if (buffalo.getGenderAnimal() == Gender.MALE) {
                            maleBuffaloCount++;
                        } else if (buffalo.getGenderAnimal() == Gender.FEMALE) {
                            femaleBuffaloCount++;
                        }
                    } else if (obj instanceof Caterpillar) {
                        totalCaterpillarCount++;
                        Caterpillar caterpillar = (Caterpillar) obj;
                        if (caterpillar.getGenderAnimal() == Gender.MALE) {
                            maleCaterpillarCount++;
                        } else if (caterpillar.getGenderAnimal() == Gender.FEMALE) {
                            femaleCaterpillarCount++;
                        }
                    }
                }

                System.out.print("In coordinates (" + x + ", " + y + "):");
                System.out.print(grassSymbol + ": " + grassCount);
                System.out.print(rabbitSymbol + ": " + totalRabbitCount + " (" + rabbitSymbol + "♂: " + maleRabbitCount + ", " + rabbitSymbol + "♀: " + femaleRabbitCount + ")");
                System.out.print(deerSymbol + ": " + totalDeerCount + " (" + deerSymbol + "♂: " + maleDeerCount + ", " + deerSymbol + "♀: " + femaleDeerCount + ")");
                System.out.print(goatSymbol + ": " + totalGoatCount + " (" + goatSymbol + "♂: " + maleGoatCount + ", " + goatSymbol + "♀: " + femaleGoatCount + ")");
                System.out.print(sheepSymbol + ": " + totalSheepCount + " (" + sheepSymbol + "♂: " + maleSheepCount + ", " + sheepSymbol + "♀: " + femaleSheepCount + ")");
                System.out.print(buffaloSymbol + ": " + totalBuffaloCount + " (" + buffaloSymbol + "♂: " + maleBuffaloCount + ", " + buffaloSymbol + "♀: " + femaleBuffaloCount + ")");
                System.out.println(caterpillarSymbol + ": " + (totalCaterpillarCount) + " (" + caterpillarSymbol + "♂: " + maleCaterpillarCount + ", " + caterpillarSymbol + "♀: " + femaleCaterpillarCount + ")");
            }
        }
    }


    public static void countTotalIslandObjects(Map<Integer, Map<Integer, List<Object>>> grid) {
        int totalGrassCount = 0;
        int totalMaleRabbitCount = 0;
        int totalFemaleRabbitCount = 0;
        int totalDeerCount = 0;
        int totalMaleDeerCount = 0;
        int totalFemaleDeerCount = 0;
        int totalGoatCount = 0;
        int totalMaleGoatCount = 0;
        int totalFemaleGoatCount = 0;
        int totalSheepCount = 0;
        int totalMaleSheepCount = 0;
        int totalFemaleSheepCount = 0;
        int totalBuffaloCount = 0;
        int totalMaleBuffaloCount = 0;
        int totalFemaleBuffaloCount = 0;
        int totalCaterpillarCount = 0;
        int totalMaleCaterpillarCount = 0;
        int totalFemaleCaterpillarCount = 0;

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
                    } else if (obj instanceof Deer) {
                        Deer deer = (Deer) obj;
                        totalDeerCount++;
                        if (deer.getGenderAnimal() == Gender.MALE) {
                            totalMaleDeerCount++;
                        } else if (deer.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleDeerCount++;
                        }
                    } else if (obj instanceof Goat) {
                        Goat goat = (Goat) obj;
                        totalGoatCount++;
                        if (goat.getGenderAnimal() == Gender.MALE) {
                            totalMaleGoatCount++;
                        } else if (goat.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleGoatCount++;
                        }
                    } else if (obj instanceof Sheep) {
                        Sheep sheep = (Sheep) obj;
                        totalSheepCount++;
                        if (sheep.getGenderAnimal() == Gender.MALE) {
                            totalMaleSheepCount++;
                        } else if (sheep.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleSheepCount++;
                        }
                    } else if (obj instanceof Buffalo) {
                        Buffalo buffalo = (Buffalo) obj;
                        totalBuffaloCount++;
                        if (buffalo.getGenderAnimal() == Gender.MALE) {
                            totalMaleBuffaloCount++;
                        } else if (buffalo.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleBuffaloCount++;
                        }
                    } else if (obj instanceof Caterpillar) {
                        Caterpillar caterpillar = (Caterpillar) obj;
                        totalCaterpillarCount++;
                        if (caterpillar.getGenderAnimal() == Gender.MALE) {
                            totalMaleCaterpillarCount++;
                        } else if (caterpillar.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleCaterpillarCount++;
                        }
                    }
                }
            }
        }

        System.out.print("Total objects on the island:");
        System.out.print(grassSymbol + ": " + totalGrassCount);
        System.out.print(rabbitSymbol + ": " + (totalMaleRabbitCount + totalFemaleRabbitCount) + " (" + rabbitSymbol + "♂: " + totalMaleRabbitCount + ", " + rabbitSymbol + "♀: " + totalFemaleRabbitCount + ")");
        System.out.print(deerSymbol + ": " + (totalMaleDeerCount + totalFemaleDeerCount) + " (" + deerSymbol + "♂: " + totalMaleDeerCount + ", " + deerSymbol + "♀: " + totalFemaleDeerCount + ")");
        System.out.print(goatSymbol + ": " + (totalMaleGoatCount + totalFemaleGoatCount) + " (" + goatSymbol + "♂: " + totalMaleGoatCount + ", " + goatSymbol + "♀: " + totalFemaleGoatCount + ")");
        System.out.print(sheepSymbol + ": " + (totalMaleSheepCount + totalFemaleSheepCount) + " (" + sheepSymbol + "♂: " + totalMaleSheepCount + ", " + sheepSymbol + "♀: " + totalFemaleSheepCount + ")");
        System.out.print(buffaloSymbol + ": " + (totalMaleBuffaloCount + totalFemaleBuffaloCount) + " (" + buffaloSymbol + "♂: " + totalMaleBuffaloCount + ", " + buffaloSymbol + "♀: " + totalFemaleBuffaloCount + ")");
        System.out.println(caterpillarSymbol + ": " + (totalCaterpillarCount) + " (" + caterpillarSymbol + "♂: " + totalMaleCaterpillarCount + ", " + caterpillarSymbol + "♀: " + totalFemaleCaterpillarCount + ")");
    }

}