package cardgame.gui;

import cardgame.Simulation;
import cardgame.simulation.Card;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class GUI
{
    private int width, height, defaultCardWidth, defaultCardHeight;
    private Image blankCard;

    public GUI(int width, int height, String title)
    {
        GraphicsAPI api = new GraphicsAPI(width, height, title);
        this.width = width;
        this.height = height;

        this.defaultCardHeight = Simulation.instance.getGame().getDeck().removeCard(0).getImage().getHeight(null);
        this.defaultCardWidth =  Simulation.instance.getGame().getDeck().removeCard(0).getImage().getWidth(null);

        try
        {
            blankCard = ImageIO.read(new File("resources/cardHolders/blankCard.png"));
            blankCard = blankCard.getScaledInstance((int) (blankCard.getWidth(null) * 0.5), (int) (blankCard.getHeight(null) * 0.5), Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            System.err.println("Error loading blank card");
            e.printStackTrace();
        }

        mainLoop(api);
    }

    private void mainLoop(final GraphicsAPI api)
    {
        new Thread() {
            @Override
            public void run() {
                Simulation.instance.getGame().getUs().draw();

                Graphics g;
                Font font = new Font("Times New Roman", Font.BOLD, 32);
                while (true) {
                    g = api.getGraphics();
                    g.setFont(font);

                    //reset
                    g.setColor(Color.white);
                    g.fillRect(0, 0, width, height);

                    //draw pile in middle
                    int x = width / 2 - ((int) (defaultCardWidth * 0.5)) / 2;
                    int y = height / 2 - ((int) (defaultCardHeight * 0.5)) / 2;
                    g.setColor(Color.black);
                    g.drawRect(x, y, (int) (defaultCardWidth), (int) (defaultCardHeight));

                    g.drawString(String.valueOf(Simulation.instance.getGame().getDeck().getSize()), x + 37, y + 87);


                    //draw our cards
                    int numCards = Simulation.instance.getGame().getUs().getHand().getCurrentSize();

                    for (int i = 0; i < numCards; i++) {
                        Card card = Simulation.instance.getGame().getUs().getHand().
                        Image image = card.getImage();
                        int drawX = i * ((width / 2) / numCards); //Evenly separated on the left half of the screen
                        int drawY = height - image.getHeight(null); //draw at the bottom on the screen
                        g.drawImage(image, drawX, drawY, null);
                    }


                    //draw opponent's cards
                    int numOpponentsCards = Simulation.instance.getGame().getThem().getHand().getCurrentSize();

                    for(int i = 0; i < numOpponentsCards; i++)
                    {
                        int drawX = i * ((width / 2) / numOpponentsCards);
                        int drawY = 0;
                        g.drawImage(blankCard, drawX, drawY, null);
                    }

                    api.draw();
                   // System.out.println("loop");
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }
}
