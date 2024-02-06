package Cards;

import Cards.CardTemplates.PokemonCreature;

public class CharmanderPAF extends PokemonCreature{

    public CharmanderPAF(){

        cardName = "Charmander (PAF)";
        cardType = "Basic Pokemon";
        HP = 70;

        attackOneDamage = 0;
        attackOneName = "Blazing Destruction";

        attackTwoDamage = 30;
        attackTwoName = "Steady Firebreathing";

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
