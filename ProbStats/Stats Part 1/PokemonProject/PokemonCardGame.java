import java.util.Scanner;
import java.util.ArrayList;

import Cards.Templates.*;

public class PokemonCardGame {

    private PokemonPlayer player1 = new PokemonPlayer();
    private PokemonPlayer player2 = new PokemonPlayer();
    private String winner;
    private PokemonTrainer currentStadium;
    Scanner scan = new Scanner(System.in);

    public PokemonCardGame(){
              
    }

    /**
     * This actually runs the game, 
     * This is where the players select their names, otherwise it is 'placeholder'
     * This also handles running the game until winner
     */
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

        while(winner == null){
            takeTurn(player1, player2);

            if((winner == null))
            takeTurn(player2, player1);
        }
        
    }

    /**
     * This has the players select their decks for the game
     * Only the test deck is properly implemented
     */
    public void preGame(){

        int playerChoice;
        System.out.println(player1.getName() +", which deck would you like:\n1) Charizard Flames\n2) Snorlax Stall\n3) Mew Genesect Madness\n4) Arceus Godmode\n5) Test");
        playerChoice = choiceChecker(1,5);

        if (playerChoice == 1) player1.createCharizardDeck();
        if (playerChoice == 5) player1.createTestDeck(0);
        player1.createPrizePile();

        System.out.println("\n" + player2.getName() +", which deck would you like:\n1) Charizard Flames\n2) Snorlax Stall\n3) Mew Genesect Madness\n4) Arceus Godmode\n5) Test");
        playerChoice = choiceChecker(1,5);

        if (playerChoice == 1) player2.createCharizardDeck();
        if (playerChoice == 5) player2.createTestDeck(0);
        player2.createPrizePile();
    }

    /**
     * This handles the start of game sequence, drawing the opening hands, and setting up the field
     */
    public void startOfGame(){

        System.out.println(player1.getName() +", draw your opening hand!");
        int player1Fails = player1.openingHand(0);
        System.out.println(player2.getName() +", draw your opening hand");
        int player2Fails = player2.openingHand(0);

        if(player2Fails>player1Fails){

            player1.setField();
            System.out.println(player2.getName() +" had " + player2Fails +" more failed hands, so "+ player1.getName() +" will draw "+player2Fails+" more cards");
            player1.drawCard(player2Fails);
            player2.setField();
        }
        else if(player2Fails<player1Fails){

            player2.setField();
            System.out.println(player1.getName() +" had " + player1Fails +" failed hands, so "+ player2.getName() +" will draw "+player1Fails+" more cards");
            player2.drawCard(player1Fails);
            player1.setField();
        }
        else{
            player1.setField();
            player2.setField();
        }
    }

    /**
     * This is the bulk of the loop, having the player choose what they want to do on their turn
     * Attacking/passing ends the turn
     * You may only retreat once per turn
     * @param currentPlayer The player taking their turn and making the decisions
     * @param opponent The opposing player. Makes no decisions, but is needed for other methods
     */
    public void takeTurn(PokemonPlayer currentPlayer, PokemonPlayer opponent){

        currentPlayer.drawCard(1);
        System.out.println(currentPlayer.getName()+ " you have drawn " + currentPlayer.getHand().get(currentPlayer.getHand().size()-1).getCardName());
        System.out.println(currentPlayer.getName()+", What would you like to do?");
        boolean attacked = false;
        boolean retreated = false;
        while(!attacked){
            System.out.println("1) Play a card\n2) Attack (and end your turn)\n3) Retreat\n4) Use an ability\n5) Check something\n6) End Turn");
            int playerChoice = choiceChecker(1, 6);

            if (playerChoice == 1) playCard(currentPlayer, opponent);
            if (playerChoice == 2) {
                attacked = attack( currentPlayer, opponent);
                currentPlayer.getActivePokemon().updateDescription();
                opponent.getActivePokemon().updateDescription();
            }
            if (playerChoice == 3) {
                if(!retreated) retreated = retreat(currentPlayer);
                else System.out.println("You already retreated a Pokemon!");
            } 
            if (playerChoice == 4) System.out.println("No");
            if (playerChoice == 5) checkStatus(currentPlayer, opponent);
            if (playerChoice == 6) {attacked = true;}
        }
        endOfTurn(currentPlayer, opponent);
        if(winner == null) endOfTurn(opponent, currentPlayer);
    }

    /**
     * This is used for playing cards, calling each cards playCard method, 
     * There are multiple checks to make sure they want to play the card selected
     * @param currentPlayer The player playing the card
     * @param opponent The opponent they will possibly target
     */
    public void playCard(PokemonPlayer currentPlayer, PokemonPlayer opponent){

        boolean wantsToPlay = true;
        while(wantsToPlay){

            System.out.println(currentPlayer.getName() +", what card would you like to play?");
            currentPlayer.printHand(); 
            System.out.println(currentPlayer.getHand().size()+") Go back");
            int playerChoice = choiceChecker(0, currentPlayer.getHand().size());

            if (playerChoice == currentPlayer.getHand().size()){ wantsToPlay = false;}
            else{

                System.out.println(currentPlayer.getHand().get(playerChoice).getCardDescription());
                System.out.println("Are you sure you want to play this card?\n1) yes\n2) no");
                int playerChoice2 = choiceChecker(1, 2);
        
                if (playerChoice2 == 1) { 
                    currentPlayer.getHand().get(playerChoice).playCard(currentPlayer, opponent, playerChoice);
                    wantsToPlay = false;
                }
            }
        }

    }

    /**
     * This is used to attack with the active pokemon, making sure they can attack with said pokemon
     * @param currentPlayer The player attempting to attack
     * @param opponent The opposing player, used to get their active pokemon, and possibly bench
     * @return Returns true if the attack was successful, and false otherwise
     */
    public boolean attack(PokemonPlayer currentPlayer, PokemonPlayer opponent){
        System.out.println(currentPlayer.getName() +", what attack would you like to use?");
        System.out.println("1)"+currentPlayer.getActivePokemon().getAttackOneDiscription());
        int playerChoice;
        if(currentPlayer.getActivePokemon().hasSecondAttack()){
            System.out.println("2)"+currentPlayer.getActivePokemon().getAttackTwoDiscription());
            System.out.println("3) Cancel");
            playerChoice = choiceChecker(1,3);
        }
        else{
            System.out.println("2) Cancel");
            playerChoice = choiceChecker(1,2);
        }
        if(playerChoice == 1 ){ 

            currentPlayer.getActivePokemon().attackOne(currentPlayer, opponent);
            System.out.println(currentPlayer.getName()+ ", your opponent's active pokemon has "+ opponent.getActivePokemon().getHP() + " Remaining health!");
            return true;
        }
        else if (playerChoice == 2 && currentPlayer.getActivePokemon().hasSecondAttack()){
            currentPlayer.getActivePokemon().attackTwo(currentPlayer, opponent);
            System.out.println(currentPlayer.getName()+ ", your opponent's active pokemon has "+ opponent.getActivePokemon().getHP() + " Remaining health!");
            return true;
        }
        else  
        return false;
    }

    /**
     * This is used to retreat the active pokemon, replacing it with a benched pokemon
     * @param currentPlayer The player who is attempting to retreat
     * @return Returns true if the retreat was sucessful, or false otherwise
     */
    public boolean retreat(PokemonPlayer currentPlayer){

        if(currentPlayer.getActivePokemon().checkEnergies("Any", currentPlayer.getActivePokemon().getRetreatCost())){
            if(currentPlayer.getBench().isEmpty()){
                System.out.println("You dont have any benched Pokemon to replace your active Pokemon!");
                return false;
            }
            else{
                int playerChoice;
                System.out.println("Who would you like to replace your active Pokemon: " + currentPlayer.getActivePokemon().getCardName() + "?");
                currentPlayer.printBench();
                System.out.println(currentPlayer.getBench().size() + ") Cancel");
                playerChoice = choiceChecker(0, currentPlayer.getBench().size());
                if(playerChoice < currentPlayer.getBench().size()){

                    int needToDiscard = currentPlayer.getActivePokemon().getRetreatCost();
                    int discarded = 0;
                    int discardChoice;
                    boolean payedCost = false;
                    ArrayList<PokemonEnergy> toBeDiscarded = new ArrayList<PokemonEnergy>();
                    while(needToDiscard > discarded){
                        System.out.println("You need to discard " + needToDiscard + " energies from " + currentPlayer.getActivePokemon().getCardName() +". You have discarded " + discarded +" energies so far.");
                        System.out.println("Which energy would you like to discard?");
                        currentPlayer.getActivePokemon().printAttachedEnergies();
                        System.out.println(currentPlayer.getActivePokemon().getAttachedEnergies().size() +") Cancel");
                        discardChoice = choiceChecker(0, currentPlayer.getActivePokemon().getAttachedEnergies().size());
                        if(discardChoice < currentPlayer.getActivePokemon().getAttachedEnergies().size()){
                            toBeDiscarded.add(currentPlayer.getActivePokemon().getAttachedEnergies().remove(discardChoice));
                            discarded ++;
                        }
                        else{
                            for(int i = 0; i < toBeDiscarded.size(); i++){
                                currentPlayer.getActivePokemon().getAttachedEnergies().add(toBeDiscarded.remove(i));
                            }
                            return false;
                        }
                        if(needToDiscard == discarded) payedCost = true;
                    }
                    if(payedCost){
                        currentPlayer.getBench().add(currentPlayer.getActivePokemon());
                        currentPlayer.setActivePokemon(currentPlayer.getBench().get(playerChoice));
                        return true;
                    }
                }
                else return false;
            }
        }
        else{
            System.out.println("Your active Pokemon does not have enough attached energies to retreat!");
            return false;
        }
        //This line will never be reached, Java is jsut needy.
        return false;
    }

    /**
     * This is used to check various things:
     * The current player's field
     * The opponent's field
     * Or the current player's hand
     * @param currentPlayer The player using the check, used to get their board and hand
     * @param opponent The opponent, needed to get their board
     */
    public void checkStatus(PokemonPlayer currentPlayer, PokemonPlayer opponent){

        System.out.println("What would you like to check?");
        System.out.println("1) Your board\n2) Opponent board\n3) Cards in hand\n4) Cancel");
        int playerChoice = choiceChecker(1,4);
        if(playerChoice == 1) currentPlayer.printBoard();
        if(playerChoice == 2) opponent.printBoard();
        if(playerChoice == 3) currentPlayer.printHand();

    }

    /**
     * This is used to check the end of each turn
     * It checks the Current player and Oppoennts turn to find a winner, and possibly replace a pokemon if needed
     * Winner piority is given to the current player(Both could win at the same time technically)
     * If a pokemon faints, their controller must pick a new pokemon to replace them
     * If there is no pokemon to replace, then the other player wins. 
     * A player also wins when they have no more prize cards
     * @param currentPlayer The player with priority to win
     * @param opponent The opponet, whos pokemon may ahve fainted
     */
    public void endOfTurn(PokemonPlayer currentPlayer, PokemonPlayer opponent){
        if(currentPlayer.getPrizePile().size() <= 0){
            System.out.println(currentPlayer.getName()+ " has won!");
            winner = currentPlayer.getName();
        }
        
        if(opponent.getActivePokemon().getHP() <= 0){
            System.out.println(opponent.getName()+"'s " + opponent.getActivePokemon().getCardName() + " has fainted!");
            if(opponent.getBench().isEmpty()){
                System.out.println(opponent.getName()+" has no Pokemon remaining! " +currentPlayer.getName()+ " has won!");
                winner = currentPlayer.getName();
            }
            else if(currentPlayer.getPrizePile().size() <= 1){
                System.out.println(currentPlayer.getName()+ " has won!");
                winner = currentPlayer.getName();
            }
            else{
                System.out.println(opponent.getName()+" who would you like use to replace your active Pokemon?");
                opponent.printBench();
                int playerChoice = choiceChecker(0, opponent.getBench().size());
                opponent.setActivePokemon(opponent.getBench().remove(playerChoice));
                System.out.println(currentPlayer.getName()+ " has taken a prize card!");
                currentPlayer.drawCard(currentPlayer.getPrizePile().remove(0));
            }
        }
        if(currentPlayer.getPrizePile().size() <= 1){
            System.out.println(currentPlayer.getName()+ " has won!");
            winner = currentPlayer.getName();
        }
    }

    /**
     * This is used in all of my programs to make sure a user enters a legal number. 
     * if both values are -100, then it is assumed there is no bounds
     * @param nLowerBounds The upper bounds[inclusive] allowed
     * @param nUpperBounds The lower bounds[inclusive] allowed
     * @return
     */
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
