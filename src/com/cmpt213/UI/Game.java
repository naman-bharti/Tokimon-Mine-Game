package com.cmpt213.UI;

import com.cmpt213.Model.KnownGrid;
import com.cmpt213.Model.UnknownGrid;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Game class asks the user for various arguments and keeps the application working until the games ends.
 */

public class Game {
    public static final int N_ROWS = 10;
    public static final int N_COLS = 10;
    private int numToki;
    private int knownNumToki;
    private int numFoki;
    private int rowNum;
    private int colNum;
    private int numSpells = 3;
    private boolean cheat;
    private KnownGrid knownGrid;
    private UnknownGrid unknownGrid;
    public static GridPrinter gridPrinter;

    public Game(int numToki, int numFoki, boolean cheat) {
        this.numToki = numToki;
        this.knownNumToki = 0;
        this.numFoki = numFoki;
        this.cheat = cheat;
        knownGrid = new KnownGrid(numToki, numFoki);
        gridPrinter = new GridPrinter();
        if (cheat) {
            System.out.println("Cheat Grid: ");
            gridPrinter.printKnownGrid(knownGrid);
        }
        else {
            System.out.println("Starting Grid: ");
            gridPrinter.printStartingGrid();
        }
    }

    public Game(int startPosAlpha, int startPosNum) {
        this.rowNum = startPosAlpha;
        this.colNum = startPosNum;
        unknownGrid = new UnknownGrid(startPosAlpha, startPosNum);
    }

    public void gameplay() {
        System.out.println("");
        System.out.println("W - Move Up");
        System.out.println("S - Move Down");
        System.out.println("A - Move Left");
        System.out.println("D - Move Right");
        if (numSpells > 0) {
            System.out.println("1 - Use a spell to jump the player to another grid location");
            System.out.println("2 - Use a spell to randomly reveal the location of one of the Tokimons");
            System.out.println("3 - Use a spell to randomly kill off one of the Fokimons");
        }
        System.out.println("E - Exit");
        System.out.print("Enter one of the above options:");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().toUpperCase();
        System.out.println("");

        if (input.equals("E")) {
            System.out.println("Exiting the game");
            System.exit(0);
        }

        else if (input.equals("W")) {
            if (rowNum == 0) {
                System.out.println("This move is not valid");
                gameplay();
            }
            rowNum = rowNum - 1;
            gridChecker();
            gameplay();
        }

        else if (input.equals("S")) {
            if (rowNum == 9) {
                System.out.println("This move is not valid");
                gameplay();
            }
            rowNum = rowNum + 1;
            gridChecker();
            gameplay();
        }

        else if (input.equals("A")) {
            if (colNum == 0) {
                System.out.println("This move is not valid");
                gameplay();
            }
            colNum = colNum - 1;
            gridChecker();
            gameplay();
        }

        else if (input.equals("D")) {
            if (colNum == 9) {
                System.out.println("This move is not valid");
                gameplay();
            }
            colNum = colNum + 1;
            gridChecker();
            gameplay();
        }

        else if (numSpells > 0 && input.equals("1")) {
            numSpells--;
            System.out.println("Enter a new position: ");
            Scanner in2 = new Scanner(System.in);
            String input2 = in2.nextLine().toUpperCase();
            System.out.println("");
            if (input2.length() < 2 || input2.length() > 3) {
                System.out.println("The new position is not valid");
                gameplay();
            }
            HashMap<Character, Integer> chrConverter = new HashMap<>();
            chrConverter.put('A',0);
            chrConverter.put('B',1);
            chrConverter.put('C',2);
            chrConverter.put('D',3);
            chrConverter.put('E',4);
            chrConverter.put('F',5);
            chrConverter.put('G',6);
            chrConverter.put('H',7);
            chrConverter.put('I',8);
            chrConverter.put('J',9);
            if (input2.length() == 2) {
                rowNum = chrConverter.get(input2.charAt(0));
                colNum = chrConverter.get(input2.charAt(1)) - 1;
            }
            if (input2.length() == 3) {
                rowNum = chrConverter.get(input2.charAt(0));
                colNum = 9;
            }
            gridChecker();
            gameplay();
        }

        else if (numSpells > 0 && input.equals("2")) {
            numSpells--;
            String[][] unknownTemp = UnknownGrid.unknownGrid;
            String[][] knownTemp = KnownGrid.knownGrid;
            Random random = new Random();
            while (true) {
                int randomRow = random.nextInt(10);
                int randomCol = random.nextInt(10);
                if (KnownGrid.knownGrid[randomRow][randomCol].equals("$") && !UnknownGrid.unknownGrid[randomRow][randomCol].equals("$")) {
                    UnknownGrid.unknownGrid[randomRow][randomCol] = "$";
                    KnownGrid.nTokimons--;
                    numToki = numToki - 1;
                    knownNumToki = knownNumToki + 1;
                    System.out.println("A Tokimon has been revealed");
                    if (KnownGrid.nTokimons == 0) {
                        System.out.println("Congrats!!! You won the game.");
                        String[][] temp = KnownGrid.knownGrid;
                        KnownGrid.knownGrid[randomRow][randomCol] = "@$";
                        gridPrinter.printKnownGrid(knownGrid);
                        System.exit(0);
                    }
                    UnknownGrid.unknownGrid[rowNum][colNum] = "@";
                    gridPrinter.printUnknownGrid(unknownGrid);
                    System.out.println("Number of collected Tokimons: " + knownNumToki);
                    System.out.println("Number of remaining Tokimons: " + KnownGrid.nTokimons);
                    System.out.println("Number of remaining spells: " + numSpells);
                    break;
                }
            }
            gameplay();
        }

        else if (numSpells > 0 && input.equals("3")) {
            numSpells--;
            String[][] unknownTemp = UnknownGrid.unknownGrid;
            String[][] knownTemp = KnownGrid.knownGrid;
            Random random = new Random();
            while (true) {
                int randomRow = random.nextInt(10);
                int randomCol = random.nextInt(10);
                if (KnownGrid.knownGrid[randomRow][randomCol].equals("X") && !UnknownGrid.unknownGrid[randomRow][randomCol].equals("X")) {
                    KnownGrid.knownGrid[randomRow][randomCol] = " ";
                    KnownGrid.nFokimons--;
                    System.out.println("A Fokimon has been killed");
                    System.out.println("Number of remaining Fokimons: " + KnownGrid.nFokimons);
                    UnknownGrid.unknownGrid[rowNum][colNum] = "@";
                    gridPrinter.printUnknownGrid(unknownGrid);
                    System.out.println("Number of collected Tokimons: " + knownNumToki);
                    System.out.println("Number of remaining Tokimons: " + KnownGrid.nTokimons);
                    System.out.println("Number of remaining spells: " + numSpells);
                    break;
                }
            }
            gameplay();
        }

        else {
            System.out.println("Enter a valid option");
            gameplay();
        }
    }


    public void gridChecker() {
        String[][] tempKnown = KnownGrid.knownGrid;
        String[][] tempUnknown = UnknownGrid.unknownGrid;
        if (KnownGrid.knownGrid[rowNum][colNum].equals("$") && !UnknownGrid.unknownGrid[rowNum][colNum].equals("$")) {
            UnknownGrid.unknownGrid[rowNum][colNum] = "@$";
            KnownGrid.nTokimons--;
            numToki = numToki - 1;
            knownNumToki = knownNumToki + 1;
            System.out.println("Congrats!!! You found a Tokimon.");
            if (KnownGrid.nTokimons == 0) {
                System.out.println("Congrats!!! You won the game.");
                String[][] temp = KnownGrid.knownGrid;
                KnownGrid.knownGrid[rowNum][colNum] = "@$";
                gridPrinter.printKnownGrid(knownGrid);
                System.exit(0);
            }
        }

        else if (KnownGrid.knownGrid[rowNum][colNum].equals("$") && UnknownGrid.unknownGrid[rowNum][colNum].equals("$")) {
            UnknownGrid.unknownGrid[rowNum][colNum] = "@$";
            System.out.println("You had already found this Tokimon and hence it won't be counted");
        }
        else if (KnownGrid.knownGrid[rowNum][colNum].equals("X")) {
            UnknownGrid.unknownGrid[rowNum][colNum] = "@X";
            KnownGrid.knownGrid[rowNum][colNum] = "@X";
            gridPrinter.printKnownGrid(knownGrid);
            System.out.println("You accidentally found a Fokimon. You lost the game.");
            System.out.println("Game Over");
            System.exit(0);
        }
        else {
            UnknownGrid.unknownGrid[rowNum][colNum] = "@";
        }

        gridPrinter.printUnknownGrid(unknownGrid);
        System.out.println("Number of collected Tokimons: " + knownNumToki);
        System.out.println("Number of remaining Tokimons: " + KnownGrid.nTokimons);
        System.out.println("Number of remaining spells: " + numSpells);

        String[][] unknownTemp2 = UnknownGrid.unknownGrid;
        if (UnknownGrid.unknownGrid[rowNum][colNum].equals("@$")) {
            UnknownGrid.unknownGrid[rowNum][colNum] = "$";
        }
        else {
            UnknownGrid.unknownGrid[rowNum][colNum] = " ";
        }
    }
}
