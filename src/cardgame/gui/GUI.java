package cardgame.gui;

import cardgame.Simulation;

import java.awt.*;

/**
 * Created by andersonc12 on 3/1/2016.
 */
public class GUI
{
    private int width, height, cardWidth, cardHeight;

    public GUI(int width, int height, String title)
    {
        GraphicsAPI api = new GraphicsAPI(width, height, title);
        this.width = width;
        this.height = height;

        this.cardHeight = Simulation.instance.getPile().get(0).getImage().getHeight(null);
        this.cardWidth =  Simulation.instance.getPile().get(0).getImage().getWidth(null);

        mainLoop(api);
    }

    private void mainLoop(GraphicsAPI api)
    {
        double scaleFactor = 0.5;
        this.cardWidth  *= scaleFactor;
        this.cardHeight *= scaleFactor;
        Graphics g;
        while(true)
        {
            g = api.getGraphics();

            //reset
            g.setColor(Color.white);
            g.fillRect(0, 0, this.width, this.height);

            //draw pile in middle
            int x = this.width/2 - this.cardWidth/2;
            int y = this.height/2 - this.cardHeight/2;
            g.setColor(Color.black);
            g.drawRect(x, y, this.cardWidth, this.cardHeight);
            g.drawString("Pile: " + Simulation.instance.getPile().size(), x + 5, y + 20);

            api.draw();
            System.out.println("loop");
            try {
                Thread.sleep(10);
            }
            catch (Exception e){}
        }
    }
}
