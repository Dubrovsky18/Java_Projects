package converter;

import java.util.Scanner;

public class Converter {

    public static void choose() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number in decimal system: ");
        int number = scanner.nextInt();
        System.out.print("\n Enter target base: ");
        int systemBinary = scanner.nextInt();
        String result = "";
        switch (systemBinary) {
            case 2:
                result = Integer.toBinaryString(number);
                break;
            case 8:
                result = Integer.toOctalString(number);
                break;
            case 16:
                result = Integer.toHexString(number);
                break;
            default:
                break;
        }
        System.out.println("Conversion result: " + result);
    }
}
