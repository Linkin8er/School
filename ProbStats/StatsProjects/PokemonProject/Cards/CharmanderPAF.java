package Cards;

import Cards.Templates.PokemonCreature;

public class CharmanderPAF extends PokemonCreature{

    public CharmanderPAF(){

        cardName = "Charmander (PAF)";
        cardSubType = "Basic Pokemon";
        HP = 70;

        attackOneDamage = 0;
        attackOneName = "Blazing Destruction";
        attackOneDescription = "Attack One: "+attackOneName+" deals " + attackOneDamage + " damage.\nIt costs 1 energy and destroys the current stadium.";

        attackTwoDamage = 30;
        attackTwoName = "Steady Firebreathing";
        attackTwoDescription = "Attack Two: "+attackTwoName+" deals " + attackTwoDamage + " damage.\nIt costs 2 energy";

        cardDescription = "Name: "+cardName+"\nCard type:"+cardType+"\nCurrent HP: "+HP+"\n"+attackOneDescription+"\n"+attackTwoDescription;

    }
    public void attackOne(PokemonCreature target){

        System.out.println("Trainer:\tCharmander, use blazing destruction!");
        System.out.println("Charmander:\tAight.");
        target.takeDamage(attackOneDamage);
    }
    
    public void attackTwo(PokemonCreature target){
        
        System.out.println("Trainer:\tCharmander, use steady firebreathing!");
        System.out.println("Charmander:\tAight.");
        target.takeDamage(attackTwoDamage);
    }
    
}
