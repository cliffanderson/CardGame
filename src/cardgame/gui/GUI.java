package cardgame.gui;

import cardgame.Simulation;
import cardgame.simulation.Card;

import java.awt.*;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class GUI
{
    private int width, height, defaultCardWidth, defaultCardHeight;

    public GUI(int width, int height, String title)
    {
        GraphicsAPI api = new GraphicsAPI(width, height, title);
        this.width = width;
        this.height = height;

        System.out.println("Instance: " + Simulation.instance);
        System.out.println("Game: " + Simulation.instance.getGame());
        System.out.println("There are " + Simulation.instance.getGame().getDeck().getSize() + " cards");
        System.out.println("First card: " + Simulation.instance.getGame().getDeck().getCard(0));
        System.out.println("Image object: " + Simulation.instance.getGame().getDeck().getCard(0).getImage());
        this.defaultCardHeight = Simulation.instance.getGame().getDeck().getCard(0).getImage().getHeight(null);
        this.defaultCardWidth =  Simulation.instance.getGame().getDeck().getCard(0).getImage().getWidth(null);

        mainLoop(api);
    }

    private void mainLoop(GraphicsAPI api)
    {
        Simulation.instance.getGame().getUs().draw();

        Graphics g;
        Font font = new Font("Times New Roman", Font.BOLD, 32);
        while(true)
        {
            g = api.getGraphics();
            g.setFont(font);

            //reset
            g.setColor(Color.white);
            g.fillRect(0, 0, this.width, this.height);

            //draw pile in middle
            int x = this.width/2 - ((int) (this.defaultCardWidth * 0.5)) /2;
            int y = this.height/2 - ((int) (this.defaultCardHeight * 0.5)) /2;
            g.setColor(Color.black);
            g.drawRect(x, y, (int) (this.defaultCardWidth * 0.5), (int) (this.defaultCardHeight * 0.5));

            g.drawString(String.valueOf(Simulation.instance.getGame().getDeck().getSize()), x + 37, y + 87);


            //draw our cards
            int numCards = Simulation.instance.getGame().getUs().getHand().getCurrentSize();
            int maxWidth = this.width / numCards;

            if(maxWidth > this.defaultCardWidth)
            {
                maxWidth = this.defaultCardWidth;
            }

            for(int i = 0; i < numCards; i++) {
                Card card = Simulation.instance.getGame().getDeck().getCard(i);
                Image image = card.getImage();
                g.drawImage(image, i * maxWidth, this.height - image.getHeight(null), null);
            }

            api.draw();
            System.out.println("loop");
            try {
                Thread.sleep(10);
            }
            catch (Exception e){}
        }
    }
}
