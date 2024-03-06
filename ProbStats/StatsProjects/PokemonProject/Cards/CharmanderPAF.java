package Cards;

import Cards.Templates.PokemonCreature;

public class CharmanderPAF extends PokemonCreature{

    public CharmanderPAF(){

        retreatCost = 1;
        cardName = "Charmander (PAF)";
        cardSubType = "Basic Pokemon";
        HP = 70;

        attackOneDamage = 0;
        attackOneName = "Blazing Destruction";
        attackOneCost = "One Fire Energy";
        attackOneDescription = "Attack One: "+attackOneName+" deals " + attackOneDamage + " damage.\nIt costs 1 energy and destroys the current stadium.";

        attackTwoDamage = 30;
        attackTwoName = "Steady Firebreathing";
        attackTwoCost = "Two Fire Energies";
        attackTwoDescription = "Attack Two: "+attackTwoName+" deals " + attackTwoDamage + " damage.\nIt costs 2 energy";

        cardDescription = "Name: "+cardName+", Card type:"+cardType+", Current HP: "+HP+", "+attackOneDescription+", "+attackTwoDescription;

    }
    public void attackOne(PokemonCreature target){

        if(checkEnergies("fire", 1)){
            System.out.println("Trainer:\tCharmander, use blazing destruction!");
            System.out.println("Charmander:\tAight.");
            target.takeDamage(attackOneDamage);
        }
    }
    
    public void attackTwo(PokemonCreature target){
        if(checkEnergies("fire", 2)){
            System.out.println("Trainer:\tCharmander, use steady firebreathing!");
            System.out.println("Charmander:\tAight.");
            target.takeDamage(attackTwoDamage);
        }
    }
    
}
