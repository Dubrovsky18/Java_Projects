package tictactoe;

import java.util.Scanner;

public class Main {


    public static void drawTable(char[][] array) {
        System.out.println("---------");
        System.out.println("| " + array[0][0] + " " + array[0][1] + " " + array[0][2] + " |");
        System.out.println("| " + array[1][0] + " " + array[1][1] + " " + array[1][2] + " |");
        System.out.println("| " + array[2][0] + " " + array[2][1] + " " + array[2][2] + " |");
        System.out.println("---------");
    }

    public static boolean checkArray(char[][] array, char symbol) {
        return symbol == array[0][0] && symbol == array[0][1] && symbol == array[0][2] ||
                symbol == array[0][0] && symbol == array[1][1] && symbol == array[2][2] ||
                symbol == array[0][0] && symbol == array[1][0] && symbol == array[2][0] ||
                symbol == array[0][1] && symbol == array[1][1] && symbol == array[2][1] ||
                symbol == array[0][2] && symbol == array[1][2] && symbol == array[2][2] ||
                symbol == array[0][2] && symbol == array[1][1] && symbol == array[2][0] ||
                symbol == array[1][0] && symbol == array[1][1] && symbol == array[1][2] ||
                symbol == array[2][0] && symbol == array[2][1] && symbol == array[2][2];
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] array = new char[3][3];
        int num1;
        int num2;
        boolean result = false;
        char symbol;
        boolean flag;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = '_';
            }
        }
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                symbol = 'X';
            } else {
                symbol = 'O';
            }
            drawTable(array);
            do {
                flag = true;
                boolean num = false;
                num1 = 0;
                num2 = 0;
                try {
                    num1 = scanner.nextInt();
                    num2 = scanner.nextInt();
                } catch (NumberFormatException e) {
                    flag = false;
                    num = true;
                }
                boolean numeric = num1 <= 3 && num1 >= 1 && num2 <= 3 && num2 >= 1;
                if (num) {
                    System.out.println("You should enter numbers!");
                } else if (!numeric) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    flag = false;
                } else if (array[num1 - 1][num2 - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    flag = false;
                }
            } while (!flag);
            array[num1 - 1][num2 - 1] = symbol;
            drawTable(array);
            result = checkArray(array, symbol);
            if (result) {
                System.out.println(symbol + " wins");
                break;
            }
        }
        if (!result) {
            System.out.println("Draw");
        }
    }
}
