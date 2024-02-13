import java.util.Scanner;
import Cards.CardTemplates.*;

public class PokemonCardGame {

    private PokemonPlayer player1;
    private PokemonPlayer player2;
    private PokemonTrainer currentStadium;
    Scanner scan = new Scanner(System.in);

    public PokemonCardGame(){
        
        System.out.println("Player1, what is your name?");
        String Player1Name = scan.nextLine();
        player1 = new PokemonPlayer(Player1Name);

        System.out.println("Player2, what is your name?");
        String Player2Name = scan.nextLine();
        player2 = new PokemonPlayer(Player2Name);       
    }

    public void runGame(){

        System.out.println("Game is started");
        preGame();
        startOfGame();

        while(player1.getPrizeSize() > 0 && player2.getPrizeSize() > 0){
            gameRounds();
        }
        
    }

    public void preGame(){

        int playerChoice;
        System.out.println(player1.getName() +", which deck would you like:\n1) Charizard Flames\n2) Snorlax Stall\n3) Mew Genesect Madness\n4) Arceus Godmode");
        playerChoice = choiceChecker(1,4);

        if (playerChoice == 1) player1.createCharizardDeck();
        player1.createPrizePile();

        System.out.println("\n" + player2.getName() +", which deck would you like:\n1) Charizard Flames\n2) Snorlax Stall\n3) Mew Genesect Madness\n4) Arceus Godmode");
        playerChoice = choiceChecker(1,4);

        if (playerChoice == 1) player2.createCharizardDeck();
        player2.createPrizePile();
    }

    public void startOfGame(){

        System.out.println(player1.getName() +",draw your opening hand!");
        int player1Fails = player1.openingHand(0);
        System.out.println(player2.getName() +",draw your opening hand");
        int player2Fails = player2.openingHand(0);
        System.out.println(player1.getName() +" had " + player1Fails +" mulligans, so "+ player2.getName() +" will draw "+player1Fails+" more cards");
        player2.drawCard(player1Fails);
        System.out.println(player2.getName() +" had " + player2Fails +" failed hands, so "+ player1.getName() +" will draw "+player2Fails+" more cards");
        player1.drawCard(player1Fails);

    }

    public void gameRounds(){
        takeTurn(player1);
        takeTurn(player2);
        endOfRound();
    }

    public void takeTurn(PokemonPlayer currentPlayer){

        currentPlayer.drawCard(1);
        System.out.println(currentPlayer.getName()+", What would you like to do?");
        System.out.println("1) Play a card\n2) Attack (and end your turn)\n3) Retreat\n4) Use an ability\n5) Check something");
        int playerChoice = choiceChecker(1, 5);

        if (playerChoice == 1) playCard(currentPlayer);
        if (playerChoice == 2) playCard(currentPlayer);
        if (playerChoice == 3) retreat();
        if (playerChoice == 4) playCard(currentPlayer);
        if (playerChoice == 5) checkStatus();

    }

    public void playCard(PokemonPlayer currentPlayer){

        System.out.println(currentPlayer.getName() +", what card would you like to play?");
        currentPlayer.printHand();
        int playerChoice = choiceChecker(1, currentPlayer.getHandSize()-1);
        System.out.println(currentPlayer.getDescription(playerChoice));
        System.out.println("Are you sure you want to play this card?\n1) yes\n2) no");
        int playerChoice2 = choiceChecker(1, 2);
        if (playerChoice2 == 1) { 

            currentPlayer.playCard(playerChoice);
            System.out.println("Your active pokemon is now:\n" + currentPlayer.getActivePokemon().getCardDescription());
        }

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
