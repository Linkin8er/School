package Cards.Templates;

//This is used by pokemon creatures
public interface Attackable {
    void attackOne(PokemonPlayer user, PokemonPlayer opponent);
    void attackTwo(PokemonPlayer user, PokemonPlayer opponent);
}
