package Fight;

import place.PlaceBattleField;

import java.util.Scanner;

public class Fire {

    static int fireNumber;
    static int fireLetter;


    private static void shot() {
        String result = PlaceBattleField.setBattleField(fireLetter, fireNumber);
        PlaceBattleField.outputBattleField();
        if (result.equals("X")) {
            System.out.println("You hit a ship!");
        } else {
            System.out.println("You missed!");
        }
    }

    private static boolean checkCorrectAnswer(String answer) {
        if (PlaceBattleField.getAlphabet().contains(String.valueOf(answer.charAt(0)))) {
            fireLetter = PlaceBattleField.getAlphabet().indexOf(answer.charAt(0));
            if (answer.length() == 3 && answer.charAt(1) == 1 && answer.charAt(2) == 0) {
                fireNumber = 10;
            } else if (answer.length() == 2) {
                fireNumber = Integer.parseInt(String.valueOf(answer.charAt(1)));
            } else return false;
            return true;
        }
        return false;
    }


    public static void game() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nThe game starts!\n");
        PlaceBattleField.outputBattleField();
        System.out.println("\nTake a shot!\n");
        String answer = scanner.nextLine();
        while (!checkCorrectAnswer(answer)) {
            System.out.println("Error! You entered the wrong coordinates! Try again:\n");
            answer = scanner.nextLine();
        }
        shot();
    }
}
