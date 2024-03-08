package Cards.Templates;
import java.util.ArrayList;

//All cards implement this in one way or annother
public interface Playable {

    void playCard(PokemonPlayer userPlayer, PokemonPlayer opponentPlayer, int LocationInHand);
    
}
