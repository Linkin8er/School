import java.util.Scanner;
import java.math.*;
public class BirthdayProblem {
    public static void main(String[] args){
        BirthdayProblem runner = new BirthdayProblem();
    }

    public BirthdayProblem(){
        Scanner scan = new Scanner(System.in);
        System.out.println("How many people are in a room with you?");
        int numberOfPeople = scan.nextInt();
        int sum = 0;
        for(int x = 1; x < numberOfPeople; x++){
            sum += x;
        }
        System.out.println("There is a "+ (1-(Math.pow((364.0/365.0),sum)))*100+"% chance that you will have someone will share birthdays.");
        
    }
}
