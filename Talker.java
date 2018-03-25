import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Talker {
    private String ID;
    private BufferedReader reader;
    private DataOutputStream dos;

    Talker(String domain, int portNum, String ID) throws IOException {
        Socket s;

        s = new Socket(domain, portNum);
        //TODO: Assign reader
        //TODO: Assign dos
        this.ID = ID;
    }

    Talker(Socket s) {
        //TODO: Assign reader
        //TODO: Assign dos
    }

    /**
     * Simple setter for ID.
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
}
