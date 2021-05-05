package com.cmpt213.UI;

import java.util.HashMap;
import java.util.Scanner;

/**
 * TokimonFinder class asks the user for initial arguments and launches the application.
 */

public class TokimonFinder {

    public static void main(String[] args) {
        int nToki = 10;
        int nFoki = 5;
        boolean cheat = false;
        String startingPosition;
        int startPosAlpha = 0;
        int startPosNum = 0;

        if (args.length > 3) {
            System.out.println("Please enter valid number of arguments");
            System.exit(0);
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("--numToki=(.*)")) {
                args[i] = args[i].replace("--numToki=", "");
                if (!args[i].isBlank() || Integer.parseInt(args[i]) >= 5) {
                    nToki = Integer.parseInt(args[i]);
                }
            }
            if (args[i].matches("--numFoki=(.*)")) {
                args[i] = args[i].replace("--numFoki=", "");
                if (!args[i].isBlank() || Integer.parseInt(args[i]) >= 5) {
                    nFoki = Integer.parseInt(args[i]);
                }
            }
            if (args[i].matches("--cheat")) {
                cheat =true;
            }
        }

        Game knownGame = new Game(nToki, nFoki, cheat);

        System.out.print("PLease enter starting position: ");
        Scanner in = new Scanner(System.in);
        System.out.println("");
        startingPosition = in.nextLine().toUpperCase();
        if (startingPosition.length() > 3 || startingPosition.length() < 2) {
            System.out.println("Starting position is not valid");
            System.exit(0);
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
        if (startingPosition.length() == 2) {
            startPosAlpha = chrConverter.get(startingPosition.charAt(0));
            startPosNum = Character.getNumericValue(startingPosition.charAt(1)) - 1;
        }
        if (startingPosition.length() == 3) {
            startPosAlpha = chrConverter.get(startingPosition.charAt(0));
            startPosNum = 9;
        }

        Game unknownGame = new Game(startPosAlpha, startPosNum);
        unknownGame.gridChecker();
        unknownGame.gameplay();
    }
}
