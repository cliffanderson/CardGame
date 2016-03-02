package cardgame.simulation;

import java.awt.image.BufferedImage;
/**
 * Created by andersonc12 on 3/1/2016.
 */
public class Card {
    private String suite="";
    private int value = 0;
    BufferedImage card =null;
    private int x,y;

    public Card(String suite, int value, BufferedImage card)
    {
        this.suite=suite;
        this.value=value;
        this.card=card;
    }
    public String getSuite() {
        return suite;
    }
    public int getValue() {
        return value;
    }
    public BufferedImage getCard() {
        return card;
    }
    public boolean compare(String type)
    {
        return type.equals(value + suite);
    }
    public int getColor()
    {
        if(suite.equals("heart")||suite.equals("diamond"))
        {
            return 1;
        }
        return 0;
    }
}
