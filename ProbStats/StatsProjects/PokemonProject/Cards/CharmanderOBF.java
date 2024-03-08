package Cards;

import Cards.Templates.PokemonCreature;
import Cards.Templates.PokemonPlayer;

public class CharmanderOBF extends PokemonCreature{
    public CharmanderOBF(){

        retreatCost = 1;
        cardName = "Charmander (OBF)";
        cardSubType = "Basic Pokemon";
        HP = 60;

        attackOneDamage = 30;
        attackOneName = "Heat Tackle";
        attackOneDescription = "Attack One: "+attackOneName+".\n        "+ attackOneName + " deals " + attackOneDamage + " damage. It costs 1 energy and deals 10 damage to itself.";

        secondAttack = false;
        attackTwoDamage = 0;
        attackTwoName = "";
        attackTwoDescription = "No second attack";

        cardDescription = "    Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", Retreat cost: " +retreatCost+ "\n" +printAttachedEnergies()+"    "+attackOneDescription+"\n    "+attackTwoDescription;
    }
    //This one jsut attacks for a small ammount and then hurts itself
    public void attackOne(PokemonPlayer user, PokemonPlayer opponent){
        if(checkEnergies("Fire", 1)){
            opponent.getActivePokemon().takeDamage(attackOneDamage);
            HP = HP - 10;
        }
    }
    
    public void attackTwo(PokemonPlayer user, PokemonPlayer opponent){}

    //This is jsut used to update health/energies
    public void updateDescription(){cardDescription = "    Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", Retreat cost: " +retreatCost+ "\n" +printAttachedEnergies()+"    "+attackOneDescription+"\n    "+attackTwoDescription;}
    
}
