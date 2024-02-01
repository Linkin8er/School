//////////////////////////////////////////////////////////////////////////////
//                                                                          //
//                          Author: Nicholas Stephani                       //
//                          Project: Linked Lists                           //
//                          Date: 10/16/2020                                //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////


import java.util.LinkedList;
import java.util.Scanner;


public class Project1
{
    public static void main(String[] args)
    {
        AssignmentListModifier theList = new AssignmentListModifier();
    }
}


class Assignment
{
    //The name in each node
    private String assignmentName;
    //The assigned number
    private int assignmentDueDate;
    protected Assignment(String assignment,int number)
    {
        assignmentName = assignment;
        assignmentDueDate = number;
    }
    public String getName(){ return assignmentName;}
    public int getDueDate(){ return assignmentDueDate;}
}


class AssignmentListModifier
{
    Scanner scan = new Scanner(System.in);
    private boolean foundIt;
    private LinkedList<Assignment> assignmentList;
    public AssignmentListModifier()
    {
        assignmentList = new LinkedList<Assignment>();
        System.out.println("Hello there! I hear you may have some assignments, would you like to do?");
        boolean wantsToContinue = true;
        int userChoice;
        while(wantsToContinue)
        {
            System.out.println("1) Add an assignment\n2) Remove an assignment\n3) List all the assignments\n4) Find the assignment due next\n5) End");
            userChoice = choiceChecker(1,5);
            if(userChoice == 1) addAssignment();
            if(userChoice == 2) removeAssignment();
            if(userChoice == 3) listAssignments();
            if(userChoice == 4) findNextDue();
            if(userChoice == 5) wantsToContinue = false;
        }
    }
    public void addAssignment()
    {
        System.out.println("What would you like the name to be?");
        String assignment = scan.nextLine();
        System.out.println("When is the assignment due? please enter a number in the form of 1yymmdd.");
        int number = choiceChecker(1000101, 1991231);
        Assignment AssignmentToBeAdded = new Assignment(assignment, number);
        assignmentList.addLast(AssignmentToBeAdded);
    }
    public void removeAssignment()
    {
        if (assignmentList.size() == 0) System.out.println("You have no assignments, good for you!");
        else
        {
            System.out.println("What is the name of the assignment you want to remove?");
            String AssignmentName = scan.nextLine();
            foundIt = false;
            for(int i=0; i < assignmentList.size(); i++)
            {
                if(assignmentList.get(i).getName().equalsIgnoreCase(AssignmentName))
                {
                    assignmentList.remove(i);
                    System.out.println("The assignment was removed.");
                    foundIt = true;
                    i = assignmentList.size();
                }
            }
            if (!foundIt) System.out.println("That assignment doesn't exist");
        }
    }
    public void listAssignments()
    {
        if (assignmentList.size() == 0) System.out.println("You have no assignments, good for you!");
        else
        {
            System.out.println("Your assignments are:");
            for(int i=0; i < assignmentList.size(); i++)
            {
                System.out.println((i+1) + ", " + assignmentList.get(i).getName());
            }
        }
    }
    public void findNextDue()
    {
        if (assignmentList.size() == 0) System.out.println("You have no assignments, good for you!");
        else
        {
            int indexOfAssignmentWithLowestDate = 0;
            for(int i=0; i < assignmentList.size()-1; i++)
            {
                if(assignmentList.get(indexOfAssignmentWithLowestDate).getDueDate() > assignmentList.get(i+1).getDueDate())
                {
                    indexOfAssignmentWithLowestDate = i+1;   
                }
            }
            System.out.println("Your next assignment is due " + assignmentList.get(indexOfAssignmentWithLowestDate).getDueDate() + " and is called " + assignmentList.get(indexOfAssignmentWithLowestDate).getName());
        }
    }
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
                //This set is used to make sure the user enters an integer. If i cal for an integer and they input anything else, the program keeps breaking
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
}