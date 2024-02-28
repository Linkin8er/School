/*
*   This is to be a library of everything we are learning in prob stats
*   Created by Nicholas Stephani, 01/24/2024   
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
*   02/21/2023
*   Implimented the Mode method using a hashmap. Very useful! Will nead to learn more about them
*/

/*
*   02/24/2023
*   implemeneted the permutations and combinations methods
*/

/*
*   02/25/2023
*   I have realized I should change most of this porogram
*   Instead of running out of the constructor, I should make a method that runs it all
*   I also should have each method do a calculation, without any output other than the return
*   I can have the runer method make lists and such when you select an operation that needs one
*   This will make it easier to manage, as well as make the mothods more useful
*   Cutting it kinda close, but ah well.
*   Edited choice checker to include only a lower bounds
*   Implimented binomial distribution
*/

/*
*   02/27/2023
*   I forgot the set operations
*   attempting to implement union, intersection, and compliment
*
*/

//Add conditional prob
import java.math.BigInteger;
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
        System.out.println("Hello there! I hear you may have some statistics assignments, what would you like to do?");
        operationPicker();
    }
    //This method runs a loop that calls other methods depending on what the user needs to do
    public void operationPicker(){
        boolean wantsToContinue = true;
        while(wantsToContinue)
        {
            System.out.println("1) Find a mean\n2) Find a median\n3) Find a mode\n4) Find the standard deviation\n5) Find a permutation\n6) Find a combination\n7) Find a binomial distribution\n8) Find a Geometric distribution\n9) Do some set operations\n10)End");
            userChoice = choiceChecker(1,10);
            if(userChoice == 1) System.out.println("The mean of your data set is "+ (findMean(listMaker()))+"!\n");
            if(userChoice == 2) System.out.println("The median of your data set is "+ (findMedian(listSorter(listMaker()))+"!\n"));
            if(userChoice == 3) System.out.println("The mode of your data set is "+ (findMode(listMaker()))+"!\n");
            if(userChoice == 4) System.out.println("The Standard Deviation of the set is: "+ standardDeviationCalculator(listMaker()));
            if(userChoice == 5) System.out.println("The total permutations would be " + findPermutation(0, 0)+ "!\n");
            if(userChoice == 6) System.out.println("The total combinations would be " + findCombination(0, 0)+ "!\n");
            if(userChoice == 7) System.out.println("The Binomial Distribution is " + findBinomialDistribution()+ "!\n");
            if(userChoice == 8) System.out.println("The Geometric Distribution is " + findGeometricDistribution() + "!\n");
            if(userChoice == 9) setOperations();
            if(userChoice == 10) wantsToContinue = false;
        }
        System.out.println("\n\nHave a great day!");
    }

    //This just runs a for loop to multiply a number by each whole number less than it greater than 0(factorial!).
    public BigInteger factorialB(int x){
        BigInteger xFactorialB = BigInteger.valueOf(1);
        for (int i = x; i > 0; i--){
            
            xFactorialB = xFactorialB.multiply(BigInteger.valueOf(i));
        }
        return xFactorialB;
    }

    public long factorialL(int x){
        long xFactorialL = 1;
        for (int i = x; i > 0; i--){
            
            xFactorialL = xFactorialL* i;
        }
        return xFactorialL;
    }
    
    //Takes and finds the mean of a list. Then, subtracts each value in the list by the mean, and squares the result
    //That is then summed, and devided by the size of the data set-1. This gives the variance, which is rooted to get deviation
    public double standardDeviationCalculator(ArrayList<Double> dataSet){

        //Finds the mean of the list
        double listMean = findMean(dataSet);
        double sum = 0;
        for(int i = 0; i < dataSet.size(); i++){
            sum += Math.pow((dataSet.get(i)-listMean), 2);
        }
        double standardDeviation = Math.sqrt(sum/(dataSet.size()-1));
        return standardDeviation;

    }

    //Easy method, just adds up the values in a list, and divides by the total number of elements
    public double findMean(ArrayList<Double> dataSet){
        
        double dSum = 0;
        for(double iSingleElement : dataSet){
            dSum = dSum + iSingleElement;
        }
        double mean = dSum/dataSet.size();
        return mean;
    }

    //This one is also easy. It takes in a sorted list, and checks to see the size to find the median
    public double findMedian(ArrayList<Double> dataSet){

        double dMedian = 0;

        //Find the celing of size divided by 2 and that is the median
        if(dataSet.size()%2 == 1)dMedian = dataSet.get((int)(Math.ceil(dataSet.size()/2.0))-1);
        //take the average of the size divided by 2 and divided by 2 + 1
        else if(dataSet.size()%2 == 0 && dataSet.size() != 0)dMedian = (dataSet.get(dataSet.size()/2-1)+dataSet.get((dataSet.size()/2)))/2.0;
        else System.out.println("There is no median of nothing!");
        
        return dMedian;
    }

    //The mode is extra fun, it uses a hash map to store each value, and the number of times it is repeated
    public double findMode(ArrayList<Double> dataSet){

        HashMap<Double,Integer> modeTracker = new HashMap<Double,Integer>();

        int max  = 1;
        double currentMode = 0;

        for(int i = 0; i < dataSet.size(); i++) {

            if (modeTracker.get(dataSet.get(i)) != null) {

                int count = modeTracker.get(dataSet.get(i));
                count++;
                modeTracker.put(dataSet.get(i), count);

                if(count > max) {
                    max  = count;
                    currentMode = dataSet.get(i);
                }
            }

            modeTracker.put(dataSet.get(i), 1);
        }
        
        return currentMode;
    }

    //Gets the number of objects in the set(n), and the number of selected objects from the set(r)
    //P(number of permutations) = n!/(n-r)!
    public int findPermutation(int totalObjects, int selectedObjects){

        if(totalObjects == 0 && selectedObjects == 0){
            System.out.println("This function requires you enter the total number of objects in the set(n), followed by the number of objects selected(r)");
            System.out.println("First, enter the objects in the set:");
            totalObjects = choiceChecker(0, -100);

            System.out.println("Now, please enter the number of objectes selected, which must be less than or equal to the total objects:");
            selectedObjects = choiceChecker(0, totalObjects);
        }

        int permutations = (factorialB(totalObjects).divide(factorialB(totalObjects - selectedObjects))).intValue();
        return permutations;
        
    }

    public long findCombination(int totalObjects, int chosingObjects){

        //Runs the permutation solver, but devides it's answer by r!
        if(totalObjects == 0 && chosingObjects == 0){
            System.out.println("This function requires you enter the total number of objects in the set(n), followed by the number of chosing objects ");
            System.out.println("First, enter the objects in the set:");
            totalObjects = choiceChecker(0, -100);

            System.out.println("Now, please enter the number of chosing objects, which must be less than or equal to the total objects:");
            chosingObjects = choiceChecker(0, totalObjects);
        }

        int combinations = BigInteger.valueOf(findPermutation(totalObjects, chosingObjects)).divide(factorialB(chosingObjects)).intValue();

        return combinations;

    }

    //use the permutation and combination solver as needed to help with redundancy
    public double findBinomialDistribution(){
        //Needs to get n(number of trials), x(number of times for a specific outcome within n trials), 
        //p(probability of success on a single trial), and q(probability of failure on a single trial)
        int numberOfTrials;
        int numberOfSucesses;
        double probabilityOfSucess;

        System.out.println("This function requires you enter:\nThe total number of trials(n)\nThe specific number of sucesses you want\nThe probability of sucess");
        System.out.println("First, enter the total number of trials:");
        numberOfTrials = choiceChecker(0, -100);

        System.out.println("Now, please enter the exact number of sucesses, which must be less than or equal to the number of trials:");
        numberOfSucesses = choiceChecker(0, numberOfTrials);

        System.out.println("Finally, please enter the probability of sucess, which must be written as a decimal:");
        probabilityOfSucess = choiceChecker(0.0, 1.0);

        //The probability of failure is going to be 1 - the probability of sucess. 
        double probabilityOfFailure = 1 - probabilityOfSucess;

        double binomialDistribution = findCombination(numberOfTrials, numberOfSucesses) * Math.pow(probabilityOfSucess, numberOfSucesses) * Math.pow(probabilityOfFailure, (numberOfTrials-numberOfSucesses));

        return binomialDistribution;
    }

    public void setOperations(){
        System.out.println("What kind of set operations were you looking to do?");
        boolean wantsToContinue = true;
        
        while(wantsToContinue)
        {
            System.out.println("1) Find a Union\n2) Find an Intersection\n3) Find the compliment\n4) End");
            userChoice = choiceChecker(1,4);

            if(userChoice == 1){
                System.out.println("This operation requires you make two sets of numbers, which will then be unioned.");
                System.out.println("Please begin entering your first set:");
                ArrayList<Double> setOne = listSorter(listMaker());

                System.out.println("Now please begin entering your second set:");
                ArrayList<Double> setTwo = listSorter(listMaker());
                System.out.println("The union of your two sets is "+ findUnion(setOne, setTwo) +"!\n");
            }

            if(userChoice == 2){
                System.out.println("This operation requires you make two sets of numbers, which we will then find the intersect of.");
                System.out.println("Please begin entering your first set:");
                ArrayList<Double> setOne = listSorter(listMaker());

                System.out.println("Now please begin entering your second set:");
                ArrayList<Double> setTwo = listSorter(listMaker());
                System.out.println("The intersection of your two sets is "+ findIntersect(setOne, setTwo) +"!\n");
            }

            if(userChoice == 3){
                System.out.println("This operation requires you make two sets of numbers, the set to be complimented and the whole set.");
                System.out.println("Please begin entering your first set:");
                ArrayList<Double> setOne = listSorter(listMaker());

                System.out.println("Now please begin entering the entire set:");
                ArrayList<Double> setTwo = listSorter(listMaker());
                System.out.println("The compliment of your set is "+ findCompliment(setOne, setTwo) +"!\n");
            }
            if(userChoice == 4) wantsToContinue = false;
        }
    }

    public ArrayList<Double> findUnion(ArrayList<Double> setOne, ArrayList<Double> setTwo){

        ArrayList<Double> unionedSet = new ArrayList();
        boolean inSet;

        for(int w = 0; w < setOne.size(); w++){
            inSet = false;
            for(int x = 0; x < unionedSet.size(); x++){
                //Why does java think 1 != 1? WHY
                if(setOne.get(w) == unionedSet.get(x)) inSet = true;

                System.out.println(setOne.get(w) +"\t" + unionedSet.get(x) + "\t" + inSet );
            }
            if(!inSet) unionedSet.add(setOne.get(w));
        }
        for(int y = 0; y < setTwo.size(); y++){
            inSet = false;
            for(int z = 0; z < unionedSet.size(); z++){
                if(setTwo.get(y) == unionedSet.get(z)) inSet = true;
                System.out.println(setTwo.get(y) +"\t" + unionedSet.get(z) + "\t" + inSet );
            }
            System.out.println("inset test +" + inSet);
            if(!inSet) unionedSet.add(setTwo.get(y));
        }
        return unionedSet;
    }

    public ArrayList<Double> findIntersect(ArrayList<Double>setOne, ArrayList<Double>setTwo){
        return setOne;
    }

    public ArrayList<Double> findCompliment(ArrayList<Double>setOne, ArrayList<Double>setTwo){
        return setOne;
    }

    public double findGeometricDistribution(){
        //Needs to get n(trial to get sucess), p(probability of success on a single trial), and q(probability of failure on a single trial)
        int numberOfTrials;
        double probabilityOfSucess;

        System.out.println("This function requires you enter:\nThe total number of trials until sucess(y)\nand the probability of sucess");
        System.out.println("First, enter the total number of trials:");
        numberOfTrials = choiceChecker(0, -100);

        System.out.println("Finally, please enter the probability of sucess, which must be written as a decimal:");
        probabilityOfSucess = choiceChecker(0.0, 1.0);

        //The probability of failure is going to be 1 - the probability of sucess. 
        double probabilityOfFailure = 1 - probabilityOfSucess;

        double geometricDistribution = (Math.pow(probabilityOfSucess, (numberOfTrials-1)) * probabilityOfFailure);
        return geometricDistribution;
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
            if(userChoice == 1) dataSet.add(choiceChecker(-100.0,-100.0));
            if(userChoice == 2) bHasMoreInputs = false;
        }
        userChoice = 0;
        return dataSet;
    }

    //listSorter uses an insertion sort to sort a given arraylist
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

