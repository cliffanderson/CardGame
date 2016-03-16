package cardgame.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by andersonc12 on 3/16/2016.
 */
public class MouseEventListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click");
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
