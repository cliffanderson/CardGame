package cardgame.simulation;

/**
 * Created by planot on 3/1/2016.
 */
public class GoFish extends Engine
{
    public GoFish()
    {
        super();
            super.shuffle();
        for(int i=0; i<7; i++) {
            super.getUs().hand.add(super.draw());
            super.getThem().hand.add(super.draw());
        }
    }

    public void requestmatch()
    {
        //need click listener
        //if clicked card also exists in hand of non active player remove both from each players hand and play
        //then in front of acive player: active player retains status as active player

        //if clicked card does not exist in nonactive hand, active player draws a card and passes the turn
    }
}
