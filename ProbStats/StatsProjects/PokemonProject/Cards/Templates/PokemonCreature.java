package Cards.Templates;
import java.util.ArrayList;

public class PokemonCreature extends PokemonCard implements Attackable{

    protected int HP;
    protected String creatureType;
    private ArrayList<PokemonCard> attachedEnergies;

    protected String attackOneName;
    protected String attackOneCost;
    protected int attackOneDamage;
    protected String attackOneDescription;

    protected String attackTwoName;
    protected String attackTwoCost;
    protected int attackTwoDamage;
    protected String attackTwoDescription;

    public PokemonCreature(){
        cardType = "Pokemon";
    }

    public void setHP(int newHP){HP = newHP;}
    public int getHP(){ return HP;}
    public void takeDamage(int damage) {HP = HP - damage;}
    public String getCreatureType(){return creatureType;}

    public void attackOne(PokemonCreature target){}
    public void attackTwo(PokemonCreature target){}
    public String getAttackOneName(){ return attackOneName;}
    public String getAttackTwoName(){ return attackTwoName;}

}