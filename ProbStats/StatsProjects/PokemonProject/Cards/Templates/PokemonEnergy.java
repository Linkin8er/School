package Cards.Templates;
public class PokemonEnergy extends PokemonCard implements Playable{

    public PokemonEnergy(){
        cardType = "Energy";
    }

    public void playCard(PokemonPlayer userPlayer, PokemonPlayer opponentPlayer, int locationInHand){

        System.out.println("Which pokemon would you like to attach to?:\n");
        System.out.println("Bench:");
        userPlayer.printBench();
        System.out.println("\nActive:");
        System.out.println((userPlayer.getBench().size()) + ") "+ userPlayer.getActivePokemon().getCardName());
        System.out.println((userPlayer.getBench().size()+1) + ") Cancel");

        int playerChoice = choiceChecker(0, userPlayer.getBench().size()+1);

        if(playerChoice != userPlayer.getBench().size()+1){
            if(playerChoice != userPlayer.getBench().size()){userPlayer.getBench().get(playerChoice).attachEnergy((PokemonEnergy)userPlayer.getHand().remove(locationInHand));}
            else{userPlayer.getActivePokemon().attachEnergy((PokemonEnergy)userPlayer.getHand().remove(locationInHand));}
        }
    }
}
