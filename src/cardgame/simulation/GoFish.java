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
        if (getUs().getActive()==true) {
            //called by clicker
            //if clicked card also exists in hand of non active player remove both from each players hand and play
            //then in front of acive player: active player retains status as active player
            boolean match = false;
            int MatchingCardID = 0;
            for (int i = 0; i < getThem().getHand().size(); i++) {
                if (ChosenCard.getType().getValue() == getThem().getHand().get(i).getType().getValue()) {
                    match = true;
                    MatchingCardID = i;
                    break;
                }
            }

            if (match == true) {
                getUs().playCard(ChosenCard);
                getThem().playCard(getThem().getHand().get(MatchingCardID));
            }

            //if clicked card does not exist in nonactive hand, active player draws a card and passes the turn
            else {
                getUs().draw();
                passTurn();
            }
        }
    }
    public void requestmatch() {
        if (getThem().getActive()==true){
            Card Randomchioce=getThem().getHand().get((int)(Math.random()*getThem().getHand().size()));
            boolean match2 = false;
            int MatchingCardID2 = 0;
            for (int j = 0; j < getUs().getHand().size(); j++) {
                if (Randomchioce.getType().getValue() == getUs().getHand().get(j).getType().getValue()) {
                    match2 = true;
                    MatchingCardID2 = j;
                    break;
                }
            }
            if (match2 == true) {
                getThem().playCard(Randomchioce);
                getUs().playCard(getUs().getHand().get(MatchingCardID2));
            }

            //if clicked card does not exist in nonactive hand, active player draws a card and passes the turn
            else {
                getThem().draw();
                passTurn();
            }
        }
    }

}
