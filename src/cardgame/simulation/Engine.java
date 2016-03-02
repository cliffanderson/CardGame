package cardgame.simulation;

/**
 * Created by planot on 3/1/2016.
 */
public abstract class Engine {

    public void draw(){
        //LoadedDeck.pop();
    }
    public void shuffle(){
       //call deck class
    }
    public void play_card(){
        //put card from hand into play space
    }
    public void pass_turn(){
        //change actitive player
    }
    Deck LoadedDeck;
    Player[] Players;
    int NumofPlayers;
}




