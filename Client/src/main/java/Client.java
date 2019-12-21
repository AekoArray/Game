package main.java;

import main.java.Model.GameEngine;
import main.java.Model.GameObjects.Line;
import main.java.Model.ItemsBuilder;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class Client {
    public void startClient(final GameEngine gameEngine) {
        try {
            final Socket s = new Socket("localhost", 666);
            final ItemsBuilder itemsBuilder = new ItemsBuilder();
            final ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            final int[][] dataFromServer = (int[][]) in.readObject();
            List<List<Line>> lines = itemsBuilder.transformDataFromServerToList(dataFromServer);
            gameEngine.synchronizeLinesWithServer(lines);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
