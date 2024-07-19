package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        Stage5();


    }

    private static void Stage5() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int codeLength =TryParseInt(scanner);

        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbols = TryParseInt(scanner);

        scanner.nextLine(); // to avoid delimiter error from scanner

        if (possibleSymbols < codeLength) {
            System.out.println("Error possibleSymbols < codeLength ");
            System.exit(0);
        }
        if (possibleSymbols > 36) {
            System.out.println("Error possibleSymbols > 36 ");
            System.exit(0);
        }


        if (codeLength > 0 && codeLength <= 36) {

            String secretNumber = GenerateSecretCode(codeLength, possibleSymbols);

            // stars and range
            String stars = new String(new char[possibleSymbols]).replace('\0', '*');
            char initChar = 'a';
            if (possibleSymbols > 10) {
                for (int i = 1; i < possibleSymbols - 10; i++) {
                    ++initChar;
                }
            }

            String rangeString = possibleSymbols > 10 ? String.valueOf(initChar) : "";

            System.out.println("The secret is prepared: " + stars + " (0-9, a-" + rangeString + ")");

            System.out.println("Okay, let's start a game!");

            int bulls = 0;
            int count = 1;
            while (bulls != codeLength) {


                System.out.println("Turn " + count + ": Code-> " + secretNumber);

                int[] result = CheckForBulls(scanner.nextLine(), secretNumber);
                bulls = result[0];
                int cows = result[1];


                StringBuilder stringBuilder = new StringBuilder();

                if (bulls == 0 && cows == 0) {
                    System.out.println("Grade: None");
                } else {
                    stringBuilder.append("Grade: ").append(bulls >= 1 ? bulls : "").append(bulls > 1 ? " bulls " : bulls == 1 ? " bull " : "").append(bulls >= 1 && cows >= 1 ? " and " : "").append(cows > 0 ? cows : "").append(cows > 1 ? " cows " : cows == 1 ? " cow " : "");


                    System.out.println(stringBuilder);
                }
                count++;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            System.out.println("Error Ende");
        }
    }

    private static int TryParseInt(Scanner scanner) {
        try{
            return scanner.nextInt();
        }catch (Exception e){
            System.out.println("Error No Number");
            System.exit(0);
            return 0;
        }
    }

    private static String GenerateSecretCode(int codeLength, int possibleSymbols) {

        Random rn = new Random();
        int rnMax = rn.nextInt(10) + 1;

        List<String> numbers = new ArrayList<String>();

        for (int i = 0; i <= rnMax; i++) {
            numbers.add(String.valueOf(i));
        }

        List<String> letters = new ArrayList<String>();

        char letter = 'a';
        for (int i = 0; i < codeLength - numbers.size(); i++) {
            letters.add(String.valueOf(letter));
            ++letter;
        }
        numbers.addAll(letters);

        Collections.shuffle(numbers);


        StringBuilder temp = new StringBuilder();
        for (String e : numbers) {
            temp.append(e);
        }
        return temp.toString();
    }

    private static void Stage4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int codeLength = scanner.nextInt();
        scanner.nextLine(); // to avoid delimiter error from scanner

        System.out.println("Okay, let's start a game!");

        if (codeLength < 10) {
            String secretNumber = GenerateSecretNumber(codeLength);
            int bulls = 0;
            int count = 1;
            while (bulls != codeLength) {


                System.out.println("Turn " + count + ":");

                int[] result = CheckForBulls(scanner.nextLine(), secretNumber);
                bulls = result[0];
                int cows = result[1];


                StringBuilder stringBuilder = new StringBuilder();

                if (bulls == 0 && cows == 0) {
                    System.out.println("Grade: None");
                } else {
                    stringBuilder.append("Grade: ").append(bulls >= 1 ? bulls : "").append(bulls > 1 ? " bulls " : bulls == 1 ? " bull " : "").append(bulls >= 1 && cows >= 1 ? " and " : "").append(cows > 0 ? cows : "").append(cows > 1 ? " cows " : cows == 1 ? " cow " : "");


                    System.out.println(stringBuilder);
                }
                count++;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            System.out.println("Error");
        }
    }

    private static String GenerateSecretNumber(int codeLength) {
        //return "1234567890".substring(0, codeLength);

        // Stage 5 Code below

        List<Integer> numbers = new ArrayList<Integer>();

        for (int i = 1; i <= codeLength; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        if (numbers.get(0) == 0) {

            numbers.remove(0);
            numbers.add(0);
        }

        StringBuilder temp = new StringBuilder();
        for (int e : numbers) {
            temp.append(e);
        }
        return temp.toString();
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
        int[] result = CheckForBulls(input, "9305");
        if (result[0] > 0) {
            System.out.println("Grade:" + result[0] + " bull(s) and " + result[1] + " cow(s). The secret code is 9305.");
        } else if (result[0] == 0 && result[1] > 0) {
            System.out.println("Grade:" + result[1] + " cow(s). The secret code is 9305.");
        } else {
            System.out.println("Grade: None. The secret code is 9305.");
        }

    }

    public static int[] CheckForBulls(String input, String code) {
        int bulls = 0;
        int cows = 0;
        char[] codeArr = code.toCharArray();
        char[] inputArr = input.toCharArray();

        for (int i = 0; i < inputArr.length; i++) {
            for (int j = 0; j < codeArr.length; j++) {
                if (i == j && inputArr[i] == codeArr[j]) {
                    bulls++;
                } else if (i != j && inputArr[i] == codeArr[j]) {
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
