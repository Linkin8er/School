package Cards.Templates;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Cards.*;

public class PokemonPlayer {

    Scanner scan = new Scanner(System.in);
    private ArrayList<PokemonCard> deck;
    private ArrayList<PokemonCard> hand;
    private ArrayList<PokemonCard> discard;
    private ArrayList<PokemonCard> prizeCards;
    private PokemonCreature activeSpot;
    private ArrayList<PokemonCreature> bench;
    private String name;

    public PokemonPlayer(){

        name = "PlaceHolder";
        deck = new ArrayList<PokemonCard>();
        hand = new ArrayList<PokemonCard>();
        discard = new ArrayList<PokemonCard>();
        prizeCards = new ArrayList<PokemonCard>();
        bench = new ArrayList<PokemonCreature>();
    }

    public ArrayList<PokemonCard> createDeckMonteTest(int pokemonin){

        ArrayList<PokemonCard> deckList = new ArrayList<PokemonCard>();
        CharmanderPAF mander = new CharmanderPAF();
        NestBall notMander = new NestBall();

        for(int i =0; i < (60-pokemonin); i++) {deckList.add(notMander);}
        for(int i =0; i < pokemonin; i++) {deckList.add(mander);}

        Collections.shuffle(deckList);
        
        return deckList;
    }

    public void createCharizardDeck(){
        CharmanderPAF mander1 = new CharmanderPAF();
        CharmanderOBF mander2 = new CharmanderOBF();
        for(int i =0; i < 30; i++) {deck.add(mander1);}
        for(int i =0; i < 30; i++) {deck.add(mander2);}
    }
    public void createTestDeck(int numCandies){

        CharmanderPAF mander1 = new CharmanderPAF();
        CharmanderOBF mander2 = new CharmanderOBF();
        NestBall pokeBall = new NestBall();
        ProfessorOaksResearch research = new ProfessorOaksResearch();
        RareCandy candy = new RareCandy();
        FireEnergy energies = new FireEnergy();

        for(int i =0; i < 10; i++) {deck.add(mander1);}
        for(int i =0; i < 10; i++) {deck.add(mander2);}
        for(int i =0; i < 10; i++) {deck.add(pokeBall);}
        for(int i =0; i < 10; i++) {deck.add(research);}
        for(int i =0; i < numCandies; i++) {deck.add(candy);}
        for(int i =0; i < 20-numCandies; i++) {deck.add(energies);}
    }
    
    public int openingHand(int redraws){

        //System.out.println("opening hand is drawn");
        shuffleDeck();
        drawCard(7);

        boolean hasACreature = false;
        for(int x = 0; x < hand.size()-1; x++){
            if (hand.get(x).getCardSubType().equals("Basic Pokemon")) hasACreature = true;
        }

        //if(hasACreature) System.out.println("I got a useable hand!");
        if(!hasACreature){
            //System.out.println("I Suck at deckbuilding and dont have any creatures so i gotta draw again");
            returnHandToDeck();
            shuffleDeck();
            redraws = openingHand(redraws + 1);
        }
        return redraws;
    }
    
    public int getPokemonCount(){ 
        int pokemonCards = 0;
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getCardType().equals("Pokemon") || deck.get(i).getCardType().equals("Basic Pokemon")){
                pokemonCards++;
            }
        }
        return pokemonCards;
    }
    
    public void printHand(){
        for(int i = 0; i < hand.size(); i++){
            System.out.println((i) + ") "+ hand.get(i).getCardName());
            System.out.println("    " + hand.get(i).getCardDescription());
        }
    }

    public void printBench(){
        for(int i = 0; i < bench.size(); i++){
            System.out.println((i) + ") "+ bench.get(i).getCardName());
            System.out.println("    " + bench.get(i).getCardDescription());
        }
    }

    public void printBoard(){
        System.out.println(name + "'s Active Pokemon:");
        System.out.println(activeSpot.getCardName());
        System.out.println("    "+ activeSpot.getCardDescription());

        System.out.println(name + "'s Benched Pokemon:");
        if(!bench.isEmpty()){
            for(int i = 0; i < bench.size(); i++){
                System.out.println((i) + ") "+ bench.get(i).getCardName());
                System.out.println("    " + bench.get(i).getCardDescription());
            }
        }
        else System.out.println("No benched pokemon!");
    }

    public void setField(){

        System.out.println(name +"Time to set up!");
        boolean fieldSet = false;
        boolean activeSlotFilled = false;

        while(!fieldSet){

            ArrayList<Integer> cardLocation = new ArrayList();

            for(int i = 0; i < hand.size(); i++){
                if(hand.get(i).getCardSubType().equals("Basic Pokemon")){
                    cardLocation.add(i);
                }
            }

            int placeInHand;
            if(!activeSlotFilled){
                System.out.println("First, which pokemon would you like in your active slot?");
                for(int x = 0; x < cardLocation.size(); x++){
                    placeInHand = cardLocation.get(x);
                    System.out.println((x) + ") "+ hand.get(placeInHand).getCardName());
                }
                int playerChoice = choiceChecker(0, cardLocation.size()-1);
                placeInHand = cardLocation.get(playerChoice);
                activeSpot = (PokemonCreature)hand.remove(placeInHand);
                activeSlotFilled = true;
            } 
            else if (!cardLocation.isEmpty()){
                System.out.println("What pokemon would you like on your bench?");
                for(int x = 0; x < cardLocation.size(); x++){
                    placeInHand = cardLocation.get(x);
                    System.out.println((x) + ") "+ hand.get(placeInHand).getCardName());
                }
                System.out.println(cardLocation.size() + ") End Setup");
                int playerChoice = choiceChecker(0, cardLocation.size());
                if(playerChoice < cardLocation.size()){
                    placeInHand = cardLocation.get(playerChoice);
                    bench.add((PokemonCreature)hand.remove(placeInHand));
                } 
                else{
                    fieldSet = true;
                }
            } 
            else {
                System.out.println("You have no more pokemon to set!");
                fieldSet = true;
            }
        }
    }

    public void setName(String newName){name = newName;}
    public void createPrizePile(){for(int i =0; i < 6; i++) {prizeCards.add(deck.remove(deck.size()-1));}}
    public void removeDeck(){ if(!(deck.isEmpty())) deck.clear(); }
    
    public void drawCard(int numToDraw){ for(int i = 0; i < numToDraw; i++) {hand.add(deck.remove(deck.size()-1));}}
    public void createDeck(ArrayList<PokemonCard> deckList){deck = deckList;}
    public void shuffleDeck(){ Collections.shuffle(deck);}
    public void returnHandToDeck(){ for(int i =0; i < hand.size(); i++) {deck.add(hand.remove(hand.size()-1));}}
    public void returnPrizePile(){ for(int i =0; i < prizeCards.size(); i++) {deck.add(prizeCards.remove(i));}}
    public void discardCard(PokemonCard card){ discard.add(card);}

    public ArrayList<PokemonCard> getDeck(){ return deck;}
    public ArrayList<PokemonCard> getHand(){ return hand;}
    public ArrayList<PokemonCard> getDiscard(){ return discard;}
    public ArrayList<PokemonCard> getPrizePile(){ return prizeCards;}
    public PokemonCreature getActivePokemon(){ return activeSpot;}
    public ArrayList<PokemonCreature> getBench(){ return bench;}
    public String getName(){ return name;}
    public String getDescription(int card){ return hand.get(card).getCardDescription();}
    

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
