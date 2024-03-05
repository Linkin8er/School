import Cards.Templates.PokemonPlayer;

public class MonteCarloBrickDeck {

    public MonteCarloBrickDeck(int numcandy){
        PokemonPlayer playerTest = new PokemonPlayer();

        //This is going to loop through for whatever the input is down to 1, testing an opening prize pile 10000 times. 
        //Exe. if we input 10, we will ge the result for 10,9,8...2,1 candies. 
        for (int i = 1; i <= numcandy; i++){
            
            playerTest.createTestDeck(i);
            int totalBricks = 0;
            int plays = 10000;

            for(int x = 0; x < plays; x++){

                playerTest.createPrizePile();

                boolean hasCandy = false;

                //This checks the deck for copies of rare candies
                for(int y = 0; y < playerTest.getDeck().size(); y++){
                    if(playerTest.getDeck().get(y).getCardName().equals("Rare Candy")) hasCandy = true;
                }

                if(!hasCandy) totalBricks++;

                //Then we return prize pile to deck and shuffle for the next itteration
                playerTest.returnPrizePile();
                playerTest.shuffleDeck(); 
                
            }
            
            //Lastly we print the probability of bricking for each card count.
            System.out.println("Number of Rare Candy in deck: " + i);
            System.out.println("Total plays: "+ plays);
            System.out.println("Total bricks: " + totalBricks);
            System.out.println("Probaility of bricking: " + Double.valueOf(totalBricks)/Double.valueOf(plays) + "\n");
            playerTest.removeDeck();
        }
    }
}
