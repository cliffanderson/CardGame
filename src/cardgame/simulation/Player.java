package cardgame.simulation;

import cardgame.Simulation;
import cardgame.adt.TeamLinkedBag;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class Player
{
    public Player()
    {
        activeplayer=false;
        hand = new TeamLinkedBag<Card>();
    }
    public void playCard(){
        //put card from hand into play space
    }

    public TeamLinkedBag<Card> getHand()
    {
        return hand;
    }

    public void draw()
    {
        hand.add(Simulation.instance.getGame().getDeck().removeCard(0));
    }

    public void setActive(boolean newState)
    {
        activeplayer = newState;
    }

    public boolean getActive()
    {
        return activeplayer;
    }

    private TeamLinkedBag<Card> hand;
    private boolean activeplayer;
}
