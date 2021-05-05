package com.cmpt213.Model;

import com.cmpt213.UI.Game;

import java.util.Random;

/**
 * KnownGrid class creates a grid that knows the location of all Tokimons and Fokimons of the grid
 * that is to be used during the game.
 */

public class KnownGrid {
    public static String[][] knownGrid = new String[10][10];
    public static int nTokimons;
    public static int nFokimons;

    public KnownGrid(int numToki, int numFoki) {
        nTokimons = numToki;
        nFokimons = numFoki;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                knownGrid[i][j] = " ";
            }
        }

        Random random = new Random();
        for (int i = 0; i < numToki; i++) {
            while (true) {
                int rRow = random.nextInt(10);
                int rCol = random.nextInt(10);
                if (knownGrid[rRow][rCol].isBlank()) {
                    knownGrid[rRow][rCol] = "$";
                    break;
                }
            }
        }
        for (int i = 0; i < numFoki; i++) {
            while (true) {
                int rRow = random.nextInt(10);
                int rCol = random.nextInt(10);
                if (knownGrid[rRow][rCol].isBlank()) {
                    knownGrid[rRow][rCol] = "X";
                    break;
                }
            }
        }
    }

    public String[][] getKnownGrid() {
        return knownGrid;
    }

    public void setKnownGrid(String[][] knownGrid) {
        KnownGrid.knownGrid = knownGrid;
    }
}
