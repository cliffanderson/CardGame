package cardgame.simulation;

/**
 * Created by planot on 3/1/2016.
 */
public class GoFish extends Engine
{
    public GoFish()
    {
        super();
    }

    public void setupGame()
    {
        for(int i=0; i<7; i++) {
            getUs().draw();
            getThem().draw();
        }
    }

    public void requestmatch(Card ChosenCard)
    {
        //called by clicker
        //if clicked card also exists in hand of non active player remove both from each players hand and play
        //then in front of acive player: active player retains status as active player
        if(getThem().getHand().contains(ChosenCard)){
            getUs().playCard();
            getThem().playCard();
        }

        //if clicked card does not exist in nonactive hand, active player draws a card and passes the turn
        else if(!getThem().getHand().contains(ChosenCard)){
            getUs().draw();
            passTurn();
        }
    }
}
