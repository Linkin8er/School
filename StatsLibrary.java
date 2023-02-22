/*
*   This is to be a library of everything we are learning in prob stats
*   Created by Nicholas Stephani, 01/24/2023
*/

/*
*   01/25/2023 Currently there is a bug
*   After entering numbers into the data set for mean, choice checker requests you enter a proper number
*   Also, after finnishing the list and getting the mean, it immediately restarts you in the mean function 
*/

/*
*   02/20/2023
*   It has been way too long since I worked on this
*   There is still a bug in the choice checker, but it isnt breaking, so progress time
*   I HAVE FIXED THE BUG, AW YEAHHHHHH
*   Median function, arraylist sorter, and mode function are implimented.
*/

/*
*   02/22/2023
*   Implimented the Mode method using a hashmap. Very useful! Will nead to learn more about them
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

//This just runs the stats wizard
public class StatsLibrary {
    public static void main(String[] args){
        StatsWizard runner = new StatsWizard();

    }
}

class StatsWizard{

    private double userChoice;
    Scanner scan = new Scanner(System.in);
    //Constructor time! This runs continuously unti the user opts out. 
    StatsWizard(){
        System.out.println("Hello there! I hear you may have some assignments, would you like to do?");
        boolean wantsToContinue = true;
        
        while(wantsToContinue)
        {
            System.out.println("1) Find a mean\n2) Find a median\n3) Find a mode\n4) Find a combination\n5) Find a permutation\n6) Find a binomial distribution\n7) End");
            userChoice = choiceChecker(1,7);
            if(userChoice == 1) findMean();
            if(userChoice == 2) findMedian();
            if(userChoice == 3) findMode();
            if(userChoice == 4) findCombination();
            if(userChoice == 5) findPermutation();
            if(userChoice == 6) findBinomialDistribution();
            if(userChoice == 7) wantsToContinue = false;
        }
        System.out.println("\n\nHave a great day!");
    }

    public void findMean(){
        
        ArrayList<Double> dataSet = listMaker();
        
        double dSum = 0;
        for(double iSingleElement : dataSet){
            dSum = dSum + iSingleElement;
        }
        
        System.out.println("The mean of your data set is "+ (dSum/dataSet.size())+"!\n\n");
    }

    //Not implimented yet
    public void findMedian(){

        ArrayList<Double> dataSet = listSorter(listMaker());
        double dMedian = 0;

        //Find the celing of size divided by 2 and that is the median
        if(dataSet.size()%2 == 1)dMedian = dataSet.get((int)(Math.ceil(dataSet.size()/2.0))-1);
        //take the average of the size divided by 2 and divided by 2 + 1
        else if(dataSet.size()%2 == 0 && dataSet.size() != 0)dMedian = (dataSet.get(dataSet.size()/2-1)+dataSet.get((dataSet.size()/2)))/2.0;
        else System.out.println("There is no median of nothing!");
        System.out.println("The median of your data set is "+ dMedian+"!\n\n");
    }

    public double findMode(){

        ArrayList<Double> dataSet = listMaker();

        HashMap<Double,Integer> modeTracker = new HashMap<Double,Integer>();

        int max  = 1;
        double temp = 0;

        for(int i = 0; i < dataSet.size(); i++) {

            if (modeTracker.get(dataSet.get(i)) != null) {

                int count = modeTracker.get(dataSet.get(i));
                count++;
                modeTracker.put(dataSet.get(i), count);

                if(count > max) {
                    max  = count;
                    temp = dataSet.get(i);
                }
            }

            modeTracker.put(dataSet.get(i), 1);
        }
        return temp;
    }

    public void findCombination(){

    }

    public void findPermutation(){
        
    }

    public void birthdayChecker(){

    }

    //use the permutation and combination solver as needed to help with redundancy
    public void findBinomialDistribution(){

    }
    //This runs though and makes sure the user inputs legal choices when picking numbers of some kind. 
    
    public double choiceChecker(int nLowerBounds, int nUpperBounds)
    {
        String userInputString = "";
        double userInputDouble = 0;
        boolean userCompliance = false;
        Boolean noBounds = false;
        //This try block is used to not only make sure that the user enters a number, but also that the number follows the set parameters
        while(!userCompliance)
        {
            try
            {
                //If the upper and lower bounds are both -100, then that indicates that we don't have any bounds, as -100 should never end up being the real bounds
                if(nLowerBounds == -100 && nUpperBounds == -100) noBounds = true;
                if(noBounds) System.out.println("Please enter an your number");
                else System.out.println("Please enter an integer from " + nLowerBounds + " to " + nUpperBounds+ ".");
                //This set is used to make sure the user enters an integer. If i call for an integer and they input anything else, the program keeps breaking
                //This way, I can call for a string, and then just change it into an int. If that doesn't work, then I can handle it and work from there.
                userInputString = scan.nextLine();
                userInputDouble = Double.parseDouble(userInputString); 
                //This here is the base case for this
                if((userInputDouble >= nLowerBounds && userInputDouble <= nUpperBounds) || (noBounds)) userCompliance = true;
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
    

    //This method goes through and makes an array list of values that are later returned. 
    //It uses the choice checker to make sure that the user inputs proper answers. 
    public ArrayList listMaker(){

        ArrayList<Double> dataSet = new ArrayList();
        System.out.println("\nWhat is in your dataset?\n");
        boolean bHasMoreInputs = true;

        //This repeats, adding numbers to the array list until the user opts out 
        while(bHasMoreInputs){
            System.out.print("You have entered: ");
            for (int iIterator =0; iIterator < dataSet.size(); iIterator++) System.out.print(dataSet.get(iIterator) + " ,");
            System.out.println(" so far. Would you like to enter more?\n1) Yes!\n2) No, I'm Done.");
            userChoice = choiceChecker(1,2);
            if(userChoice == 1) dataSet.add(choiceChecker(-100,-100));
            if(userChoice == 2) bHasMoreInputs = false;
        }
        userChoice = 0;
        return dataSet;
    }
    public ArrayList listSorter(ArrayList<Double> unsorted){

        for( int i = 0; i < unsorted.size();  i++){
            double key = unsorted.get(i);
		    //insert A[i] into the sorted subarray A[0:i-1]
            int j = i-1;
            while( j>=0 && unsorted.get(j) > key){
                unsorted.set(j+1, unsorted.get(j));
                j = j-1;
            }
            unsorted.set(j+1, key);
        }
        return unsorted;
    }
}

