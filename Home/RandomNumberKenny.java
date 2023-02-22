package Home;

import java.util.Random;
import java.util.Scanner;

public class RandomNumberKenny {
    public static void main(String[] args) {

        int number;
        int guess;
        boolean foundIt = true;

        System.out.println("Hello Kenny!\nCan you guess what number I am thinking of?\n");

        Random randomNumber = new Random();
        Scanner scan = new Scanner(System.in);

        number = randomNumber.nextInt(11) +1;

        System.out.println("I'm thinking of a number between 1-10.");

        while(foundIt){

            System.out.println("What is your guess?");
            System.out.print("Please enter an integer:");

            guess = scan.nextInt();

            if(guess > number) System.out.println("Nope, too high!");
            if(guess < number) System.out.println("Nope, too low!");
            if(guess == number){

                System.out.println("You got it! Good Job!");
                foundIt = false;
            }
        }

        System.out.println("Thanks for playing!");
    }

}
