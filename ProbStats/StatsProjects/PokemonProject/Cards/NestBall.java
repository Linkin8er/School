package Cards;

import java.util.ArrayList;

import Cards.Templates.PokemonCreature;
import Cards.Templates.PokemonPlayer;
import Cards.Templates.PokemonTrainer;

public class NestBall extends PokemonTrainer{
    public NestBall(){

        cardType = "Trainer";
        cardSubType = "Item";
        cardName = "Nest Ball";
        cardDescription = "Search your deck for a Basic Pokémon and put it onto your Bench. Then, shuffle your deck.";
    }
    
    //This goes through and gets the basic pokemon in the deck. Then, it prompts the player to select one of the pokemon, which is then added to the bench
    public void playCard(PokemonPlayer targetPlayer, PokemonPlayer opponent, int locationInHand){

        ArrayList<Integer> cardLocations = new ArrayList();
        for(int i = 0; i < targetPlayer.getDeck().size(); i++){
            if(targetPlayer.getDeck().get(i).getCardSubType().equals("Basic Pokemon")){ cardLocations.add(i);}
        }
        System.out.println("Would you like to grab:");
        for(int x = 0; x < cardLocations.size(); x++){
            System.out.println(x + ") " + targetPlayer.getDeck().get(cardLocations.get(x)).getCardName());
        }
        
        int playerChoice = choiceChecker(0, cardLocations.size());
        int deckLocation = cardLocations.get(playerChoice);

        targetPlayer.getBench().add((PokemonCreature)targetPlayer.getDeck().remove(deckLocation));
        targetPlayer.shuffleDeck();
        targetPlayer.discardCard(targetPlayer.getHand().remove(locationInHand));
    }
}
