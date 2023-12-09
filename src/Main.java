import Island.IslandStatistics.ObjectInitializer.DaySimulator.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter island size (width):");
        int sizeX = scanner.nextInt();

        System.out.println("Enter island size (height):");
        int sizeY = scanner.nextInt();

        System.out.println("Enter the number of days for the simulation:");
        int numDays = scanner.nextInt();

        Island island = new Island(sizeX, sizeY);
        System.out.println("World creation!");

        ObjectInitializer.initializeIsland(island);
        for (int day = 1; day <= numDays; day++) {
            System.out.println("Day " + day + ":");
            DaySimulator.simulateDay(island);
        }
    }
}
