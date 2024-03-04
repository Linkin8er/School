import java.util.Scanner;

import Cards.Templates.*;

public class PokemonCardGame {

    private PokemonPlayer player1 = new PokemonPlayer();
    private PokemonPlayer player2 = new PokemonPlayer();
    private PokemonTrainer currentStadium;
    Scanner scan = new Scanner(System.in);

    public PokemonCardGame(){
              
    }

    public void runGame(){

        System.out.println("Player1, what is your name?");
        String Player1Name = scan.nextLine();
        player1.setName(Player1Name);

        System.out.println("Player2, what is your name?");
        String Player2Name = scan.nextLine();
        player2.setName(Player2Name); 

        System.out.println("Game is started");
        preGame();
        startOfGame();

        while(player1.getPrizePile().size() > 0 && player2.getPrizePile().size() > 0){
            gameRounds();
        }
        
    }

    public void gameRounds(){
        takeTurn(player1, player2);
        takeTurn(player2, player1);
        endOfRound();
    }

    public void preGame(){

        int playerChoice;
        System.out.println(player1.getName() +", which deck would you like:\n1) Charizard Flames\n2) Snorlax Stall\n3) Mew Genesect Madness\n4) Arceus Godmode\n5) Test");
        playerChoice = choiceChecker(1,5);

        if (playerChoice == 1) player1.createCharizardDeck();
        if (playerChoice == 5) player1.createTestDeck();
        player1.createPrizePile();

        System.out.println("\n" + player2.getName() +", which deck would you like:\n1) Charizard Flames\n2) Snorlax Stall\n3) Mew Genesect Madness\n4) Arceus Godmode\n5) Test");
        playerChoice = choiceChecker(1,5);

        if (playerChoice == 1) player2.createCharizardDeck();
        if (playerChoice == 5) player2.createTestDeck();
        player2.createPrizePile();
    }

    public void startOfGame(){

        System.out.println(player1.getName() +", draw your opening hand!");
        int player1Fails = player1.openingHand(0);
        System.out.println(player2.getName() +", draw your opening hand");
        int player2Fails = player2.openingHand(0);
        System.out.println(player1.getName() +" had " + player1Fails +" failed hands, so "+ player2.getName() +" will draw "+player1Fails+" more cards");
        player2.drawCard(player1Fails);
        System.out.println(player2.getName() +" had " + player2Fails +" failed hands, so "+ player1.getName() +" will draw "+player2Fails+" more cards");
        player1.drawCard(player1Fails);

    }

    public void takeTurn(PokemonPlayer currentPlayer, PokemonPlayer opponent){

        currentPlayer.drawCard(1);
        System.out.println(currentPlayer.getName()+ " you have drawn " + currentPlayer.getHand().get(currentPlayer.getHand().size()-1).getCardName());
        System.out.println(currentPlayer.getName()+", What would you like to do?");
        boolean attacked = false;
        while(!attacked){
            System.out.println("1) Play a card\n2) Attack (and end your turn)\n3) Retreat\n4) Use an ability\n5) Check something");
            int playerChoice = choiceChecker(1, 5);

            if (playerChoice == 1) playCard(currentPlayer, opponent);
            if (playerChoice == 2) {
                attacked = true;
                
            };
            if (playerChoice == 3) retreat();
            if (playerChoice == 4) playCard(currentPlayer, opponent);
            if (playerChoice == 5) checkStatus();
        }

    }

    public void playCard(PokemonPlayer currentPlayer, PokemonPlayer opponent){

        System.out.println(currentPlayer.getName() +", what card would you like to play?");
        currentPlayer.printHand();
        int playerChoice = choiceChecker(0, currentPlayer.getHand().size()-1);
        System.out.println(currentPlayer.getHand().get(playerChoice).getCardDescription());
        System.out.println("Are you sure you want to play this card?\n1) yes\n2) no");
        int playerChoice2 = choiceChecker(1, 2);
        if (playerChoice2 == 1) { currentPlayer.getHand().get(playerChoice).playCard(currentPlayer, opponent);}

    }

    public void retreat(){

    }

    public void checkStatus(){

    }

    public void endOfRound(){

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
