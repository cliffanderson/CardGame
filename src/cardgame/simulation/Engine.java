package cardgame.simulation;

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

    public Card draw(){
       return deck.removeCard(0);
    }
    public void shuffle(){

    }

    public void passTurn(){
        //change actitive player
        us.setActive(!us.getActive());
        them.setActive(!them.getActive());
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
}




