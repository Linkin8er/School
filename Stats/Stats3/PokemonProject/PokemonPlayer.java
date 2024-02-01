import java.util.ArrayList;
import java.util.Collections;

public class PokemonPlayer {

    private ArrayList<PokemonCard> deck;
    private ArrayList<PokemonCard> hand;
    private ArrayList<PokemonCard> discard;
    private int pokemonin;

    public PokemonPlayer(int pokemoninclusion){

        pokemonin = pokemoninclusion;
        deck = createDeck();
        shuffleDeck();
        hand = new ArrayList<PokemonCard>();
    }

    private ArrayList<PokemonCard> createDeck(){

        ArrayList<PokemonCard> deckList = new ArrayList<PokemonCard>();
        PokemonCreature pikachu = new PokemonCreature();
        PokemonEnergy fire = new PokemonEnergy();
        PokemonSupporter bill = new PokemonSupporter();

        for(int i =0; i < (60-pokemonin); i++) {deckList.add(fire);}
        for(int i =0; i < pokemonin; i++) {deckList.add(pikachu);}
        for(int i =0; i < 0; i++) {deckList.add(bill);}

        Collections.shuffle(deckList);
        //System.out.println("Deck is made");
        return deckList;
    }
    public int openingHand(int redraws){

        //System.out.println("opening hand is drawn");
        shuffleDeck();
        drawCard(7);

        boolean hasACreature = false;
        for(int x = 0; x < hand.size()-1; x++){
            if (hand.get(x).getCardType().equals("Creature")) hasACreature = true;
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

    public void shuffleDeck(){ Collections.shuffle(deck);}

    public void returnHandToDeck(){ for(int i =0; i < hand.size(); i++) {deck.add(hand.remove(hand.size()-1));}}
    
    public int getPokemon(){ return pokemonin; }
    public void removeDeck(){ if(!(deck.isEmpty())) deck.clear(); }
    public void drawCard(int numToDraw){ for(int i =0; i <= numToDraw; i++) {hand.add(deck.remove(deck.size()-1));}}
    

}
