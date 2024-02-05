package StatisticsWork;
//Opening program for prob stats on 01/19/2023
//This class makes the calculator, and then prints the average of 10 and 12.4
import java.util.Scanner;

//This class is just used to make and call the calculator
public class TestAverage{
    public static void main(String[] args){

        AverageCalc caluclatorOfAwesomeness = new AverageCalc();
        System.out.println("The average of your two numbers is " + caluclatorOfAwesomeness.getAverage() + "!");
    }
}

//This class returns the average of two numbers
class AverageCalc{

    private double dFirstInput;
    private double dSecondInput;

    Scanner scan = new Scanner(System.in);

    //Constructor that sets variables to default of 0
    public AverageCalc(){
        dFirstInput = 0;
        dSecondInput = 0;
    }
    //This function asks the user for 2 numbers, and finds the average of them both
    public double getAverage(){ 

        System.out.println("Please enter a number!");
        dFirstInput = scan.nextDouble();
        System.out.println("Please enter annother number!");
        dSecondInput = scan.nextDouble();

        return ((dFirstInput+dSecondInput)/2);}
}