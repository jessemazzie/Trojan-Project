import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Queue;

/**
 * This class acts as a modular application piece that takes in a string which corresponds to an action.
 */
public class CommandHandler implements Runnable, ActionListener {
    Talker talker;
    CommandHandler() {
        Thread chThread;
        chThread = new Thread(this);
        chThread.start();
    }

    void parseFileSystem() {
        Queue<File> foldersToVisit;
    }

    /**
     * Displays a JOptionPane with a message given from the server.
     * @param messageToDisplay
     */
    void displayMessage(String messageToDisplay) {
        JOptionPane.showMessageDialog(null,  messageToDisplay, "Alert", JOptionPane.INFORMATION_MESSAGE);
    }

    void performCommand(String command) {
        command = command.trim(); //trim whitespace

        if(command.equals("EXIT")) {
            System.exit(0);
        } else if(command.equals("GET_MM_FILES")) {
            parseFileSystem();
        } else if(command.startsWith("DISPLAY_MESSAGE")) {
            command = command.substring(15); //This leaves us with the string to display by trimming off "DISPLAY_MESSAGE"
            displayMessage(command);
        }
    }

    @Override
    public void run() {
        boolean isConnected = false;

        while(true) {
            if(isConnected) {
                String msg;

                try {
                    msg = talker.receive();
                    performCommand(msg);
                    System.out.println("Message in client: " + msg);
                } catch(IOException ioe) {
                    //nothing needs done here, this just means no message is available.
                }
            } else {
                try {
                    talker = new Talker("127.0.0.1", 555, "CLIENT");
                    System.out.println("Talker successfully connected in client.");
                    isConnected = true;
                } catch (IOException ioe) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("Reconnection pause in client has been interrupted");
                    }
                    System.out.println("Connection failed. Retrying...");
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
