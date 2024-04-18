package Cards;

import java.util.ArrayList;

import Cards.Templates.PokemonCreature;
import Cards.Templates.PokemonPlayer;


public class ArceusV extends PokemonCreature{
    public ArceusV(){
        retreatCost = 2;
        cardName = "ArceusV";
        cardSubType = "Basic Pokemon";
        HP = 220;

        attackOneDamage = 0;
        attackOneName = "Trinity Charge";
        attackOneDescription = "Attack One: "+attackOneName+".\n        "+ attackOneName + " deals " + attackOneDamage + " damage. It costs 2 energy and searched for 3 energy to attach to your V Pokemon.";

        secondAttack = true;
        attackTwoDamage = 130;
        attackTwoName = "Power Edge";
        attackTwoDescription = "Attack Two: "+attackTwoName+".\n        "+ attackTwoName + " deals " + attackTwoDamage + " damage. It costs 3 energy and deals a large ammount of damage.";

        cardDescription = "    Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", Retreat cost: " +retreatCost+ "\n" +printAttachedEnergies()+"    "+attackOneDescription+"\n    "+attackTwoDescription;
    }

    //The first attack goes thorough the deck to get an energy and add it to hand
    public void attackOne(PokemonPlayer user, PokemonPlayer opponent){

        if(checkEnergies("Any", 2)){
            System.out.println("Trainer:\tArceus, use Trinity Charge!");
            System.out.println("Arceus:\tAight.");
            ArrayList<Integer> cardLocations = new ArrayList();
            for(int i = 0; i < user.getDeck().size(); i++){
                if(user.getDeck().get(i).getCardType().equals("Energy")){ cardLocations.add(i);}
            }
            System.out.println("Would you like to grab:");
            for(int x = 0; x < cardLocations.size(); x++){
                System.out.println(x + ") " + user.getDeck().get(cardLocations.get(x)).getCardName());
            }
        
            int playerChoice = choiceChecker(0, cardLocations.size());
            int deckLocation = cardLocations.get(playerChoice);

            user.getHand().add((PokemonCreature)user.getDeck().remove(deckLocation));
            user.shuffleDeck();
        }
    }
    
    //This attack just hits for alot
    public void attackTwo(PokemonPlayer user, PokemonPlayer opponent){
        if(checkEnergies("Any", 3)){
            System.out.println("Trainer:\tArceus, use Power Edge!");
            System.out.println("Arceus:\tAight.");
            opponent.getActivePokemon().takeDamage(attackTwoDamage);
        }
    }
    public void updateDescription(){cardDescription = "    Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", Retreat cost: " +retreatCost+ "\n" +printAttachedEnergies()+"    "+attackOneDescription+"\n    "+attackTwoDescription;}
}
