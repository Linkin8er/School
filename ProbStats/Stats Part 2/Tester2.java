import java.util.Scanner;
public class Tester2 {

    Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Hello World");
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                        //
// These next methods don't do any calculations                                                           //
// They are for getting information safely, and sorting information                                       //
//                                                                                                        //
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //This runs though and makes sure the user inputs legal choices when picking numbers of some kind. 
    //This choiceChecker is the same as the other, except this accepts integers
    public int choiceChecker(int nLowerBounds, int nUpperBounds)
    {
        String userInputString = "";
        int userInputInteger = 0;
        boolean userCompliance = false;
        Boolean noBounds = false;
        Boolean lowBounds = false;
        //This try block is used to not only make sure that the user enters a number, but also that the number follows the set parameters
        while(!userCompliance)
        {
            try
            {
                //If the upper and lower bounds are both -100, then that indicates that we don't have any bounds, as -100 should never end up being the real bounds
                //If the upper bounds are lower than the lower bounds, then we just care about the lower bounds
                //Otherwise we use the regular bounds
                if(nLowerBounds == -100 && nUpperBounds == -100) noBounds = true;
                if(nLowerBounds > nUpperBounds) lowBounds = true;

                if(noBounds) System.out.print("Please enter an your integer:\t");
                    else if(lowBounds) System.out.print("Please enter an integer greater than " + nLowerBounds + ":\t");
                        else System.out.print("Please enter an integer from " + nLowerBounds + " to " + nUpperBounds+ ":\t");
                //This set is used to make sure the user enters an integer. If i call for an integer and they input anything else, the program keeps breaking
                //This way, I can call for a string, and then just change it into an int. If that doesn't work, then I can handle it and work from there.
                userInputString = scan.nextLine();
                userInputInteger = Integer.parseInt(userInputString); 
                //This checks if the user entered a number within their given bounds
                if((userInputInteger >= nLowerBounds && userInputInteger <= nUpperBounds) || (noBounds)|| (lowBounds && (userInputInteger > nLowerBounds))) userCompliance = true;
                //This else will run if the user enters a number that is outside the bounds in which case it uses recursion until the user complies
                else System.out.println("That was not within the bounds");
            }
            //This catch is to make sure the user enters an integer
            //If they don't, it will use recursion until they do
            catch (NumberFormatException exception)
            {
                System.out.println("Please enter a proper number next time.");
            }
        }        
        return userInputInteger;
    }
    //This choice checker is the same as the other, but instead is used for doubles
    public double choiceChecker(double nLowerBounds, double nUpperBounds)
    {
        String userInputString = "";
        double userInputDouble = 0;
        boolean userCompliance = false;
        Boolean noBounds = false;
        Boolean lowBounds = false;
        //This try block is used to not only make sure that the user enters a number, but also that the number follows the set parameters
        while(!userCompliance)
        {
            try
            {
                //If the upper and lower bounds are both -100, then that indicates that we don't have any bounds, as -100 should never end up being the real bounds
                //If the upper bounds are lower than the lower bounds, then we just care about the lower bounds
                //Otherwise we use the regular bounds
                if(nLowerBounds == -100 && nUpperBounds == -100) noBounds = true;
                if(nLowerBounds > nUpperBounds) lowBounds = true;

                if(noBounds) System.out.print("Please enter an your number:\t");
                    else if(lowBounds) System.out.print("Please enter an integer greater than " + nLowerBounds + ":\t");
                        else System.out.print("Please enter a real number from " + nLowerBounds + " to " + nUpperBounds+ ":\t");
                //This set is used to make sure the user enters an integer. If i call for an integer and they input anything else, the program keeps breaking
                //This way, I can call for a string, and then just change it into an int. If that doesn't work, then I can handle it and work from there.
                userInputString = scan.nextLine();
                userInputDouble = Double.parseDouble(userInputString); 
                //This checks if the user entered a number within their given bounds
                if((userInputDouble >= nLowerBounds && userInputDouble <= nUpperBounds) || (noBounds)|| (lowBounds && (userInputDouble > nLowerBounds))) userCompliance = true;
                //This else will run if the user enters a number that is outside the bounds in which case it uses recursion until the user complies
                else System.out.println("That was not within the bounds");
            }
            //This catch is to make sure the user enters an integer
            //If they don't, it will use recursion until they do
            catch (NumberFormatException exception)
            {
                System.out.println("Please enter a proper number next time.");
            }
        }        
        return userInputDouble;
    }
}