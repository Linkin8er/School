package Cards.Templates;
import java.util.ArrayList;

//All pokemon creatures will use these things.
public class PokemonCreature extends PokemonCard implements Attackable, Playable{

    protected int HP;
    protected int retreatCost;
    protected ArrayList<PokemonEnergy> attachedEnergies;

    protected String attackOneName;
    protected int attackOneDamage;
    protected String attackOneDescription;

    protected boolean secondAttack;
    protected String attackTwoName;
    protected int attackTwoDamage;
    protected String attackTwoDescription;

    public PokemonCreature(){
        cardType = "Pokemon";
        attachedEnergies = new ArrayList<PokemonEnergy>();
    }

    //Some basic setters and getters
    public void setHP(int newHP){HP = newHP;}
    public int getHP(){ return HP;}
    public void takeDamage(int damage) {HP = HP - damage;}
    public int getRetreatCost(){ return retreatCost;}

    public void attackOne(PokemonPlayer user, PokemonPlayer opponent){}
    public boolean hasSecondAttack(){ return secondAttack;}
    public void attackTwo(PokemonPlayer user, PokemonPlayer opponent){}
    public void updateDescription(){}

    public ArrayList<PokemonEnergy> getAttachedEnergies(){ return attachedEnergies;}

    //This is used for printing all of the enrgies attached to a pokemon
    public String printAttachedEnergies(){
        String printingEnergies = "";
        if(attachedEnergies.isEmpty()) printingEnergies = ("No energies!");
        else{
            printingEnergies ="      Attached Energies: ";
            for(int i = 0; i < attachedEnergies.size(); i++){
                printingEnergies += attachedEnergies.get(i).getCardName() + " ";
            }
        }
        return printingEnergies;
    }

    public String getAttackOneName(){ return attackOneName;}
    public String getAttackOneDiscription(){ return attackOneDescription;}
    public String getAttackTwoName(){ return attackTwoName;}
    public String getAttackTwoDiscription(){ return attackOneDescription;}
    public void attachEnergy(PokemonEnergy energy){attachedEnergies.add(energy);}

    //All pokemon are played onto the bench the same, so this is the basic player for them all
    public void playCard(PokemonPlayer userPlayer, PokemonPlayer opponentPlayer, int locationInHand){
        if(!userPlayer.getBench().isEmpty()){
            if(userPlayer.getBench().size() > 5){ System.out.println("Your bench if full!"); }
            else userPlayer.getBench().add((PokemonCreature)userPlayer.getHand().remove(locationInHand));
        }
        else{ 
            userPlayer.getBench().add((PokemonCreature)userPlayer.getHand().remove(locationInHand));
        }
    }
    
    //Retreating and attacks cost energies, so this method checks for the proper number of correct energies
    public boolean checkEnergies(String energyTypeNeeded, int countNeeded){

        if(attachedEnergies.isEmpty()) return false;
        boolean hasEnergy = false;
        int energyCount = 0;
        boolean needTypedEnergy = true;
        if(energyTypeNeeded.equals("Any")) needTypedEnergy = false;

        if(needTypedEnergy){
            for(int i = 0; i < attachedEnergies.size(); i ++){
                if(attachedEnergies.get(i).getCardSubType().equals(energyTypeNeeded)){
                    energyCount ++;
                }
            }
        }
        else energyCount = attachedEnergies.size();

        if(energyCount >= countNeeded){ hasEnergy = true;}
        if(!hasEnergy) System.out.println(cardName + " does not have enough energy to do that!");
        return hasEnergy;
    }

}