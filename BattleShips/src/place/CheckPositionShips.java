package place;

import java.util.Arrays;
import java.util.Scanner;

public class CheckPositionShips {

    private static final String[] question = {"Aircraft Carrier (5 cells):", "Battleship (4 cells): ", "Submarine (3 cells):", "Cruiser (3 cells):", "Destroyer (2 cells):"};

    private static int distanstStartLetter;

    private static int distanstStartNumber;

    private static int distanstFinishLetter;

    private static int distanstFinishNumber;

    static String number = "";

    static String numberFirst = "";
    static String numberSecond = "";


    private static boolean checkingLengthAnswer(int index) {
        int sizeShip;
        switch (index) {
            case 0 -> sizeShip = 5;
            case 1 -> sizeShip = 4;
            case 2, 3 -> sizeShip = 3;
            case 4 -> sizeShip = 2;
            default -> sizeShip = 0;
        }
        if (distanstStartNumber == distanstFinishNumber) {
            int result = distanstFinishLetter - distanstStartLetter;
            if (Math.abs(result) + 1 == sizeShip) {
                return true;
            }
        } else if (distanstStartLetter == distanstFinishLetter) {
            int result = distanstFinishNumber - distanstStartNumber;
            if (Math.abs(result) + 1 == sizeShip) {
                return true;
            }
        }
        System.out.println("Error! Wrong length of the Submarine! Try again:\n");
        return false;
    }


    private static boolean checkingNearPositions(int i, int j) {
        if (i == 10 && j == 10) {
            return PlaceBattleField.battleField[i - 1][j].equals("O") || PlaceBattleField.battleField[i - 1][j - 1].equals("O") || PlaceBattleField.battleField[i][j - 1].equals("O") || PlaceBattleField.battleField[i][j].equals("O");
        } else if (i == 10) {
            return PlaceBattleField.battleField[i - 1][j].equals("O") || PlaceBattleField.battleField[i - 1][j - 1].equals("O") || PlaceBattleField.battleField[i][j - 1].equals("O") || PlaceBattleField.battleField[i][j].equals("O") || PlaceBattleField.battleField[i][j + 1].equals("O");
        } else if (j == 10) {
            return PlaceBattleField.battleField[i - 1][j].equals("O") || PlaceBattleField.battleField[i - 1][j - 1].equals("O") || PlaceBattleField.battleField[i][j - 1].equals("O") || PlaceBattleField.battleField[i][j].equals("O") || PlaceBattleField.battleField[i + 1][j].equals("O");
        } else {
            return PlaceBattleField.battleField[i - 1][j].equals("O") || PlaceBattleField.battleField[i - 1][j - 1].equals("O") || PlaceBattleField.battleField[i][j - 1].equals("O") || PlaceBattleField.battleField[i][j].equals("O") || PlaceBattleField.battleField[i][j + 1].equals("O") || PlaceBattleField.battleField[i + 1][j + 1].equals("O") || PlaceBattleField.battleField[i + 1][j].equals("O");
        }
    }

    private static boolean checkingPositionShips() {
        if (distanstStartNumber == distanstFinishNumber) {
            for (int i = distanstStartLetter; i <= distanstFinishLetter; i++) {
                if (checkingNearPositions(i, distanstStartNumber)) {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                    return false;
                }
            }
        } else {
            for (int i = distanstStartNumber; i <= distanstFinishNumber; i++) {
                if (checkingNearPositions(distanstStartLetter, i)) {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                    return false;
                }
            }
        }
        return true;


    }

    private static boolean checkingCorrectAnswer(String[] answer) {
        for (int i = 0; i < 2; i++) {
            char letter = answer[i].charAt(0);
            if (!PlaceBattleField.getAlphabet().contains(String.valueOf(letter)) || !Arrays.asList(PlaceBattleField.getNumbers()).contains(number)) {
                System.out.println("Error! Wrong length of the Submarine! Try again:\n");
                return false;
            }
        }
        return true;
    }


    private static void peremenInPlace(String[] answer) {
        for (int i = 0; i < 2; i++) {
            if (answer[i].length() == 3) {
                number = "10";
            } else {
                number = String.valueOf(answer[i].charAt(1));
            }
            if (i == 0) {
                numberFirst = number;
            } else {
                numberSecond = number;
            }
        }
        int prepareToDistantLetterFirst = PlaceBattleField.getAlphabet().indexOf(answer[0].charAt(0));
        int prepareToDistantLetterSecond = PlaceBattleField.getAlphabet().indexOf(answer[1].charAt(0));
        int prepareToDistantNumberFirst = Integer.parseInt(numberFirst);
        int prepareToDistantNumberSecond = Integer.parseInt(numberSecond);
        if (prepareToDistantLetterFirst == prepareToDistantLetterSecond) {
            distanstStartNumber = Math.min(prepareToDistantNumberFirst, prepareToDistantNumberSecond);
            distanstFinishNumber = Math.max(prepareToDistantNumberFirst, prepareToDistantNumberSecond);
            distanstStartLetter = prepareToDistantLetterFirst;
            distanstFinishLetter = prepareToDistantLetterSecond;
        } else {
            distanstStartLetter = Math.min(prepareToDistantLetterFirst, prepareToDistantLetterSecond);
            distanstFinishLetter = Math.max(prepareToDistantLetterFirst, prepareToDistantLetterSecond);
            distanstStartNumber = prepareToDistantNumberFirst;
            distanstFinishNumber = prepareToDistantNumberSecond;
        }
    }


    private static void writeShipsToField() {
        if (distanstStartNumber == distanstFinishNumber) {
            for (int i = distanstStartLetter; i <= distanstFinishLetter; i++) {
                PlaceBattleField.battleField[i][distanstStartNumber] = "O";
            }
        } else {
            for (int i = distanstStartNumber; i <= distanstFinishNumber; i++) {
                PlaceBattleField.battleField[distanstStartLetter][i] = "O";
            }
        }
    }

    public static void askCellsShip() {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < question.length; i++) {
            PlaceBattleField.outputBattleField();
            System.out.println("Enter the coordinates of the " + question[i]);
            String[] answer = in.nextLine().split(" ");
            peremenInPlace(answer);
            while (!checkingCorrectAnswer(answer) || !checkingLengthAnswer(i) || !checkingPositionShips()) {
                answer = in.nextLine().split(" ");
                peremenInPlace(answer);
            }
            writeShipsToField();
        }
        PlaceBattleField.outputBattleField();
    }
}
