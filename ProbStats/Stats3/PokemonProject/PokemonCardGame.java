public class PokemonCardGame {

    private PokemonPlayer Player1;
    private PokemonPlayer Player2;

    public PokemonCardGame(){

        Player1 = new PokemonPlayer();
        Player2 = new PokemonPlayer();       
    }

    public void play(){

        System.out.println("Game is started");
        startOfGame();
    }
    public void startOfGame(){

        System.out.println("Player 1 Draw");
        System.out.println(Player1.openingHand(0));

    }
}
