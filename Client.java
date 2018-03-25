import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {
    Client() {
        Container cp;
        cp = getContentPane();
        

        setupMainFrame();
    }

    void setupMainFrame() {
        Toolkit tk;
        Dimension d;

        tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        setSize(d.width/4, d.height/4);
        setLocation(d.width/4, d.height/4);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Fun game that is not a trojan.");

        setVisible(true);
    }
}
