//////////////////////////////////////////////////////////////////////////////
//                                                                          //
//                          Author: Nicholas Stephani                       //
//                          Project: Project 2                              //
//                          Date: 12/07/2020                                //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////

/*
    The purpose of this program is to use an array to make and maintain a heap
    There are 3 main methods, add, remove, and print. 
    The add method will add a new number to the heap, sorting as it adds in.
    The remove method removes the number at the top of the heap, and then organizes itself.
    The print method goes though and prints out he whole array in order.
    The variables in this are named with hungarian notation, where the first letter shows the data type, followed by camel case.
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Project2
{
    Scanner scan = new Scanner(System.in);
    protected ArrayList<Integer> table;
    public static void main(String[] args)
    {

        Project2 workbot = new Project2();
        boolean bWantsToKeepGoing = true;
        int nChoice;

        System.out.println("Hello and welcome to the heap utilizing list!");
        System.out.println("Here, we can store integers within our list.");
        System.out.println("Why dont you try out one of our wonderful options on how you can use our list!");

        //This section of code will run until the user decides they don't want to use the program anymore.
        while(bWantsToKeepGoing)
        {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add a value to the list\n2. Remove a value from the list\n3. Print out the list\n4. Quit");
            nChoice = workbot.choiceChecker(1, 4);
            if(nChoice == 1)
            {
                System.out.println("Please input the number you want to add!");
                nChoice = workbot.choiceChecker(0,0);
                workbot.addToList(nChoice);
                nChoice = 1;
            } 
            if(nChoice == 2)
            {
                workbot.removeValue();
            }
            if(nChoice == 3)
            {
                workbot.printList();
            }
            if(nChoice == 4)
            {
                bWantsToKeepGoing = false;
            } 
        }
        System.out.println("Have a great day!");
    }

    Project2()
    {
        table = new ArrayList();
    }

    /*
    @param : needs a number to be added in
    @post : will add the number to the array, and then will sort so it remains a heap format
    */
    public void addToList(int nAddedNumber)
    {
        int nChild;
        int nParent;
        int nTemp;

        table.add(nAddedNumber);

        nChild = table.size() - 1;
        nParent = (nChild - 1)/2;
        
        //This loop will run until the number that was added is less than its parent node, or is at the top
        while(((nParent >= 0) && (( table.get(nParent)).compareTo( table.get(nChild)) >= 0)) && (nChild != 0))
        {
            nTemp = table.get(nParent);
            table.set(nParent, table.get(nChild));
            table.set(nChild, nTemp);

            nChild = nParent;
            nParent = (nChild - 1)/2;
        }
        System.out.println(nAddedNumber + " was added!");
    }

    /*
    @param : none
    @pre : an array you want to remove the top number from
    @post : will remove the top of the array by putting the last number in it's place, and then organizing down.
    */
    public void removeValue()
    {

        int nParent = 0;
        boolean bIsNotDone = true;
        int nLeftChild;
        int nRightChild;
        int nMinChild;
        int nNumberRemoved = 0;

        //This checks to make sure the array isn't empty and therefore doesn't cause any out of bounds errors
        if(table.size() != 0) 
        {
            nNumberRemoved = table.get(0);
            table.set(0, table.size()-1);
            table.remove(table.size()-1);

            //This section of code will run until the heap is properly reorganized and we get to the end of the list
            while(bIsNotDone)
            { 

                nLeftChild = (2*nParent) + 1;
                nRightChild = (nLeftChild + 1);

                //This is the condition to check and see if we are at the end of the list
                if (nLeftChild >= table.size())
                {
                    System.out.println("All done, removed " + nNumberRemoved);
                    bIsNotDone = false;
                }
                else 
                {
                    //This section of code checks which child node is less
                    nMinChild = nLeftChild;
                    if(nRightChild < table.size() && table.get(nRightChild) < table.get(nLeftChild))
                    {
                        nMinChild = nRightChild;
                    }

                    //This section is where the program actually checks and makes sure that the child nodes are not bigger than the parent
                    if(table.get(nParent) > table.get(nMinChild))
                    {
                        int nTemp = table.get(nParent);
                        table.set(nParent, table.get(nMinChild));
                        table.set(nMinChild, nTemp);
                        nParent = nMinChild;
                    }
                }
            }
        }
        else System.out.println("There is no number to remove!");
    }

    /*
    @post : will simply print out the array
    */
    public void printList()
    {
        for(int x=0; x < table.size(); x++)
        {
            if(x < table.size()-1)System.out.print(table.get(x)+", ");
            else System.out.print(table.get(x));
        }
    }

    //This method is used to check and make sure the user is entering good numbers. The two integers enters are generally the bounds of the accepted integers.
    public int choiceChecker(int nLowerBounds, int nUpperBounds)
    {
        String userInputString;
        int userInputInt;
        Boolean noBounds = false;
        //This try block is used to not only make sure that the user enters a number, but also that the number follows the set parameters
        try
        {
            //If the upper and lower bounds are both -100, then that indicates that we don't have any bounds, as -100 should never end up being the real bounds
            if(nLowerBounds ==  nUpperBounds) noBounds = true;

            if(noBounds) System.out.println("Please enter an integer");
            else System.out.println("Please enter an integer from " + nLowerBounds + " to " + nUpperBounds+ ".");

            //This set is used to make sure the user enters an integer. If i cal for an integer and they input anything else, the program keeps breaking
            //This way, I can call for a string, and then just change it into an int. If that doesn't work, then I can handle it and work from there.
            userInputString = scan.nextLine();
            userInputInt = Integer.parseInt(userInputString);

            //This here is the base case for this
            if((userInputInt >= nLowerBounds && userInputInt <= nUpperBounds) || (noBounds))
            {
                return userInputInt;
            }
            //This else will run if the user enters a number that is outside the bounds in which case it uses recursion until the user complies
            else
            {
                System.out.println("That was not within the bounds");
                userInputInt =  choiceChecker(nLowerBounds, nUpperBounds);
                return userInputInt;
            }
        } 
        //This catch is to make sure the user enters an integer
        //If they don't, it will use recursion until they do
        catch (NumberFormatException exception)
        {
            userInputInt = 0;
            System.out.println("Please enter a proper number next time.");
            userInputInt = choiceChecker(nLowerBounds, nUpperBounds);
        }
        return userInputInt;
    }
}