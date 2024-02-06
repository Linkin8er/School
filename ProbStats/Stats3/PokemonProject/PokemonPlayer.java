import java.util.ArrayList;
import java.util.Collections;

import Cards.*;

public class PokemonPlayer {

    private ArrayList<PokemonCard> deck;
    private ArrayList<PokemonCard> hand;
    private ArrayList<PokemonCard> discard;

    public PokemonPlayer(){
        hand = new ArrayList<PokemonCard>();
    }
    public ArrayList<PokemonCard> createDeckMonteTest(int pokemonin){

        ArrayList<PokemonCard> deckList = new ArrayList<PokemonCard>();
        CharmanderPAF mander = new CharmanderPAF();
        PokemonEnergy fire = new PokemonEnergy();
        PokemonSupporter bill = new PokemonSupporter();

        for(int i =0; i < (60-pokemonin); i++) {deckList.add(fire);}
        for(int i =0; i < pokemonin; i++) {deckList.add(mander);}
        for(int i =0; i < 0; i++) {deckList.add(bill);}

        Collections.shuffle(deckList);
        
        return deckList;
    }

    public void createCharizardDeck(){
        CharmanderPAF mander1 = new CharmanderPAF();
        for(int i =0; i <= 60; i++) {deck.add(mander1);}
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
    public void removeDeck(){ if(!(deck.isEmpty())) deck.clear(); }
    public void drawCard(int numToDraw){ for(int i =0; i <= numToDraw; i++) {hand.add(deck.remove(deck.size()-1));}}
    public void createDeck(ArrayList<PokemonCard> deckList){deck = deckList;}
    public void shuffleDeck(){ Collections.shuffle(deck);}
    public void returnHandToDeck(){ for(int i =0; i < hand.size(); i++) {deck.add(hand.remove(hand.size()-1));}}
    

}
