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
    static String wolfSymbol = "🐺";
    static String snakeSymbol = "🐍";
    static String foxSymbol = "🦊";
    static String bearSymbol = "🐻";
    static String eagleSymbol = "🦅";
    static String mouseSymbol = "🐭";
    static String boarSymbol = "🐗";
    static String duckSymbol = "🦆";

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

    private static int dailyCaterpillarGenerated = 0;
    private static int dailyCaterpillarConsumed = 0;
    private static int totalCaterpillarEaten = 0;
    private static int totalCaterpillarGrown = 0;

    private static int dailyWolfGenerated = 0;
    private static int dailyWolfConsumed = 0;
    private static int totalWolfEaten = 0;
    private static int totalWolfGrown = 0;

    private static int dailySnakeGenerated = 0;
    private static int dailySnakeConsumed = 0;
    private static int totalSnakeEaten = 0;
    private static int totalSnakeGrown = 0;

    private static int dailyFoxGenerated = 0;
    private static int dailyFoxConsumed = 0;
    private static int totalFoxEaten = 0;
    private static int totalFoxGrown = 0;

    private static int dailyBearGenerated = 0;
    private static int dailyBearConsumed = 0;
    private static int totalBearEaten = 0;
    private static int totalBearGrown = 0;

    private static int dailyEagleGenerated = 0;
    private static int dailyEagleConsumed = 0;
    private static int totalEagleEaten = 0;
    private static int totalEagleGrown = 0;

    private static int dailyMouseGenerated = 0;
    private static int dailyMouseConsumed = 0;
    private static int totalMouseEaten = 0;
    private static int totalMouseGrown = 0;

    private static int dailyBoarGenerated = 0;
    private static int dailyBoarConsumed = 0;
    private static int totalBoarEaten = 0;
    private static int totalBoarGrown = 0;

    private static int dailyDuckGenerated = 0;
    private static int dailyDuckConsumed = 0;
    private static int totalDuckEaten = 0;
    private static int totalDuckGrown = 0;

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
                int totalWolfCount = 0;
                int maleWolfCount = 0;
                int femaleWolfCount = 0;
                int totalSnakeCount = 0;
                int maleSnakeCount = 0;
                int femaleSnakeCount = 0;
                int totalFoxCount = 0;
                int maleFoxCount = 0;
                int femaleFoxCount = 0;
                int totalBearCount = 0;
                int maleBearCount = 0;
                int femaleBearCount = 0;
                int totalEagleCount = 0;
                int maleEagleCount = 0;
                int femaleEagleCount = 0;
                int totalMouseCount = 0;
                int maleMouseCount = 0;
                int femaleMouseCount = 0;
                int totalBoarCount = 0;
                int maleBoarCount = 0;
                int femaleBoarCount = 0;
                int totalDuckCount = 0;
                int maleDuckCount = 0;
                int femaleDuckCount = 0;

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
                    } else if (obj instanceof Wolf) {
                        totalWolfCount++;
                        Wolf wolf = (Wolf) obj;
                        if (wolf.getGenderAnimal() == Gender.MALE) {
                            maleWolfCount++;
                        } else if (wolf.getGenderAnimal() == Gender.FEMALE) {
                            femaleWolfCount++;
                        }
                    } else if (obj instanceof Snake) {
                        totalSnakeCount++;
                        Snake snake = (Snake) obj;
                        if (snake.getGenderAnimal() == Gender.MALE) {
                            maleSnakeCount++;
                        } else if (snake.getGenderAnimal() == Gender.FEMALE) {
                            femaleSnakeCount++;
                        }
                    } else if (obj instanceof Fox) {
                        totalFoxCount++;
                        Fox fox = (Fox) obj;
                        if (fox.getGenderAnimal() == Gender.MALE) {
                            maleFoxCount++;
                        } else if (fox.getGenderAnimal() == Gender.FEMALE) {
                            femaleFoxCount++;
                        }
                    } else if (obj instanceof Bear) {
                        totalBearCount++;
                        Bear bear = (Bear) obj;
                        if (bear.getGenderAnimal() == Gender.MALE) {
                            maleBearCount++;
                        } else if (bear.getGenderAnimal() == Gender.FEMALE) {
                            femaleBearCount++;
                        }
                    } else if (obj instanceof Eagle) {
                        totalEagleCount++;
                        Eagle eagle = (Eagle) obj;
                        if (eagle.getGenderAnimal() == Gender.MALE) {
                            maleEagleCount++;
                        } else if (eagle.getGenderAnimal() == Gender.FEMALE) {
                            femaleEagleCount++;
                        }
                    } else if (obj instanceof Mouse) {
                        totalMouseCount++;
                        Mouse mouse = (Mouse) obj;
                        if (mouse.getGenderAnimal() == Gender.MALE) {
                            maleMouseCount++;
                        } else if (mouse.getGenderAnimal() == Gender.FEMALE) {
                            femaleMouseCount++;
                        }
                    } else if (obj instanceof Boar) {
                        totalBoarCount++;
                        Boar boar = (Boar) obj;
                        if (boar.getGenderAnimal() == Gender.MALE) {
                            maleBoarCount++;
                        } else if (boar.getGenderAnimal() == Gender.FEMALE) {
                            femaleBoarCount++;
                        }
                    } else if (obj instanceof Duck) {
                        totalDuckCount++;
                        Duck duck = (Duck) obj;
                        if (duck.getGenderAnimal() == Gender.MALE) {
                            maleDuckCount++;
                        } else if (duck.getGenderAnimal() == Gender.FEMALE) {
                            femaleDuckCount++;
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
                System.out.print(caterpillarSymbol + ": " + (totalCaterpillarCount) + " (" + caterpillarSymbol + "♂: " + maleCaterpillarCount + ", " + caterpillarSymbol + "♀: " + femaleCaterpillarCount + ")");
                System.out.print(wolfSymbol + ": " + totalWolfCount + " (" + wolfSymbol + "♂: " + maleWolfCount + ", " + wolfSymbol + "♀: " + femaleWolfCount + ")");
                System.out.print(snakeSymbol + ": " + totalSnakeCount + " (" + snakeSymbol + "♂: " + maleSnakeCount + ", " + snakeSymbol + "♀: " + femaleSnakeCount + ")");
                System.out.print(foxSymbol + ": " + totalFoxCount + " (" + foxSymbol + "♂: " + maleFoxCount + ", " + foxSymbol + "♀: " + femaleFoxCount + ")");
                System.out.print(bearSymbol + ": " + totalBearCount + " (" + bearSymbol + "♂: " + maleBearCount + ", " + bearSymbol + "♀: " + femaleBearCount + ")");
                System.out.print(eagleSymbol + ": " + totalEagleCount + " (" + eagleSymbol + "♂: " + maleEagleCount + ", " + eagleSymbol + "♀: " + femaleEagleCount + ")");
                System.out.print(mouseSymbol + ": " + totalMouseCount + " (" + mouseSymbol + "♂: " + maleMouseCount + ", " + mouseSymbol + "♀: " + femaleMouseCount + ")");
                System.out.println(boarSymbol + ": " + totalBoarCount + " (" + boarSymbol + "♂: " + maleBoarCount + ", " + boarSymbol + "♀: " + femaleBoarCount + ")");
                System.out.println(duckSymbol + ": " + totalDuckCount + " (" + duckSymbol + "♂: " + maleDuckCount + ", " + duckSymbol + "♀: " + femaleDuckCount + ")");
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
        int totalWolfCount = 0;
        int totalMaleWolfCount = 0;
        int totalFemaleWolfCount = 0;
        int totalSnakeCount = 0;
        int totalMaleSnakeCount = 0;
        int totalFemaleSnakeCount = 0;
        int totalFoxCount = 0;
        int totalMaleFoxCount = 0;
        int totalFemaleFoxCount = 0;
        int totalBearCount = 0;
        int totalMaleBearCount = 0;
        int totalFemaleBearCount = 0;
        int totalEagleCount = 0;
        int totalMaleEagleCount = 0;
        int totalFemaleEagleCount = 0;
        int totalMouseCount = 0;
        int totalMaleMouseCount = 0;
        int totalFemaleMouseCount = 0;
        int totalBoarCount = 0;
        int totalMaleBoarCount = 0;
        int totalFemaleBoarCount = 0;
        int totalDuckCount = 0;
        int totalMaleDuckCount = 0;
        int totalFemaleDuckCount = 0;

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
                    } else if (obj instanceof Wolf) {
                        Wolf wolf = (Wolf) obj;
                        totalWolfCount++;
                        if (wolf.getGenderAnimal() == Gender.MALE) {
                            totalMaleWolfCount++;
                        } else if (wolf.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleWolfCount++;
                        }
                    } else if (obj instanceof Snake) {
                        Snake snake = (Snake) obj;
                        totalSnakeCount++;
                        if (snake.getGenderAnimal() == Gender.MALE) {
                            totalMaleSnakeCount++;
                        } else if (snake.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleSnakeCount++;
                        }
                    } else if (obj instanceof Fox) {
                        Fox fox = (Fox) obj;
                        totalFoxCount++;
                        if (fox.getGenderAnimal() == Gender.MALE) {
                            totalMaleFoxCount++;
                        } else if (fox.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleFoxCount++;
                        }
                    } else if (obj instanceof Bear) {
                        Bear bear = (Bear) obj;
                        totalBearCount++;
                        if (bear.getGenderAnimal() == Gender.MALE) {
                            totalMaleBearCount++;
                        } else if (bear.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleBearCount++;
                        }
                    } else if (obj instanceof Eagle) {
                        Eagle eagle = (Eagle) obj;
                        totalEagleCount++;
                        if (eagle.getGenderAnimal() == Gender.MALE) {
                            totalMaleEagleCount++;
                        } else if (eagle.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleEagleCount++;
                        }
                    } else if (obj instanceof Mouse) {
                        Mouse mouse = (Mouse) obj;
                        totalMouseCount++;
                        if (mouse.getGenderAnimal() == Gender.MALE) {
                            totalMaleMouseCount++;
                        } else if (mouse.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleMouseCount++;
                        }
                    } else if (obj instanceof Boar) {
                        Boar boar = (Boar) obj;
                        totalBoarCount++;
                        if (boar.getGenderAnimal() == Gender.MALE) {
                            totalMaleBoarCount++;
                        } else if (boar.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleBoarCount++;
                        }
                    } else if (obj instanceof Duck) {
                        Duck duck = (Duck) obj;
                        totalDuckCount++;
                        if (duck.getGenderAnimal() == Gender.MALE) {
                            totalMaleDuckCount++;
                        } else if (duck.getGenderAnimal() == Gender.FEMALE) {
                            totalFemaleDuckCount++;
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
        System.out.print(caterpillarSymbol + ": " + (totalCaterpillarCount) + " (" + caterpillarSymbol + "♂: " + totalMaleCaterpillarCount + ", " + caterpillarSymbol + "♀: " + totalFemaleCaterpillarCount + ")");
        System.out.print(wolfSymbol + ": " + (totalMaleWolfCount + totalFemaleWolfCount) + " (" + wolfSymbol + "♂: " + totalMaleWolfCount + ", " + wolfSymbol + "♀: " + totalFemaleWolfCount + ")");
        System.out.print(snakeSymbol + ": " + (totalMaleSnakeCount + totalFemaleSnakeCount) + " (" + snakeSymbol + "♂: " + totalMaleSnakeCount + ", " + snakeSymbol + "♀: " + totalFemaleSnakeCount + ")");
        System.out.print(foxSymbol + ": " + (totalMaleFoxCount + totalFemaleFoxCount) + " (" + foxSymbol + "♂: " + totalMaleFoxCount + ", " + foxSymbol + "♀: " + totalFemaleFoxCount + ")");
        System.out.print(bearSymbol + ": " + (totalMaleBearCount + totalFemaleBearCount) + " (" + bearSymbol + "♂: " + totalMaleBearCount + ", " + bearSymbol + "♀: " + totalFemaleBearCount + ")");
        System.out.print(eagleSymbol + ": " + (totalMaleEagleCount + totalFemaleEagleCount) + " (" + eagleSymbol + "♂: " + totalMaleEagleCount + ", " + eagleSymbol + "♀: " + totalFemaleEagleCount + ")");
        System.out.print(mouseSymbol + ": " + (totalMaleMouseCount + totalFemaleMouseCount) + " (" + mouseSymbol + "♂: " + totalMaleMouseCount + ", " + mouseSymbol + "♀: " + totalFemaleMouseCount + ")");
        System.out.println(boarSymbol + ": " + (totalMaleBoarCount + totalFemaleBoarCount) + " (" + boarSymbol + "♂: " + totalMaleBoarCount + ", " + boarSymbol + "♀: " + totalFemaleBoarCount + ")");
        System.out.println(duckSymbol + ": " + (totalMaleDuckCount + totalFemaleDuckCount) + " (" + duckSymbol + "♂: " + totalMaleDuckCount + ", " + duckSymbol + "♀: " + totalFemaleDuckCount + ")");
    }


}