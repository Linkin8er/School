package Cards.Templates;

import java.util.ArrayList;

//Most trainers have their own specific implementation
public class PokemonTrainer extends PokemonCard implements Playable{

    public PokemonTrainer(){
        cardType = "Trainer";
    }
    protected String trainerType;
}
