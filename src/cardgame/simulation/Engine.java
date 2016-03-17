package cardgame.simulation;

import cardgame.Simulation;

/**
 * Created by planot on 3/1/2016.
 */
public abstract class Engine
{
    private Player them;
    private Player us;
    private Deck deck;

    public Engine()
    {
        this.deck = new Deck();
        us = new Player();
        us.setActive(true);
        them = new Player();
    }

    public void passTurn(){
        //change actitive player
        us.setActive(!us.getActive());
        them.setActive(!them.getActive());
        GoFish gf = (GoFish) Simulation.instance.getGame();
        if(getThem().getActive()) {
            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            gf.requestmatch();
        }

    }

    public Deck getDeck()
    {
        return this.deck;
    }

    public Player getUs() {
        return us;
    }

    public Player getThem() {
        return them;
    }

    public abstract void setupGame();
}




