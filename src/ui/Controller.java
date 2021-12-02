package ui;

import client.SocketClient;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextField input;
    @FXML
    public TextArea result;
    @FXML
    private SocketClient client;
    public void search(Event event) {
        client = new SocketClient(result, input.getText());
        client.start();
    }
}
