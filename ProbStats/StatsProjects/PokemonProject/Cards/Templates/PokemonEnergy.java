package Cards.Templates;

//Most energies just are very similar, though eventually non-basic energies will be implemented
public class PokemonEnergy extends PokemonCard implements Playable{

    public PokemonEnergy(){
        cardType = "Energy";
    }

    //Played energies will be played either onto the actie pokemon, or a benched pokemon
    public void playCard(PokemonPlayer userPlayer, PokemonPlayer opponentPlayer, int locationInHand){

        System.out.println("Which pokemon would you like to attach to?:\n");
        System.out.println("Bench:");
        userPlayer.printBench();
        System.out.println("\nActive:");
        System.out.println((userPlayer.getBench().size()) + ") "+ userPlayer.getActivePokemon().getCardName());
        System.out.println((userPlayer.getBench().size()+1) + ") Cancel");

        int playerChoice = choiceChecker(0, userPlayer.getBench().size()+1);

        if(playerChoice != userPlayer.getBench().size()+1){
            if(playerChoice != userPlayer.getBench().size()){
                userPlayer.getBench().get(playerChoice).attachEnergy((PokemonEnergy)userPlayer.getHand().remove(locationInHand));
                userPlayer.getBench().get(playerChoice).updateDescription();
            }
            else{
                userPlayer.getActivePokemon().attachEnergy((PokemonEnergy)userPlayer.getHand().remove(locationInHand));
                userPlayer.getActivePokemon().updateDescription();
            }
        }
    }
}
