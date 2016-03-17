package cardgame;

import cardgame.gui.GUI;
import cardgame.simulation.Engine;
import cardgame.simulation.GoFish;

/**
 * Created by andersonc12 on 3/1/2016.
        */
    public class Simulation
    {
        public static Simulation instance;
        private Engine game;
    private static GUI gui;
        private static String message = "Welcome to Go Fish! Please begin";

    public GUI getGui() {
        return gui;
    }

        public Simulation()
        {
            this.game = new GoFish();
        }

        public Engine getGame()
        {
            return this.game;
        }

        public static void setMessage(String message)
        {
            Simulation.message = message;
        }

        public static String getMessage()
        {
            return Simulation.message;
        }

        public static void main(String[] args)
        {
            Simulation.instance = new Simulation();
        Simulation.instance.game.setupGame();

        gui = new GUI(800, 600, "Go Fish!");
        }
}
