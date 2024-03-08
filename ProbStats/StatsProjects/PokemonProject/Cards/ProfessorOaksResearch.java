package Cards;

import Cards.Templates.PokemonPlayer;
import Cards.Templates.PokemonTrainer;

public class ProfessorOaksResearch extends PokemonTrainer{
    public ProfessorOaksResearch(){
        cardType = "Trainer";
        trainerType = "Supporter";
        cardName = "Proffesor Oak's Research";
        cardDescription = "Discard your hand and draw 7 cards.";
    }

    //This trainer is very simple, it simply discards the player hand before drawing 7 new cards
    public void playCard(PokemonPlayer targetPlayer, PokemonPlayer opponent, int locationInHand){
        targetPlayer.discardCard(targetPlayer.getHand().remove(locationInHand));
        for(int i = 0; i <targetPlayer.getHand().size(); i++){
            targetPlayer.getDiscard().add(targetPlayer.getHand().remove(i));
        }
        targetPlayer.drawCard(7);
    }
}
