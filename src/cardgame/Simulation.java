package cardgame;

import cardgame.gui.GUI;
import cardgame.simulation.Card;
import cardgame.simulation.Engine;
import cardgame.simulation.GoFish;
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
        public static Simulation instance;
    private static GUI gui;
        private Engine game;

        public Simulation()
        {
            this.game = new GoFish();
        }

        public static void main(String[] args)
        {
            Simulation.instance = new Simulation();
        Simulation.instance.game.setupGame();

        gui = new GUI(800, 600, "Go Fish!");
        }

    public GUI getGui() {
        return gui;
    }

        public Engine getGame()
        {
            return this.game;
        }
}
