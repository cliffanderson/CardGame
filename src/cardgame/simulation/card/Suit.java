package cardgame.simulation.card;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public enum Suit
{
    HEART("Heart"), DIAMOND("Diamond"), CLUB("Club"), SPADE("Spade");

    private String name;

    private Suit(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public static Suit getByName(String name)
    {
        for(Suit suit : Suit.values())
        {
            if(suit.getName().equalsIgnoreCase(name))
            {
                return suit;
            }
        }

        return null;
    }
}
