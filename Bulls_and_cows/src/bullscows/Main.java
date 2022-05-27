package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static String code = "";
    static String answer;


    public static boolean checkongCode() {
        for (int i = 0; i < code.length(); i++) {
            if (code.substring(i + 1).contains(String.valueOf(code.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean generaticCode() {
        System.out.println(" Please, enter the secret code's length:");
        Scanner scanner = new Scanner(System.in);
        Random r = new Random();
        int count = -1;
        String counter = scanner.next();
        try {
            count = Integer.parseInt(counter);
        } catch (Exception e) {
            System.out.println("Error: " + counter + " isn't a valid number");
            return false;
        }
        System.out.println("Input the number of possible symbols in the code:");
        String alfabete = scanner.next();
        int alfabet = -7;
        try {
            alfabet = Integer.parseInt(alfabete);
        } catch (Exception e) {
            System.out.println("Error: " + alfabete + " isn't a valid number");
            return false;
        }
        if (alfabet >= count && count > 0) {
            StringBuilder result = new StringBuilder();
            StringBuilder stars = new StringBuilder();
            if (alfabet > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return false;
            } else {
                if (alfabet >= 11) {
                    String num = "(0-9, ";
                    result.append(num);
                    char alf = (char) (96 + (alfabet - 10));
                    if (alfabet >= 12) {
                        result.append("a-").append(alf).append(").");
                    } else {
                        result.append("a).");
                    }
                } else {
                    result.append("(0-").append(alfabet - 1).append(").");
                }
                do {
                    int finalAlfabet = alfabet;
                    code = r.ints(48, 122)
                            .filter(i -> (i < (97 + (finalAlfabet - 10)) && i > 97) || (i < 57 && i > 48))
                            .mapToObj(i -> (char) i)
                            .limit(count)
                            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                            .toString();
//                System.out.println(code);
                } while (!checkongCode());

                while (count-- > 0) {
                    stars.append("*");
                }
                System.out.println("The secret is prepared: " + stars + " " + result);
                return true;
            }
        } else {
            System.out.println("Error: it's not possible to generate a code with a length of " + alfabete + " with " + counter + " unique symbols.\n");
            return false;
        }
    }

    public static void giveAttention() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (!code.equals(answer)) {
            ++i;
            System.out.println("Turn " + i + ". Answer:");
            answer = scanner.next();
            int bull = 0;
            int cows = 0;
            for (int c = 0; c < answer.length(); c++) {
                char alf = answer.charAt(c);
                if (code.charAt(c) == alf) {
                    bull += 1;
                } else if (code.contains(String.valueOf(alf))) {
                    cows += 1;
                }
            }
            if (bull == 0 && cows == 0) {
                System.out.println("Grade: None.");
            } else if (cows == 0 && bull != 0) {
                System.out.println("Grade: " + bull + " bull(s)");
            } else if (bull == 0 && cows != 0) {
                System.out.println("Grade: " + cows + " cows(s)");
            } else {
                System.out.println("Grade: " + bull + " bull(s) and " + cows + " cow(s)");
            }
            if (bull == code.length()) {
                System.out.println("Congratulations! You guessed the secret code.");
            }
        }
    }

    public static void main(String[] args) {
        if (generaticCode()) {
            System.out.println("Okay, let's start a game!");
            giveAttention();
        }
    }
}
