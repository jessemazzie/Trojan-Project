import javax.swing.*;

/**
 * This class acts as a modular application piece that takes in a string which corresponds to an action.
 */
public class CommandHandler {

    CommandHandler() {

    }

    static void parseFileSystem() {}

    public static void performCommand(String command) {
        command = command.trim();
        if(command.equals("EXIT")) {
            System.exit(0);
        } else if(command.equals("GET_MM_FILES")) {
            parseFileSystem();
        } else if(command.equals("DISPLAY_MESSAGE")) {

        }
    }
}
