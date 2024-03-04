import java.util.ArrayList;
import java.util.Collections;

import Cards.*;
import Cards.CardTemplates.*;

public class PokemonPlayer {

    private ArrayList<PokemonCard> deck;
    private ArrayList<PokemonCard> hand;
    private ArrayList<PokemonCard> discard;
    private ArrayList<PokemonCard> prizeCards;
    private PokemonCard activeSpot;
    private ArrayList<PokemonCard> bench;
    private String name;

    public PokemonPlayer(){

        name = "PlaceHolder";
        deck = new ArrayList<PokemonCard>();
        hand = new ArrayList<PokemonCard>();
        prizeCards = new ArrayList<PokemonCard>();
    }

    public ArrayList<PokemonCard> createDeckMonteTest(int pokemonin){

        ArrayList<PokemonCard> deckList = new ArrayList<PokemonCard>();
        CharmanderPAF mander = new CharmanderPAF();
        PokemonEnergy fire = new PokemonEnergy();

        for(int i =0; i < (60-pokemonin); i++) {deckList.add(fire);}
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
    
    public int openingHand(int redraws){

        //System.out.println("opening hand is drawn");
        shuffleDeck();
        drawCard(7);

        boolean hasACreature = false;
        for(int x = 0; x < hand.size()-1; x++){
            if (hand.get(x).getCardType().equals("Basic Pokemon")) hasACreature = true;
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
        for(int i = 0; i < hand.size()-1; i++){
            System.out.println((i+1) + ") "+ hand.get(i).getCardName());
        }
    }

    public void playCard(int cardChoice){

        activeSpot = hand.remove(cardChoice-1);
    }

    public void setName(String newName){name = newName;}
    public void createPrizePile(){for(int i =0; i < 6; i++) {prizeCards.add(deck.remove(deck.size()-1));}}
    public void removeDeck(){ if(!(deck.isEmpty())) deck.clear(); }
    
    public void drawCard(int numToDraw){ for(int i = 0; i < numToDraw; i++) {hand.add(deck.remove(deck.size()-1));}}
    public void createDeck(ArrayList<PokemonCard> deckList){deck = deckList;}
    public void shuffleDeck(){ Collections.shuffle(deck);}
    public void returnHandToDeck(){ for(int i =0; i < hand.size(); i++) {deck.add(hand.remove(hand.size()-1));}}

    public int getHandSize(){ return hand.size();}
    public String getName(){ return name;}
    public int getPrizeSize(){return prizeCards.size();}
    public String getDescription(int card){ return hand.get(card).getCardDescription();}
    public PokemonCard getActivePokemon(){ return activeSpot;}
    

}
