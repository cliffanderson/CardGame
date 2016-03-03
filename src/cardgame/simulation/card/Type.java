package cardgame.simulation.card;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public enum Type
{
    TWO(2), THREE(3);

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
