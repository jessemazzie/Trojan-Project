import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class SmartPanel extends JPanel implements MouseListener {
    static Random rand;

    SmartPanel() {
        if(rand == null)
            rand = new Random();
        setBackground(Color.BLUE);
        addMouseListener(this);
    }

    /**
     * When a SmartPanel is moused over, the panel background color is randomized.
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
