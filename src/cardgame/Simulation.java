package cardgame;

import cardgame.gui.GUI;
import cardgame.simulation.Card;
import cardgame.simulation.card.Suit;
import cardgame.simulation.card.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class Simulation
{
    private ArrayList<Card> pile;
    public static Simulation instance;

    public Simulation()
    {
        this.pile = new ArrayList<>();
        loadCards();
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
            }
            catch (IOException e)
            {
                System.err.println("Error reading image: " + f.getName());
                e.printStackTrace();
                continue;
            }

            Card card = new Card(image, type, suit);
            this.pile.add(card);
        }

        System.out.println("Loaded " + this.pile.size() + " cards");
    }

    public ArrayList<Card> getPile() {
        return pile;
    }

    public static void main(String[] args)
    {
        Simulation sim = new Simulation();
        Simulation.instance = sim;
        GUI gui = new GUI(800, 600, "Go Fish!");
    }
}
