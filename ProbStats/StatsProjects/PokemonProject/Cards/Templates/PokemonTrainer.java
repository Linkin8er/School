package Cards.Templates;

import java.util.ArrayList;

public class PokemonTrainer extends PokemonCard implements TrainerAction{

    public PokemonTrainer(){
        cardType = "Trainer";
    }
    protected String trainerType;
    public void playAction(PokemonPlayer targetPlayer){

    }
}
