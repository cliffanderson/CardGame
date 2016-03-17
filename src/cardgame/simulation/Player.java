package cardgame.simulation;

import cardgame.Simulation;
import cardgame.adt.TeamLinkedBag;

import java.util.ArrayList;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class Player
{
    public Player()
    {
        activeplayer=false;
        hand = new ArrayList<Card>();
    }
    public void playCard(Card card){
        hand.remove(card);
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void draw()
    {
        Card c = Simulation.instance.getGame().getDeck().removeCard(0);
        hand.add(c);
    }

    public void setActive(boolean newState)
    {
        activeplayer = newState;
    }

    public boolean getActive()
    {
        return activeplayer;
    }

    private ArrayList<Card> hand;
    private boolean activeplayer;
}
