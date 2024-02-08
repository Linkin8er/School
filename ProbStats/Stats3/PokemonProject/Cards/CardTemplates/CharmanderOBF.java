package Cards.CardTemplates;

public class CharmanderOBF extends PokemonCreature{
    public CharmanderOBF(){

        cardName = "Charmander (OBF)";
        cardType = "Basic Pokemon";
        HP = 60;

        attackOneDamage = 30;
        attackOneName = "Blazing Destruction";
        attackOneDescription = "Attack One: "+attackOneName+" deals " + attackOneDamage + " damage.\nIt costs 1 energy and destroys the current stadium.";

        attackTwoDamage = 0;
        attackTwoName = "";
        attackTwoDescription = "No second attack";

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
