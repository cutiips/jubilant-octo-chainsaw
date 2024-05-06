/*
Java game "Guess a Number" that allows user to guess a random number that has been generated.

source: https://hackr.io/blog/java-projects
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessingGame {
    private static final int MIN_VALUE = 1;
    private static final int EASY_UPPER_LIMIT = 50;
    private static final int HARD_UPPER_LIMIT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            System.out.println("Choose your difficulty level (1: Easy, 2: Hard): ");
            int difficulty = getIntInput(scanner);

            int upperLimit;
            if (difficulty == 1) {
                upperLimit = EASY_UPPER_LIMIT;
            } else {
                upperLimit = HARD_UPPER_LIMIT;
            }

            int computerNumber = (int) (Math.random() * upperLimit + 1);
            int userAnswer = 0;
            System.out.println("Guess a number between " + MIN_VALUE + " and " + upperLimit + ".");
            int count = 1;

            while (userAnswer != computerNumber) {
                System.out.println("Enter your guess: ");
                userAnswer = getIntInput(scanner);
                System.out.println(determineGuess(userAnswer, computerNumber, count));
                count++;
                if (count == 4) {
                    if (computerNumber % 2 == 0) {
                        System.out.println("Hint: The number is even.");
                    } else {
                        System.out.println("Hint: The number is odd.");
                    }
                }
            }

            int points = determinePoints(count);
            System.out.println("Congratulations! You guessed the correct number.");
            System.out.println("Total Guesses: " + count);
            System.out.println("Points Earned: " + points);

            System.out.println("Do you want to play again? (Enter Y for Yes, N for No): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("Y");
        } while (playAgain);

        scanner.close();
    }

    public static String determineGuess(int userAnswer, int computerNumber, int count) {
        if (userAnswer < MIN_VALUE || userAnswer > 100) {
            return "Your guess is invalid";
        } else if (userAnswer == computerNumber) {
            return "Correct!\nTotal Guesses: " + count;
        } else if (userAnswer > computerNumber) {
            return "Your guess is too high, try again.\nTry Number: " + count;
        } else {
            return "Your guess is too low, try again.\nTry Number: " + count;
        }
    }

    public static int determinePoints(int count) {
        int points;
        if (count <= 3) {
            points = 10;
        } else if (count <= 6) {
            points = 5;
        } else {
            points = 1;
        }
        return points;
    }

    private static int getIntInput(Scanner scanner) {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear the input buffer
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        return input;
    }
}
