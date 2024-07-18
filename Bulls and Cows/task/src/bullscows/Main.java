package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Stage2(scanner.nextLine());
        Stage3(scanner.nextInt());
    }

    public static void Stage3(int input) {

        if (input > 10) {
            System.out.println("Error");
        } else {
            String numbers = "1234567890";
            String result = numbers.substring(0, input);

            System.out.println("The random secret number is " + result);
        }


    }

    public static void Stage2(String input) {
        char[] inputArr = input.toCharArray();
        int[] result = CheckForBulls(inputArr, "9305");
        if (result[0] > 0) {
            System.out.println("Grade:" + result[0] + " bull(s) and " + result[1] + " cow(s). The secret code is 9305.");
        } else if (result[0] == 0 && result[1] > 0) {
            System.out.println("Grade:" + result[1] + " cow(s). The secret code is 9305.");
        } else {
            System.out.println("Grade: None. The secret code is 9305.");
        }

    }

    public static int[] CheckForBulls(char[] input, String code) {
        int bulls = 0;
        int cows = 0;
        char[] codeArr = code.toCharArray();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < codeArr.length; j++) {
                if (i == j && input[i] == codeArr[j]) {
                    bulls++;
                } else if (i != j && input[i] == codeArr[j]) {
                    cows++;
                }
            }

        }
        return new int[]{bulls, cows};
    }


    // Stage 1 Code
    public static void PrintPredefinedGameLog() {
        System.out.println("""
                The secret code is prepared: ****.

                Turn 1. Answer:
                1234
                Grade: 1 cow.

                Turn 2. Answer:
                5678
                Grade: 1 cow.

                Turn 3. Answer:
                9012
                Grade: 1 bull and 1 cow.

                Turn 4. Answer:
                9087
                Grade: 1 bull and 1 cow.

                Turn 5. Answer:
                1087
                Grade: 1 cow.

                Turn 6. Answer:
                9205
                Grade: 3 bulls.

                Turn 7. Answer:
                9305
                Grade: 4 bulls.
                Congrats! The secret code is 9305.
                                
                                """);
    }
}
