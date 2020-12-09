package tp.checkers.client;

import tp.checkers.message.MessageColor;
import tp.checkers.message.MessageFields;
import tp.checkers.message.MessageIfHost;
import tp.checkers.server.game.Field;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client {

    private Window window;
    private Socket socket = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    public ObjectInputStream objectInputStream = null;
    public ObjectOutputStream objectOutputStream = null;
    private Field[][] fields = null;
    private Color color = null;

    Client() {
        System.out.println("CLIENT: started");
        try {
            socket = new Socket("localhost", 4444);

            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

            System.out.println("CLIENT: connected");
        } catch (IOException e) {
            System.out.println("Error: can't connect to the server.");
            System.out.println("Make sure the server is working and it's not full");
            return;
        }

        this.window = new Window(this);
        window.setVisible(true);

        try {
            MessageIfHost msg = (MessageIfHost) objectInputStream.readObject();
            boolean host = msg.host;
            if(host) {
                objectOutputStream.writeObject(window.initGameData());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        initBoard();
    }

    private void initBoard() {
        try {
            MessageFields msg = (MessageFields) objectInputStream.readObject();
            fields = msg.fields;
            MessageColor msgc = (MessageColor) objectInputStream.readObject();
            color = msgc.color;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        window.initBoard(fields);
    }

    private void close() {
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
