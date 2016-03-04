package cardgame.simulation;

import cardgame.simulation.card.Suit;
import cardgame.simulation.card.Type;

import java.awt.*;

/**
 * Created by planot on 3/2/2016.
 */
public class Card {
    private Image image;
    private Type type;
    private Suit suit;

    public Card(Image image, Type type, Suit suit) {
        this.image = image;
        this.type = type;
        this.suit = suit;
    }

    public Image getImage() {
        return image;
    }

    public Type getType() {
        return type;
    }

    public Suit getSuit() {
        return suit;
    }
}

