package cardgame.simulation;

import cardgame.adt.TeamLinkedBag;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class Player {
    public Player(){
        activeplayer=false;
        hand = new TeamLinkedBag<Card>(7);
    }
    public TeamLinkedBag<Card> gethand(){
        return hand;
    }
    public void set_active(boolean new_state){
        activeplayer=new_state;
    }
    public boolean get_active(){
        return activeplayer;
    }
    TeamLinkedBag<Card> hand;
    boolean activeplayer;
}
