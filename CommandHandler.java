import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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

    static void parseFileSystem() {
        Queue<File> foldersToVisit;
    }

    public static void performCommand(String command) {
        command = command.trim(); //trim whitespace

        if(command.equals("EXIT")) {
            System.exit(0);
        } else if(command.equals("GET_MM_FILES")) {
            parseFileSystem();
        } else if(command.startsWith("DISPLAY_MESSAGE")) {
            command = command.substring(15); //This leaves us with the string to display by trimming off "DISPLAY_MESSAGE"

        }
    }

    @Override
    public void run() {
        boolean isConnected = false;

        try {
            while(true) {
                if(isConnected) {
                    String msg;
                    msg = talker.receive();
                    System.out.println("Message in client: " + msg);
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
                        ioe.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No message available.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
