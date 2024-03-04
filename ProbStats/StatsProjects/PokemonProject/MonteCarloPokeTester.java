public class MonteCarloPokeTester {

    public MonteCarloPokeTester(int numPokemon){
        PokemonPlayer playerTest = new PokemonPlayer();

        for (int i = 1; i <= numPokemon; i++){
            
            playerTest.createDeck(playerTest.createDeckMonteTest(i));
            int totalRedraws = 0;
            int plays = 1000;

            for(int x = 0; x < plays; x++){
                totalRedraws += playerTest.openingHand(0);
                playerTest.returnHandToDeck();
                playerTest.shuffleDeck();
            }
            
            System.out.println("Number of pokemon in deck: " + playerTest.getPokemonCount());
            int totalDraws = totalRedraws + plays;
            double chances = (100- ((Double.valueOf(totalRedraws)/Double.valueOf(totalDraws)) *100));
            System.out.println("Total failed hands: " + totalRedraws);
            System.out.println("Total hands drawn: "+ (totalDraws));
            System.out.printf("Chance of drawing a creature in opening hand: %.2f%% \n\n", chances);
            playerTest.removeDeck();
        }

    }
}
