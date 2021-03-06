package cardgame.gui;

import cardgame.Simulation;
import cardgame.simulation.Card;
import cardgame.simulation.GoFish;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class GUI
{
    //scaled default height and width
    private int defaultCardWidth, defaultCardHeight;
    private int width, height, numCards;
    private Image cardBack, background;

    public GUI(int width, int height, String title)
    {
        GraphicsAPI api = new GraphicsAPI(width, height, title);
        this.width = width;
        this.height = height;

        this.defaultCardHeight = Simulation.instance.getGame().getDeck().getCardHeight();
        this.defaultCardWidth =  Simulation.instance.getGame().getDeck().getCardWidth();

        try
        {
            cardBack = ImageIO.read(new File("resources/cards/cardback.png"));
            cardBack = cardBack.getScaledInstance(defaultCardWidth, defaultCardHeight, Image.SCALE_SMOOTH);

            background = ImageIO.read(new File("resources/tabletop.jpg"));
            background = background.getScaledInstance(width, height, Image.SCALE_SMOOTH);
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

                Graphics g;
                Font deckFont = new Font("Times New Roman", Font.BOLD, 32);
                Font messageFont = new Font("Times New Roman", Font.BOLD, 16);
                while (Simulation.instance.getGame() != null) {
                    g = api.getGraphics();

                    //reset
                    g.drawImage(background, 0, 0, null);

                    g.setColor(Color.BLACK);
                    g.setFont(messageFont);
                    g.drawString(Simulation.getMessage(), 50, 300);

                    //draw pile in middle
                    int x = width / 2 - defaultCardWidth / 2;
                    int y = height / 2 - defaultCardHeight / 2;
                    g.setColor(Color.black);
                    g.drawImage(cardBack, x, y, null);

                    g.setFont(deckFont);
                    //g.drawString(String.valueOf(Simulation.instance.getGame().getDeck().getSize()), x + 37, y + 87);


                    //draw our cards
                    Object[] cards = Simulation.instance.getGame().getUs().getHand().toArray();
                    numCards = cards.length;

                    for (int i = 0; i < cards.length; i++) {
                        Card card = (Card) cards[i];
                        Image image = card.getImage();
                        int drawX = i * ((width / 2) / numCards); //Evenly separated on the left half of the screen
                        int drawY = height - image.getHeight(null); //draw at the bottom on the screen
                        g.drawImage(image, drawX, drawY, null);
                    }


                    //draw opponent's cards
                    int numOpponentsCards = Simulation.instance.getGame().getThem().getHand().size();
                    //System.out.println(numOpponentsCards);
                    for(int i = 0; i < numOpponentsCards; i++)
                    {
                        int drawX = i * ((width / 2) / numOpponentsCards);
                        int drawY = 0;
                        //g.drawImage(Simulation.instance.getGame().getThem().getHand().get(i).getImage(), drawX, drawY, null);
                        g.drawImage(cardBack, drawX, drawY, null);
                    }

                    api.draw();
                   // System.out.println("loop");
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void handleClick(int x, int y)
    {
        //too high on the screen
        if(y < height - defaultCardHeight)
        {
            return;
        }

        for(int i = 1; i < numCards; i++)
        {
            if(x < i * ((width / 2) / numCards))
            {
                int cardIndex = i - 1;
                Card card = Simulation.instance.getGame().getUs().getHand().get(cardIndex);
                GoFish gf = (GoFish) Simulation.instance.getGame();
                gf.requestmatch(card);
                return;
            }
        }

        //one last check
        if(x < (width/2 + defaultCardWidth/2))
        {
            int cardIndex = numCards - 1;
            Card card = Simulation.instance.getGame().getUs().getHand().get(cardIndex);
            GoFish gf = (GoFish) Simulation.instance.getGame();
            gf.requestmatch(card);
        }
    }
}
