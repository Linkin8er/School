package Cards;

import Cards.Templates.PokemonCreature;

public class CharmanderOBF extends PokemonCreature{
    public CharmanderOBF(){

        retreatCost = 1;
        cardName = "Charmander (OBF)";
        cardSubType = "Basic Pokemon";
        HP = 60;

        attackOneDamage = 30;
        attackOneName = "Heat Tackle";
        attackOneDescription = "Attack One: "+attackOneName+" deals " + attackOneDamage + " damage. It costs 1 energy and deals 10 damage to itself.";

        attackTwoDamage = 0;
        attackTwoName = "";
        attackTwoDescription = "No second attack";

        cardDescription = "Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", "+attackOneDescription+", "+attackTwoDescription;

    }
    public void attackOne(PokemonCreature target){
        if(checkEnergies("fire", 1)){
            target.takeDamage(attackOneDamage);
            HP = HP - 10;
        }
    }
    
    public void attackTwo(PokemonCreature target){}
    
}
