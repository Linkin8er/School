/*
*   This is to be a library of everything we are learning in prob stats
*   Created by Nicholas Stephani, 01/24/2023
*/

/*
*   01/25/2023 Currently there is a bug
*   After entering numbers into the data set for mean, choice checker requests you enter a proper number
*   Also, after finnishing the list and getting the mean, it immediately restarts you in the mean function 
*/


import java.util.ArrayList;
import java.util.Scanner;

//This just runs the stats wizard
public class StatsLibrary {
    public static void main(String[] args){
        StatsWizard runner = new StatsWizard();

    }
}

class StatsWizard{

    private int userChoice;
    Scanner scan = new Scanner(System.in);
    //Constructor time! This runs continuously unti the user opts out. Currently only the mean works
    StatsWizard(){
        System.out.println("Hello there! I hear you may have some assignments, would you like to do?");
        boolean wantsToContinue = true;
        
        while(wantsToContinue)
        {
            System.out.println("1) Find a mean\n2) Find a median\n3) Find a mode\n4) End");
            userChoice = choiceChecker(1,4);
            if(userChoice == 1) findMean();
            if(userChoice == 2) findMedian();
            if(userChoice == 3) findMode();
            if(userChoice == 4) wantsToContinue = false;
        }
        System.out.println("\n\nHave a great day!");
    }

    public void findMean(){
        
        ArrayList<Integer> dataSet = listMaker();
        
        double dSum = 0;
        for(int iSingleElement : dataSet){
            dSum = dSum + iSingleElement;
        }
        
        System.out.println("The mean of your data set is "+ (dSum/dataSet.size())+"!\n\n");
    }

    //Not implimented yet
    public void findMedian(){

        ArrayList<Integer> dataSet = listMaker();
        
        int listSize = dataSet.size();

        
        double dMedian = 0;

        
        System.out.println("The median of your data set is "+ dMedian+"!\n\n");
    }

    //Not implimented yet
    public void findMode(){
        
    }


    //This runs though and makes sure the user inputs legal choices when picking numbers of some kind. 
    
    public int choiceChecker(int nLowerBounds, int nUpperBounds)
    {
        String userInputString = "";
        int userInputInt = 0;
        boolean userCompliance = false;
        Boolean noBounds = false;
        //This try block is used to not only make sure that the user enters a number, but also that the number follows the set parameters
        while(!userCompliance)
        {
            userInputInt = 0;
            try
            {
                //If the upper and lower bounds are both -100, then that indicates that we don't have any bounds, as -100 should never end up being the real bounds
                if(nLowerBounds == -100 && nUpperBounds == -100) noBounds = true;
                if(noBounds) System.out.println("Please enter an integer");
                else System.out.println("Please enter an integer from " + nLowerBounds + " to " + nUpperBounds+ ".");
                //This set is used to make sure the user enters an integer. If i call for an integer and they input anything else, the program keeps breaking
                //This way, I can call for a string, and then just change it into an int. If that doesn't work, then I can handle it and work from there.
                userInputString = scan.nextLine();
                userInputInt = Integer.parseInt(userInputString);
                //This here is the base case for this
                if((userInputInt >= nLowerBounds && userInputInt <= nUpperBounds) || (noBounds)) userCompliance = true;
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
        return userInputInt;
    }
    

    //This method goes through and makes an array list of values that are later returned. 
    //It uses the choice checker to make sure that the user inputs proper answers. 
    public ArrayList listMaker(){

        ArrayList<Integer> dataSet = new ArrayList();
        System.out.println("\nWhat is in your dataset?\n");
        boolean bHasMoreInputs = true;

        //This repeats, adding numbers to the array list until the user opts out 
        while(bHasMoreInputs){
            System.out.print("You have entered: ");
            for (int iIterator =0; iIterator < dataSet.size(); iIterator++) System.out.print(dataSet.get(iIterator) + " ,");
            System.out.println(" so far. Would you like to enter more?\n1) Yes!\n2) No, I'm Done.");
            userChoice = choiceChecker(1,2);
            if(userChoice == 1){
                System.out.println("Please enter the number you would like to add");
                dataSet.add(scan.nextInt());
            }
            if(userChoice == 2) bHasMoreInputs = false;
        }
        userChoice = 0;
        return dataSet;
    }
}

