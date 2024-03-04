package Cards;

import Cards.CardTemplates.PokemonCreature;

public class CharmanderOBF extends PokemonCreature{
    public CharmanderOBF(){

        cardName = "Charmander (OBF)";
        cardType = "Basic Pokemon";
        HP = 60;

        attackOneDamage = 30;
        attackOneName = "Heat Tackle";
        attackOneDescription = "Attack One: "+attackOneName+" deals " + attackOneDamage + " damage.\nIt costs 1 energy and deals 10 damage to itself.";

        attackTwoDamage = 0;
        attackTwoName = "";
        attackTwoDescription = "No second attack";

        cardDescription = "Name: "+cardName+"\nCard type:"+cardType+"\nCurrent HP: "+HP+"\n"+attackOneDescription+"\n"+attackTwoDescription;

    }
    public void attackOne(PokemonCreature target){

        target.takeDamage(attackOneDamage);
        HP = HP - 10;
    }
    
    public void attackTwo(PokemonCreature target){}
    
}
