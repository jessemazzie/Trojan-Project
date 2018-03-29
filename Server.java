import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements ActionListener, Runnable {
    Socket s;
    Talker talker;

    JRadioButton exitClientButton;
    JRadioButton sendMessageToClientButton;
    JRadioButton getMMFilesFromClientButton;
    JTextField messageField;
    JButton sendButton;

    Server() {
        Container cp;
        JPanel optionPanel;
        JPanel textPanel;
        JPanel buttonPanel;
        ButtonGroup groupOfOptions;

        cp = getContentPane();
        optionPanel = new JPanel();
        groupOfOptions = new ButtonGroup();

        exitClientButton = new JRadioButton("Kill client");
        exitClientButton.setSelected(true); //Default value
        sendMessageToClientButton = new JRadioButton("Send message");
        getMMFilesFromClientButton = new JRadioButton("Get multimedia file list");

        groupOfOptions.add(exitClientButton);
        groupOfOptions.add(sendMessageToClientButton);
        groupOfOptions.add(getMMFilesFromClientButton);

        optionPanel.add(exitClientButton);
        optionPanel.add(sendMessageToClientButton);
        optionPanel.add(getMMFilesFromClientButton);

        textPanel = new JPanel();
        messageField = new JTextField(25);
        textPanel.add(messageField);

        buttonPanel = new JPanel();
        sendButton = new JButton("Send");
        sendButton.setActionCommand("SEND");
        sendButton.addActionListener(this);
        buttonPanel.add(sendButton);

        try {
            s = new ServerSocket(555).accept();
            talker = new Talker(s, "SERVER");
        } catch (IOException e) {
            e.printStackTrace();
        }

        cp.add(optionPanel, BorderLayout.NORTH);
        cp.add(textPanel, BorderLayout.CENTER);
        cp.add(buttonPanel, BorderLayout.SOUTH);
        setupMainFrame();
        new Thread(this).start(); //Executes run() method which will listen in a separate thread for data sent from client.
    }

    JRadioButton getRadioButton(String label) {
        JRadioButton tempRadioButton = new JRadioButton(label);

        return tempRadioButton;
    }

    void setupMainFrame() {
        Toolkit tk;
        Dimension d;

        tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        setSize(d.width/4, d.height/4);
        setLocation(d.width/4, d.height/4);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Server side.");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String ac = ae.getActionCommand();

        if(ac.equals("SEND")) {
            try {
                if(exitClientButton.isSelected()) {
                    talker.send("EXIT");
                } else if(sendMessageToClientButton.isSelected()) {
                    talker.send("DISPLAY_MESSAGE" + messageField.getText().trim());
                } else if(getMMFilesFromClientButton.isSelected()) {
                        talker.send("GET_MM_FILES");
                        //TODO: Receive list.
                }
            } catch (IOException ioe) {
                System.out.println("Error. Could not send message to client.");
            }
        } else if(ac.equals("EXIT")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = talker.receive();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
