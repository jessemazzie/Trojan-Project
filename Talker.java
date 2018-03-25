import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Talker {
    private String ID;
    private BufferedReader reader;
    private DataOutputStream dos;

    Talker(String domain, int portNum, String ID) throws IOException {
        Socket s;

        s = new Socket(domain, portNum);

        reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        dos = new DataOutputStream(s.getOutputStream());
        this.ID = ID;
    }

    Talker(Socket s) throws IOException {

        reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        dos = new DataOutputStream(s.getOutputStream());
    }

    public void send(String message) throws IOException {
        dos.writeBytes(message + "\n");
        System.out.println("From: " + ID + "\nMessage: " + message + "\n");
    }

    public String receive() throws IOException {
        return reader.readLine();
    }

    /**
     * Simple setter for ID.
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
}
