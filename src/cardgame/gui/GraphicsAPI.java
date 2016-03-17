package cardgame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GraphicsAPI {

    private BufferStrategy strategy;
    Canvas canvas = new Canvas();

    public GraphicsAPI(int x, int y, String title)
    {

        JFrame frame = new JFrame(title);
        JPanel panel = (JPanel) frame.getContentPane();
        canvas.addMouseListener(new MouseEventListener());
        panel.setPreferredSize(new Dimension(x, y));
        panel.setLayout(null);

        canvas.setBounds(0, 0, x, y);
        panel.add(canvas);

        canvas.setIgnoreRepaint(true);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.requestFocus();

        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/*
		JFrame frame = new JFrame(title);
		canvas = new Canvas();

		canvas.setPreferredSize(new Dimension(x, y));

		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);

		canvas.setIgnoreRepaint(true);

		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		canvas.requestFocus();

		canvas.createBufferStrategy(3);
		strategy = canvas.getBufferStrategy();
		*/
    }

    public static void main(String[] args){}

    public Graphics getGraphics() {
        return strategy.getDrawGraphics();
    }

    public void draw()
    {
        getGraphics().dispose();
        canvas.getBufferStrategy().show();
    }
}