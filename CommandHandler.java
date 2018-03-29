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
    CommandHandler(Talker talker) {
        this.talker = talker;
    }

    static void parseFileSystem() {
        Queue<File> foldersToVisit;
    }

    public static void performCommand(String command) {
        command = command.trim();
        if(command.equals("EXIT")) {
            System.exit(0);
        } else if(command.equals("GET_MM_FILES")) {
            parseFileSystem();
        } else if(command.equals("DISPLAY_MESSAGE")) {

        }
    }

    @Override
    public void run() {
        try {
            System.out.println(talker.receive());
            while(true) {
                String msg;
                msg = talker.receive();
            }
        } catch (IOException e) {
            System.out.println("No message available.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
