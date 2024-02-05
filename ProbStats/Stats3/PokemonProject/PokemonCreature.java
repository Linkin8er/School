import java.util.ArrayList;

public class PokemonCreature extends PokemonCard implements Attackable{

    private int HP;
    private String creatureType;
    private ArrayList<PokemonCard> attachedEnergies;

    public PokemonCreature(){
        cardType = "Creature";
    }

    public int getHP(){ return HP;}
    public String getCreatureType(){return creatureType;}

    public void attackOne(PokemonCreature target){}
    public void attackTwo(PokemonCreature target){}

}