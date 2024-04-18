import Cards.Templates.PokemonPlayer;

public class MonteCarloPokeTester {

    //This take the max number of pokemon to include in the test, and then tests for that number and each number less
    public MonteCarloPokeTester(int numPokemon){
        PokemonPlayer playerTest = new PokemonPlayer();
        
        for (int i = 1; i <= numPokemon; i++){
            
            playerTest.createDeck(playerTest.createDeckMonteTest(i));
            int totalRedraws = 0;
            int plays = 1000;

            //This actually goes through and counts the numebr of redraws needed to get a workable hand for 1000 workable hands
            for(int x = 0; x < plays; x++){
                totalRedraws += playerTest.openingHand(0);
                playerTest.returnHandToDeck();
                playerTest.shuffleDeck();
            }
            
            //This is jsut a bunch of prints that actually prints the info
            System.out.println("Number of pokemon in deck: " + numPokemon);
            int totalDraws = totalRedraws + plays;
            double chances = (100- ((Double.valueOf(totalRedraws)/Double.valueOf(totalDraws)) *100));
            System.out.println("Total failed hands: " + totalRedraws);
            System.out.println("Total hands drawn: "+ (totalDraws));
            System.out.printf("Chance of drawing a creature in opening hand: %.2f%% \n\n", chances);
            playerTest.removeDeck();
        }

    }
}
