package Cards.Templates;
import java.util.ArrayList;

public class PokemonCreature extends PokemonCard implements Attackable, Playable{

    protected int HP;
    protected int retreatCost;
    protected ArrayList<PokemonEnergy> attachedEnergies;

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

    public void attackOne(PokemonCreature target){}
    public void attackTwo(PokemonCreature target){}

    public ArrayList<PokemonEnergy> getAttachedEnergies(){ return attachedEnergies;}
    public String getAttackOneName(){ return attackOneName;}
    public String getAttackTwoName(){ return attackTwoName;}
    public void attachEnergy(PokemonEnergy energy){attachedEnergies.add(energy);}

    public void playCard(PokemonPlayer userPlayer, PokemonPlayer opponentPlayer, int locationInHand){
        if(userPlayer.getBench().size() > 5){ System.out.println("Your bench if full!"); }
        else{ userPlayer.getBench().add((PokemonCreature)userPlayer.getHand().remove(locationInHand));
        }
    }
    public boolean checkEnergies(String energyTypeNeeded, int countNeeded){

        boolean hasEnergy = false;
        int fireEnergyCount = 0;

        for(int i = 0; i < attachedEnergies.size(); i ++){
            if(attachedEnergies.get(i).getCardSubType().equals(energyTypeNeeded)){
                fireEnergyCount ++;
            }
        }

        if(fireEnergyCount >= countNeeded){ hasEnergy = true;}
        if(!hasEnergy) System.out.println(cardName + " does not have enough energy to do that!");
        return hasEnergy;
    }

}