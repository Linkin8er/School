import java.util.Scanner;
public class PokemonCardGame {

    private PokemonPlayer Player1;
    private PokemonPlayer Player2;
    Scanner scan = new Scanner(System.in);

    public PokemonCardGame(){
        
        Player1 = new PokemonPlayer();
        Player2 = new PokemonPlayer();       
    }

    public void play(){

        System.out.println("Game is started");
        preGame();
        startOfGame();
    }
    public void startOfGame(){

        System.out.println("Player 1 Draw");
        System.out.println(Player1.openingHand(0));

    }

    public void preGame(){

        System.out.println("Player1, which deck would you like:\n1) Charizard Flames\n2) Snorlax Stall\n3) Mew Genesect Madness\n4) Arceus Godmode");
        int playerChoice = choiceChecker(1,4);

        if (playerChoice == 1) Player1.createCharizardDeck();
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
