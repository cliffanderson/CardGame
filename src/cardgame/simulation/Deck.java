package cardgame.simulation;

import cardgame.adt.Pile;
import cardgame.simulation.card.Suit;
import cardgame.simulation.card.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class Deck {
    Pile<Card> contents;

    public Deck(){
        contents = new Pile<>();
        loadCards();
        shuffle();
        //construct empty list of cards
        //here
        //create card
        //push card onto list
        //goto
    }

    private void shuffle()
    {
        //take a random card from the deck and put it at the bottom
        //do this 10000 times
        for(int i = 0; i < 10000; i++)
        {
            int cardNum = (int) (Math.random() * contents.getLength());
            Card c = contents.remove(cardNum);
            contents.add(c);
        }
    }

    private void loadCards()
    {
        File cardFolder = new File("resources/cards");
        File[] fileList = cardFolder.listFiles();

        for(File f : fileList)
        {
            if(f.isDirectory()) continue;

            //get name of file without extension
            String name = f.getName().replace(".jpg", "");

            //get type code by removing all non-numeric characters from name
            int typeCode = Integer.parseInt(name.replaceAll("[^\\d.]", ""));
            Type type = Type.getByValue(typeCode);

            //get suite name by removing all digits from name
            String suitName = name.replaceAll("[0-9]", "");
            Suit suit = Suit.getByName(suitName);

            //get the image
            Image image;
            try{
                image = ImageIO.read(f);
                image = image.getScaledInstance((int) (image.getWidth(null) * 0.5), (int) (image.getHeight(null) * 0.5), Image.SCALE_SMOOTH);
            }
            catch (IOException e)
            {
                System.err.println("Error reading image: " + f.getName());
                e.printStackTrace();
                continue;
            }

            Card card = new Card(image, type, suit);
            this.contents.add(card);
        }

        System.out.println("Loaded " + this.contents.getLength() + " cards");
    }

    public Card getCard(int index)
    {
        return this.contents.getEntry(index);
    }

    public int getSize()
    {
        return this.contents.getLength();
    }
}
