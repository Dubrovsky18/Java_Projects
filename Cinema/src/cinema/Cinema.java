package cinema;

import java.util.Scanner;

class Cinema {

    static String numbers = "12345678";

    static int expensiveRows;

    static int notExpensiveRows;

    static String[][] cinemaRoom;

    public static void main(String[] args) {
        costCinema();
        doCinemaRoom();
        showOpenPlaces();
        choicePlace();
        showOpenPlaces();
    }


    static void choicePlace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();
        int cost;
        if (row <= notExpensiveRows) {
            cost = 8;
        } else {
            cost = 10;
        }
        System.out.println("Ticket price: $" + cost);
        cinemaRoom[row][seat] = "B ";
    }

    public static void showOpenPlaces() {
        System.out.println("Cinema:");
        for (String[] strings : cinemaRoom) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    public static void costCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsRows = scanner.nextInt();
        int seats = rows * seatsRows;
        if (seats <= 60) {
            expensiveRows = 10;
            notExpensiveRows = 10;
        } else {
            expensiveRows = rows / 2;
            notExpensiveRows = rows - expensiveRows;
        }
        cinemaRoom = new String[rows + 1][seatsRows + 1];
    }

    private static void doCinemaRoom() {
        cinemaRoom[0][0] = " ";
        for (int i = 1; i < cinemaRoom.length; i++) {
            cinemaRoom[0][i] = numbers.charAt(i - 1) + " ";
            cinemaRoom[i][0] = numbers.charAt(i - 1) + " ";
            for (int j = 1; j < cinemaRoom[i].length; j++) {
                cinemaRoom[i][j] = "S ";
            }
        }
    }
}