package com.cmpt213.UI;


import com.cmpt213.Model.KnownGrid;
import com.cmpt213.Model.UnknownGrid;
import com.cmpt213.UI.Game;

/**
 * GridPrinter class prints KnownGrids, UnknownGrids and a starting grid of the game.
 */

public class GridPrinter {

    public void printKnownGrid (KnownGrid knownGrid) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char chr = 'A';
        for (int i = 0; i < Game.N_ROWS; i++) {
            System.out.print(chr + " ");
            for (int j = 0; j < Game.N_COLS; j++) {
                System.out.print(KnownGrid.knownGrid[i][j] + " ");
            }
            System.out.println("");
            chr++;
        }
    }

    public void printUnknownGrid (UnknownGrid unknownGrid) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char chr = 'A';
        for (int i = 0; i < Game.N_ROWS; i++) {
            System.out.print(chr + " ");
            for (int j = 0; j < Game.N_COLS; j++) {
                System.out.print(UnknownGrid.unknownGrid[i][j] + " ");
            }
            System.out.println("");
            chr++;
        }
    }

    public void printStartingGrid() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char chr = 'A';
        for (int i = 0; i < Game.N_ROWS; i++) {
            System.out.print(chr + " ");
            for (int j = 0; j < Game.N_COLS; j++) {
                System.out.print("~ ");
            }
            System.out.println("");
            chr++;
        }
    }
}
