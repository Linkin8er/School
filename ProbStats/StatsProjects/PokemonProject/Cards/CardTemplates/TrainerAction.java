package Cards.CardTemplates;
import java.util.ArrayList;

public interface TrainerAction {

    void playAction(ArrayList<PokemonCard> targetDeck, PokemonCreature targetPokemon);
    
}
