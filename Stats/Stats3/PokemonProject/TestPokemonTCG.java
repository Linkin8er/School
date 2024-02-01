public class TestPokemonTCG {
    public static void main(String[] args){
        PokemonCardGame testGame = new PokemonCardGame();
        
        for (int i = 1; i < 10; i++){

            PokemonPlayer playerTest = new PokemonPlayer(i);
        
            int totalRedraws = 0;
            int plays = 100000;

            for(int x = 0; x < plays; x++){
                totalRedraws += playerTest.openingHand(0);
                playerTest.returnHandToDeck();
                playerTest.shuffleDeck();
            }

            System.out.println(playerTest.getPokemon());
            int totalDraws = totalRedraws + plays;
            double chances = (100- ((Double.valueOf(totalRedraws)/Double.valueOf(totalDraws)) *100));
            System.out.println("Total failed hands: " + totalRedraws);
            System.out.println("Total hands drawn: "+ (totalDraws));
            System.out.println("Chance of drawing a creature in opening hand: " + chances + "%");
        }
        
    }
}
