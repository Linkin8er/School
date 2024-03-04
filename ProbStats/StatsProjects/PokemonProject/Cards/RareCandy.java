package Cards;

import Cards.Templates.PokemonPlayer;
import Cards.Templates.PokemonTrainer;

public class RareCandy extends PokemonTrainer{

    public RareCandy(){
        cardType = "Trainer";
        trainerType = "Item";
        cardName = "Rare Candy";
        cardDescription = "Evolve a basic pokemon to stage 2 skipping stage 1 using a stage 2 in your hand.";
    }

    public void playAction(PokemonPlayer targetPlayer, PokemonPlayer opponent){
        System.out.println("Totally played me");
    }
}
