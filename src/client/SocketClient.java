package client;

import javafx.scene.control.TextArea;
import parser.BreakfastMenu;
import parser.DataProcessor;
import java.io.*;
import java.net.Socket;

public class SocketClient extends Thread {
    private TextArea textArea;
    private String serverAddress = "127.0.01";
    private static final int PORT = 8585;
    private String input;
    private DataProcessor dataProcessor;

    public SocketClient(TextArea textArea, String input) {
       this.textArea = textArea;
       this.input = input;
       dataProcessor = new DataProcessor();
    }

    @Override
    public void run() {
        try {
            connect();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void connect() throws IOException{
        try (Socket clientSocket = new Socket(serverAddress, PORT)) {
            //Send request to the server
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(this.input);
            //Receive the response from the server
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader input = new BufferedReader(inputStreamReader);
            textArea.appendText("Server response:\n");
            //Convert XML string into BreakfastMenu object
            BreakfastMenu breakfastMenu = dataProcessor.getBreakfastMenu(input.readLine());
            textArea.appendText(breakfastMenu.toString()+"\n");
        }
    }
}
