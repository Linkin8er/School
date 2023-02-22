//////////////////////////////////////////////////////////////////////////////
//                                                                          //
//                          Author: Nicholas Stephani                       //
//                          Project: Project 3                              //
//                          Date: 4/7/2020                                  //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.util.ArrayList;

/*  This program goes through and makes an array list of integers that the user can mess with in different ways.
    The user can add integers, remove them, find integers, get the values at an index, print the list, and get the size
    Any time there is a println that has a \n in it, that is intentional, I wanted more spacing. */

public class Project3
{
    Scanner scan = new Scanner(System.in);
    private ArrayList<String> list;
    private boolean bKeepGoing;
    private int nUserChoice;
    private int nUserNumber;

    public static void main(String[] args)
    {
        Project3 list = new Project3();

        System.out.println("Hello and welcome to the List to end all lists.");
        System.out.println("This will be a list of integers, as they are cool. It will also have some already in it.\n");

        //This block is what keep running until the user enters the number 7, meaning they want to quit.
        while(list.getRunning())
        {
            int nChoice;
            System.out.println("What would you like to do with the list?");
            System.out.println("1.Add\n2.Delete\n3.Search\n4.Retrieve\n5.Get Length\n6.Display List\n7.Exit");
            
            //The choice checker referenced here and later checks to make sure that the user inputs a valid integer between the first and second number.
            nChoice = list.choiceChecker(1, 7);

            //This switch block simply runs whatever the user selected
            switch(nChoice)
            {
                case 1:
                    //This allows the user to add a number to the list
                    list.addElement();
                    break;

                case 2:
                    //This allows the user to remove a number from the list
                    list.deleteElement();
                    break;

                case 3:
                    //This allows the user to search for a particular number in the list
                    list.searchForElement();
                    break;

                case 4:
                    //This allows the user to check what number is a a certain index in the list
                    list.getElement();
                    break;

                case 5:
                    //This will print out the length of the list
                    list.getLength();
                    break;

                case 6:
                    //This will go through and print out the whole list and their indexes
                    list.printList();
                    break;

                case 7:
                    //This will end the program
                    list.end();
                    break;

                //This should never be reached, if it has, there is an issue
                default:
                    System.out.println("You shouldn't be here.");

            }
        }
    }

    //This constructor just initializes the variables and adds some values to the list.
    public Project3()
    {
        list = new ArrayList<String>();
        bKeepGoing = true;
        nUserChoice = 0;
        nUserNumber = 0;

        list.add("1");
        list.add("3");
        list.add("243");
        list.add("244");
        list.add("500");
        list.add("873");
    }

    //This small method is just used to check if the user has ended the program yet
    public boolean getRunning()
    {
        return bKeepGoing;
    }

    //This block goes through and allows the user to enter a number into the list. 
    //They have the option of adding their number to a particular location in the list, or just the end 
    public void addElement()
    {

        System.out.println("Please enter the number you want added to the list");

        //Same as before, this method just checks to make sure the user inputs a proper number, but this one doesn't have bounds
        nUserNumber = choiceChecker(-100,-100);

        System.out.println("Would you like to add your number to a particular location? Please enter 1 for yes, 2 for no, and 3 if you don't want to add it.\nIf no it will be added to the end");

        nUserChoice = choiceChecker(1,2);

        //This switch statement goes through and lets the user pick the entry location on 1, adds their number to the end on 2, or lets them exit this function
        switch(nUserChoice)
        {
            //This case is for when the user wants to pick the location. It does check to make sure the location is valid before entry.
            case 1:

                int nLocation;

                System.out.println("What position would you like to put the number in?");

                nLocation = choiceChecker(0,list.size());
                list.add(nLocation, "" + nUserNumber);
                System.out.println("Your number " + nUserNumber + " has been added.\n");
                break;
            
            //This case is when the user doesn't care where the number goes, so it just adds it to the end. 
            case 2:
                list.add("" + nUserNumber);
                System.out.println("Your number " + nUserNumber + " has been added.\n");
                break;
            
            //This case is for when the user input a wrong number and doesn't want to continue
            case 3:
                System.out.println("Okay\n");
                break;

            //This should never be reached, if it has, there is an issue
            default:
                System.out.println("You shouldn't be here.");
        }
    }

    //This is the method that allows the user to delete either a particular number, or an index from the list
    public void deleteElement()
    {
        System.out.println("Would you like to remove a certain number, or would you like to remove an index?\nPlease enter 1 for a number, 2 for an index, or 3 for none.");
        nUserChoice = choiceChecker(1,3);

        //This switch is for wether the user is removing an index or a number. 1 is for the number, 2 is for the index, and 3 is for neither.
        switch(nUserChoice)
        {
            //This is the case for removing a number. First, it gets the number the user wants and checks if it is in the list.
            //If so, it gets cut. If not, it tells the user there is no such number.
            case 1:
                boolean bFound = false;
                int nIndex = 0;
                System.out.println("What number would you like to remove?");
                nUserNumber = choiceChecker(-100,-100);
                while(!bFound)
                {
                    if(Integer.parseInt(list.get(nIndex)) == nUserNumber)
                    {
                        list.remove(nIndex);
                        System.out.println("" + nUserNumber + " was removed.\n");
                        bFound = true;
                    }
                    else
                    {
                        nIndex++;
                        if(nIndex >= list.size())
                        {
                            System.out.println("That number doesn't seem to exist in the list.\n");
                            bFound = true;
                        }
                    }
                }
                break;

            //This case is a bit simpler. All it does is get an index from the user that they want removed, and it removes it.
            case 2:
                System.out.println("Please enter the index of the number you want removed.");
                nUserNumber = choiceChecker(0, list.size()-1);
                list.remove(nUserNumber);
                System.out.println("" + nUserNumber + " was removed.\n");
                break;

            //This case is for when the user doesn't want to remove any values
            case 3:
                System.out.println("Okay.\n");
                break;

            //This should never be reached, if it has, there is an issue
            default:
                System.out.println("You shouldn't be here.");
        }
    }

    //This method is used for seeing if a number exists in the list, and if so, where.
    public void searchForElement()
    {
        boolean bFound = false;
        int nIndex = 0;
        System.out.println("What number would you like to find?");
        nUserNumber = choiceChecker(-100,-100);

        //This loop will continue until the value is found (in which case it prints it), or it reaches the end empty handed.
        while(!bFound)
        {
            if(Integer.parseInt(list.get(nIndex)) == nUserNumber)
            {
                System.out.println("" + nUserNumber + " was found at index " + nIndex + ".\n");
                bFound = true;
            }
            else
            {
                nIndex++;
                if(nIndex >= list.size())
                {
                    System.out.println("The number " + nUserNumber + " doesn't seem to exist in the list.\n");
                    bFound = true;
                }
            }
        }
    }

    //This method simply returns teh element at a specified index
    public void getElement()
    {
        System.out.println("What index would you like to check?");
        nUserChoice = choiceChecker(0,list.size()-1);
        System.out.println("The number at index " + nUserChoice + " is " + list.get(nUserChoice) + ".\n");
    }

    //This method just prints the number of elements in the list.
    public void getLength()
    {
        System.out.println("The length of the list is " + list.size() + ".\n");

    }

    //This method is used to print out the list of elements and their respective indexes
    public void printList()
    {
        for(int i = 0; i < list.size(); i++)
        {
            if(i < list.size() - 1)
            {
                System.out.print("" + i + ") " + list.get(i)+ ", ");
            }
            else System.out.print("" + i + ") " + list.get(i) + ".\n");
        }
        System.out.println();

    }

    //When this method is called, it makes sure the user actually wanted to end the program, and if so, ends it
    public void end()
    {
        System.out.println("Are you sure you want to end the program?\n1 for yes, 2 for no");
        nUserChoice = choiceChecker(1,2);
        if(nUserChoice == 1) 
        {
            bKeepGoing = false;
            System.out.println("Thank you for using my program! May the day find you well!\n");
        }
        else System.out.println("Okay.\n");
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
            if(nLowerBounds == -100 && nUpperBounds == -100) noBounds = true;

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