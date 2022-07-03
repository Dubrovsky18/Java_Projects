package place;

import java.util.Objects;

public class PlaceBattleField {
    protected static String[][] battleField = new String[11][11];

    public static String setBattleField(int i, int j) {
        String result;
        if (battleField[i][j].equals("O")) {
            battleField[i][j] = "X";
            result = "X";
        } else {
            battleField[i][j] = "M";
            result = "M";
        }
        return result;
    }


    private final static String alphabet = " ABCDEFGHIJ";

    public static String getAlphabet() {
        return alphabet;
    }

    private final static String[] numbers = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public static String[] getNumbers() {
        return numbers;
    }

    public static void outputBattleField() {
        for (String[] strings : battleField) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    public static void placeBattleField() {
        for (int i = 0; i < battleField.length; i++) {
            battleField[i][0] = String.valueOf(alphabet.charAt(i));
            battleField[0][i] = numbers[i];
            for (int j = 1; j < battleField[i].length; j++) {
                battleField[i][j] = "~";
            }
        }
    }
}
