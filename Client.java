import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Client extends JFrame {
    Talker t;

    Client() {
        JPanel containerPanel;
        Container cp;
        cp = getContentPane();

        containerPanel = new JPanel(new GridLayout(5, 10));

        for(int i = 0; i < 50; i++)
            containerPanel.add(new SmartPanel());

        try {
            t = new Talker("127.0.0.1", 555, "CLI");
        } catch (IOException e) {
            e.printStackTrace();
        }

        cp.add(containerPanel);
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
