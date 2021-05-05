package com.cmpt213.Model;

import com.cmpt213.UI.Game;

/**
 * UnknownGrid class creates a grid that works according to the person playing the game.
 */

public class UnknownGrid {
    public static String[][] unknownGrid = new String[10][10];

    public UnknownGrid(int startPosAlpha, int startPosNum) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                unknownGrid[i][j] = "~";
            }
        }
        unknownGrid[startPosAlpha][startPosNum] = "@";
    }

    public String[][] getUnknownGrid() {
        return unknownGrid;
    }

    public void setUnknownGrid(String[][] unknownGrid) {
        UnknownGrid.unknownGrid = unknownGrid;
    }
}
