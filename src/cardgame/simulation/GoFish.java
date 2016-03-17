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
        boolean match=false;
        int MatchingCardID=0;
        for(int i=0;i<getThem().getHand().size();i++){
            if(ChosenCard.getType().getValue()== getThem().getHand().get(i).getType().getValue()){
                match=true;
                MatchingCardID=i;
                break;
            }
        }

        if(match==true){
            getUs().playCard(ChosenCard);
            getThem().playCard(getThem().getHand().get(MatchingCardID));
        }

        //if clicked card does not exist in nonactive hand, active player draws a card and passes the turn
        else{
            getUs().draw();
            passTurn();
        }
    }
}
