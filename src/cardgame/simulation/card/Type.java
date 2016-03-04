package cardgame.simulation.card;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public enum Type
{
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

    private int value;

    private Type(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    public static Type getByValue(int value)
    {
        for(Type t : Type.values())
        {
            if(t.getValue() == value)
            {
                return t;
            }
        }

        return null;
    }
}
