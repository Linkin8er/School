package Cards;

import Cards.Templates.PokemonCreature;
import Cards.Templates.PokemonPlayer;

public class CharmanderPAF extends PokemonCreature{

    public CharmanderPAF(){

        retreatCost = 1;
        cardName = "Charmander (PAF)";
        cardSubType = "Basic Pokemon";
        HP = 70;

        attackOneDamage = 0;
        attackOneName = "Blazing Destruction";
        attackOneDescription = "Attack One: "+attackOneName+".\n        "+ attackOneName + " deals " + attackOneDamage + " damage. It costs 1 fire energy and destroys the current stadium.";

        secondAttack = true;
        attackTwoDamage = 30;
        attackTwoName = "Steady Firebreathing";
        attackTwoDescription = "Attack Two: "+attackTwoName+".\n        "+ attackOneName + " deals " + attackTwoDamage + " damage.\nIt costs 2 fire energy";

        cardDescription = "    Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", Retreat cost: " +retreatCost+ "\n" +printAttachedEnergies()+"    "+attackOneDescription+"\n    "+attackTwoDescription;

    }
    //This first attack doesn't deal damage but is supposed to destroy the active stadium. There are no stadiums currently so this does nothing
    public void attackOne(PokemonPlayer user, PokemonPlayer opponent){

        if(checkEnergies("Fire", 1)){
            System.out.println("Trainer:\tCharmander, use blazing destruction!");
            System.out.println("Charmander:\tAight.");
            opponent.getActivePokemon().takeDamage(attackOneDamage);
        }
    }
    
    //Second attack just hits for a little
    public void attackTwo(PokemonPlayer user, PokemonPlayer opponent){
        if(checkEnergies("Fire", 2)){
            System.out.println("Trainer:\tCharmander, use steady firebreathing!");
            System.out.println("Charmander:\tAight.");
            opponent.getActivePokemon().takeDamage(attackTwoDamage);
        }
    }
    public void updateDescription(){cardDescription = "    Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", Retreat cost: " +retreatCost+ "\n" +printAttachedEnergies()+"    "+attackOneDescription+"\n    "+attackTwoDescription;}
    
}
