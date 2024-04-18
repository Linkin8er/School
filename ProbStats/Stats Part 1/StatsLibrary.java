/*
*   This is to be a library of everything we are learning in prob stats
*   Created by Nicholas Stephani, 01/24/2024   
*/

/*
*   01/25/2024 Currently there is a bug
*   After entering numbers into the data set for mean, choice checker requests you enter a proper number
*   Also, after finnishing the list and getting the mean, it immediately restarts you in the mean function 
*/

/*
*   02/20/2024
*   It has been way too long since I worked on this
*   There is still a bug in the choice checker, but it isnt breaking, so progress time
*   I HAVE FIXED THE BUG, AW YEAHHHHHH
*   Median function, arraylist sorter, and mode function are implimented.
*/

/*
*   02/21/2024
*   Implimented the Mode method using a hashmap. Very useful! Will nead to learn more about them
*/

/*
*   02/24/2024
*   implemeneted the permutations and combinations methods
*/

/*
*   02/25/2024
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
*   02/27/2024
*   I forgot the set operations
*   attempting to implement union, intersection, and compliment
*
*/

/*
*   02/29/2024
*   Adding Hypergeometric Distributions
*   Also adding Expected and Variance for the Distributions
*
*/

/*
*   03/01/2024
*   Fixed Hypergeometric, and actually added
*   Expected and Variance for the Distributions
*
*/

/*
*   03/29/2024
*   Got back review, and this needs work. 
*   No more tight coupling, printlines or scanners inside of a computation methods.
*   Need to make this more of an actual, useable program.
*
*/

//Add conditional prob
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

/**
 * This is my stats library for 2024 Spring stats class
 * Curently includes: 
 * set operations
 * mean, median, mode
 * standard deviation
 * permutation
 * combination
 * Binomial distribution (PMF, μ, σ^2, σ)
 * Geometric distribution(PMF, μ, σ^2, σ)
 * Hypergeometric distribution(PMF, μ, σ^2, σ)
 */
public class StatsLibrary{

    private double userChoice;
    Scanner scan = new Scanner(System.in); 

    /**
     * This method runs a loop that calls other methods depending on what the user needs to do
     * Also it calles whatever method is needed to preempt the needed function
     * This includes things like making the needed lists for set operations
     */
    public void operationPicker(){
        boolean wantsToContinue = true;
        while(wantsToContinue)
        {
            System.out.println("1) Do some set operations\n2) Find a mean\n3) Find a median\n4) Find a mode\n5) Find the standard deviation\n6) Find a permutation\n7) Find a combination\n8) Find a binomial distribution\n9) Find a Geometric distribution\n10) Find a Hypergeometric distribution\n11) End");
            userChoice = choiceChecker(1,11);
            if(userChoice == 1) setOperations();
            if(userChoice == 2) System.out.println("The mean of your data set is "+ (findMean(listMaker()))+"!\n");
            if(userChoice == 3) System.out.println("The median of your data set is "+ (findMedian(listMaker())+"!\n"));
            if(userChoice == 4) System.out.println("The mode of your data set is "+ (findMode(listMaker()))+"!\n");
            if(userChoice == 5) System.out.println("The Standard Deviation of the set is: "+ standardDeviationCalculator(listMaker()));
            if(userChoice == 6) System.out.println("The total permutations would be " + findPermutation(0, 0)+ "!\n");
            if(userChoice == 7) System.out.println("The total combinations would be " + findCombination(0, 0)+ "!\n");
            if(userChoice == 8) System.out.println("The Binomial Distribution is " + findBinomialDistribution()+ "!\n");
            if(userChoice == 9) System.out.println("The Geometric Distribution is " + findGeometricDistribution() + "!\n");
            if(userChoice == 10) System.out.println("The Hypergeometric Distribution is " + findHypergometricDistribution() + "!\n");
            if(userChoice == 11) wantsToContinue = false;
        }
        System.out.println("\n\nHave a great day!");
    }

    /**
     * This just runs a for loop to multiply a number by each whole number less than it greater than 0
     * (factorial!)
     * @param x The number to be factored
     * @return Returns the factorial as a BigInteger (Some get quite large)
     */
    public BigInteger factorialB(int x){
        BigInteger xFactorialB = BigInteger.valueOf(1);
        for (int i = x; i > 0; i--){
            
            xFactorialB = xFactorialB.multiply(BigInteger.valueOf(i));
        }
        return xFactorialB;
    }

    /**
     * This also finds the factorail, same as factorialB. 
     * As it returns a long however, there is a decent chance the numbers get too big and it does not work
     * @param x The number to be factored
     * @return Returns the factorial as a long
     */
    public long factorialL(int x){
        
        long xFactorialL = 1;
        for (int i = x; i > 0; i--){
            
            xFactorialL = xFactorialL* i;
        }
        return xFactorialL;
    }
    
    /**
     * This is used to select set operations to perform
     * performs:
     * Union, Intersection, and Compliment
     * This also handles the creation of the sets that are to be used
     */
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
        userChoice = 1;
    }  

    /**
     * This goes through and adds 1 copy of each unique element in one list to a union list
     * Then it does the same for the second
     * This will create a combined list with 1 copy of each element
     * @param setOne The first list to be unioned
     * @param setTwo The second list to be unioned
     * @return The union of the two sets
     */
    public ArrayList<Double> findUnion(ArrayList<Double> setOne, ArrayList<Double> setTwo){

        ArrayList<Double> unionedSet = new ArrayList();
        boolean inSet;

        for(int w = 0; w < setOne.size(); w++){
            inSet = false;
            for(int x = 0; x < unionedSet.size(); x++){
                //Why does java think 1 != 1? WHY
                //Turns out doubles are odd
                if(setOne.get(w).equals(unionedSet.get(x))) inSet = true;
            }
            if(!inSet) unionedSet.add(setOne.get(w));
        }
        for(int y = 0; y < setTwo.size(); y++){
            inSet = false;
            for(int z = 0; z < unionedSet.size(); z++){
                if(setTwo.get(y).equals(unionedSet.get(z))) inSet = true;
            }
            if(!inSet) unionedSet.add(setTwo.get(y));
        }
        return unionedSet;
    }

    /**
     * This goes though and find the intersect by checking what elements in set 1 are also in set 2
     * It does this by maintaining an intersect list, which it then checks every element against
     * @param setOne The first set for the intersection
     * @param setTwo The second set for the intersecction
     * @return The intersection of both sets
     */
    public ArrayList<Double> findIntersect(ArrayList<Double>setOne, ArrayList<Double>setTwo){

        ArrayList<Double> intersectionedSet = new ArrayList();
        boolean inSet;

        for(int x = 0; x < setOne.size(); x++){
            inSet = false;
            for(int y = 0; y < setTwo.size(); y++){
                
                if(setOne.get(x).equals(setTwo.get(y)) && !(inSet)){
                    inSet = true;
                    intersectionedSet.add(setOne.get(x));
                }
            }
        }
        return intersectionedSet;
    }

    /**
     * This finds all the elements that are not in the first set that aren't in the second
     * it is assumed the second set will be the total set
     * @param setOne The set which we want to find the compliment of
     * @param setTwo The whole total set, or just annother set
     * @return The compliment of setOne
     */
    public ArrayList<Double> findCompliment(ArrayList<Double>setOne, ArrayList<Double>setTwo){
        ArrayList<Double> complimentSet1 = new ArrayList();
        boolean inSet;

        for(int x = 0; x < setTwo.size(); x++){
            inSet = false;
            for(int y = 0; y < setOne.size(); y++){
                
                if(setTwo.get(x).equals(setOne.get(y)))inSet = true;
            }
            if(!inSet) complimentSet1.add(setTwo.get(x));
        }
        return complimentSet1;
    }



    /**
     * This will add up the values in a list, and divides by the total number of elements
     * @param dataSet The dataset to find the mean of
     * @return The mean(μ) of the dataset
     */
    public double findMean(ArrayList<Double> dataSet){
        
        double dSum = 0;
        for(double iSingleElement : dataSet){
            dSum = dSum + iSingleElement;
        }
        double mean = dSum/dataSet.size();
        return mean;
        
    }

    /**
     * This is used to find the standard deviation of a list
     * First it takes and finds the mean of a list using the previous method
     * Then,  it subtracts each value in the list by the mean, and squares the result
     * That is then summed, and devided by the size of the data set-1. 
     * This gives the variance, which is rooted to get deviation
     * @param dataSet The dataset which you want the standard deviation(σ) of
     * @return The standard deviation(σ) of the set
     */
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

    /**
     * This method uses a list sorter in order or order a given dataset
     * Then, if the dataset has an odd count, the center of the dataset is the median. That is found by finind the celing of the size/2
     * Otherwise, if it has an even count, then the median is the average of the two center values
     * @param dataSet The dataset you want to find the median of
     * @return The median value
     */
    public double findMedian(ArrayList<Double> dataSet){

        double dMedian = 0;
        ArrayList<Double> sortedList = listSorter(dataSet);
        //Find the celing of size divided by 2 and that is the median
        if(sortedList.size()%2 == 1)dMedian = sortedList.get((int)(Math.ceil(sortedList.size()/2.0))-1);
        //take the average of the size divided by 2 and divided by 2 + 1
        else if(sortedList.size()%2 == 0 && sortedList.size() != 0)dMedian = (sortedList.get(sortedList.size()/2-1)+sortedList.get((sortedList.size()/2)))/2.0;
        else System.out.println("There is no median of nothing!");
        
        return dMedian;
    }

    /**
     * This uses a HashMap to find the mode of a dataset
     * The HashMap allows us to store the count of each value of a dataset
     * We do this by looping through the dataset, counting each unique value
     * Then we return whichever value shows up the most
     * @param dataSet The dataset we want the mode of
     * @return The mode of the dataset
     */
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

    /**
     * This is used to find the number of permutations
     * If both inputs are not 0, then it performs the permutation calculation for those values
     * Otherwise it gets the number of objects in the set(n), and the number of selected objects from the set(r)
     * P(number of permutations) = n!/(n-r)!
     * @param n_TotalObjects the total objects in the set (n)
     * @param r_SelectedObjects the total to be selected (r)
     * @return returns the permutation 
     */
    public BigInteger findPermutation(int n_TotalObjects, int r_SelectedObjects){

        if(n_TotalObjects == 0 && r_SelectedObjects == 0){
            System.out.println("This function requires you enter the total number of objects in the set(n), followed by the number of objects selected(r)");
            System.out.println("First, enter the objects in the set:");
            n_TotalObjects = choiceChecker(0, -100);

            System.out.println("Now, please enter the number of objectes selected, which must be less than or equal to the total objects:");
            r_SelectedObjects = choiceChecker(0, n_TotalObjects);
        }

        BigInteger permutations = (factorialB(n_TotalObjects).divide(factorialB(n_TotalObjects - r_SelectedObjects)));
        return permutations;
        
    }

    /**
     * This is similar to the last method
     * If the two inputs are not 0, then uses the permutation on those values, then divides by the r factorial
     * @param n_TotalObjects The total objects in the set (n)
     * @param r_SelectedObjects the total to be selected (r)
     * @return returns the combination
     */
    public double findCombination(int n_TotalObjects, int r_SelectedObjects){

        //Runs the permutation solver, but devides it's answer by r!
        if(n_TotalObjects == 0 && r_SelectedObjects == 0){
            System.out.println("This function requires you enter the total number of objects in the set(n), followed by the number of chosing objects ");
            System.out.println("First, enter the objects in the set:");
            n_TotalObjects = choiceChecker(0, -100);

            System.out.println("Now, please enter the number of chosing objects, which must be less than or equal to the total objects:");
            r_SelectedObjects = choiceChecker(0, n_TotalObjects);
        }

        double combinations = (findPermutation(n_TotalObjects, r_SelectedObjects)).doubleValue()/(factorialB(r_SelectedObjects)).doubleValue();

        return combinations;

    }

    /**
     * This uses the permutation and combination solver as needed to help with redundancy
     * This can solve for the Binomial Distributions: PMF, Variance, standard deviation, and mean
     * It gets the n(number of trials), y(number of times for a specific outcome within n trials), and p(probability of success on a single trial) to find the binomial distribution
     * @return This returns the whichever the user selects of the PMF, Variance, standard deviation, or mean
     */
    public double findBinomialDistribution(){
        //Needs to get n, y, p. solves q as 1-p
        int n_NumberOfTrials;
        int y_NumberOfSucesses;
        double p_ProbabilityOfSucess;

        System.out.println("This function requires you enter:\nThe total number of trials(n)\nThe specific number of sucesses you want\nThe probability of sucess");
        System.out.println("First, enter the total number of trials:");
        n_NumberOfTrials = choiceChecker(0, -100);

        System.out.println("Now, please enter the exact number of sucesses, which must be less than or equal to the number of trials:");
        y_NumberOfSucesses = choiceChecker(0, n_NumberOfTrials);

        System.out.println("Finally, please enter the probability of sucess, which must be written as a decimal:");
        p_ProbabilityOfSucess = choiceChecker(0.0, 1.0);

        //The probability of failure is going to be 1 - the probability of sucess. 
        double q_ProbabilityOfFailure = 1 - p_ProbabilityOfSucess;

        double binomialDistribution = 0;
        System.out.println("Now, would you like: \n1) The PMF\n2) The expected/mean\n3) The variance\n4) The Standard Deviation");
        userChoice = choiceChecker(1,4);
        if(userChoice == 1) binomialDistribution = findCombination(n_NumberOfTrials, y_NumberOfSucesses) * Math.pow(p_ProbabilityOfSucess, y_NumberOfSucesses) * Math.pow(q_ProbabilityOfFailure, (n_NumberOfTrials-y_NumberOfSucesses));
        if(userChoice == 2) binomialDistribution = n_NumberOfTrials * p_ProbabilityOfSucess;
        if(userChoice == 3 || userChoice == 4) binomialDistribution = n_NumberOfTrials * p_ProbabilityOfSucess * q_ProbabilityOfFailure;
        if(userChoice == 4) binomialDistribution = Math.sqrt(binomialDistribution);

        return binomialDistribution;
    }

   /**
     *
     * This can solve for the Geometric Distribution: PMF, Variance, standard deviation, and mean
     * It gets the n(trial to get sucess), p(probability of success on a single trial), and q(probability of failure on a single trial)
     * @return This returns the whichever the user selects of the PMF, Variance, standard deviation, or mean
     */
    public double findGeometricDistribution(){
        //Needs to get n, p, and q
        int n_NumberOfTrials;
        double p_ProbabilityOfSucess;

        System.out.println("This function requires you enter:\nThe total number of trials until sucess(y)\nand the probability of sucess");
        System.out.println("First, enter the total number of trials(n):");
        n_NumberOfTrials = choiceChecker(0, -100);

        System.out.println("Finally, please enter the probability of sucess, which must be written as a decimal:");
        p_ProbabilityOfSucess = choiceChecker(0.0, 1.0);

        //The probability of failure is going to be 1 - the probability of sucess. 
        double q_ProbabilityOfFailure = 1 - p_ProbabilityOfSucess;

        double geometricDistribution = 0;
        System.out.println("Now, would you like: \n1) The PMF\n2) The expected/mean\n3) The variance\n4) The Standard Deviation");
        userChoice = choiceChecker(1,4);
        if(userChoice == 1) geometricDistribution = (Math.pow(p_ProbabilityOfSucess, (n_NumberOfTrials-1)) * q_ProbabilityOfFailure);
        if(userChoice == 2) geometricDistribution = 1/p_ProbabilityOfSucess;
        if(userChoice == 3 || userChoice == 4) geometricDistribution = ((q_ProbabilityOfFailure)/Math.pow(p_ProbabilityOfSucess, 2));
        if(userChoice == 4) geometricDistribution = Math.sqrt(geometricDistribution);
        
        return geometricDistribution;
    }

    /**
     * Solves for the Hypergeometric Distribution: PMF, Variance, standard deviation, and mean
     * Needs to get n(Our sample size), y(How many of our desired we want), r(Count of desired in set), and N(Total set size)
     * @return
     */
    public double findHypergometricDistribution(){

        
        int n_SampleSet;
        int y_Desired;
        int r_DesiredSet;
        int N_TotalSetCount;
        int notInSet;
        int notDesiredInSample;

        System.out.println("This function requires you enter:\nThe total set size\nThe count of desired in the set\nThe sample size\nHow many of the desired you want");
        System.out.println("First, enter the total set size (N):");
        N_TotalSetCount = choiceChecker(0, -100);

        System.out.println("Now, please enter the count of desired in the set (r), which must be less than or equal to the total set size:");
        r_DesiredSet = choiceChecker(0, N_TotalSetCount);

        System.out.println("Now, please enter the sample size (n), which must be less than or equal to the total set size:");
        n_SampleSet = choiceChecker(0, N_TotalSetCount);

        System.out.println("Finally, please enter How many of the desired you want (y), which must be less than or equal to the count of the sample set:");
        y_Desired = choiceChecker(0, n_SampleSet);

        notInSet = N_TotalSetCount - r_DesiredSet;
        notDesiredInSample = n_SampleSet - y_Desired;

        double hyperGeometricDistribution = 0;
        System.out.println("Now, would you like: \n1) The PMF\n2) The expected/mean\n3) The variance\n4) The Standard Deviation");
        userChoice = choiceChecker(1,4);
        if(userChoice == 1) hyperGeometricDistribution = (findCombination(r_DesiredSet, y_Desired) * findCombination(notInSet, notDesiredInSample)) / (findCombination(N_TotalSetCount, n_SampleSet));
        if(userChoice == 2) hyperGeometricDistribution = ((n_SampleSet * r_DesiredSet) / (N_TotalSetCount)); 
        if(userChoice == 3 || userChoice == 4) hyperGeometricDistribution = (n_SampleSet * (r_DesiredSet/N_TotalSetCount) * ((N_TotalSetCount - r_DesiredSet)/(N_TotalSetCount)) * ((N_TotalSetCount - n_SampleSet)/(N_TotalSetCount-1)));
        if(userChoice == 4) hyperGeometricDistribution = Math.sqrt(hyperGeometricDistribution);
        
        return hyperGeometricDistribution;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                        //
// These next methods don't do any calculations                                                           //
// They are for getting information safely, and sorting information                                       //
//                                                                                                        //
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This is used in all of my programs to make sure a user enters a legal Integer. 
     * if both values are -100, then it is assumed there is no bounds
     * @param nLowerBounds The upper bounds[inclusive] allowed
     * @param nUpperBounds The lower bounds[inclusive] allowed
     * @return
     */
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
    
    /**
     * This is used in all of my programs to make sure a user enters a legal double. 
     * if both values are -100, then it is assumed there is no bounds
     * @param nLowerBounds The upper bounds[inclusive] allowed
     * @param nUpperBounds The lower bounds[inclusive] allowed
     * @return
     */
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
            for (int iIterator =0; iIterator < dataSet.size(); iIterator++) System.out.print(dataSet.get(iIterator) + ", ");
            System.out.println("so far. Would you like to enter more?\n1) Yes!\n2) No, I'm Done.");
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

