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
        them = new Player();
    }

    public void draw(){
        //LoadedDeck.pop();
    }
    public void shuffle(){
       //call deck class
    }
    public void playCard(){
        //put card from hand into play space
    }
    public void passTurn(){
        //change actitive player
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




