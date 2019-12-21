package main.java;

import javafx.stage.Stage;
import main.java.Controller.BoardController;
import main.java.Model.BoardBuilder;
import main.java.Model.GUIInitializer;
import main.java.Model.GameEngine;
import main.java.Model.GameObjects.Line;
import main.java.Model.ItemsBuilder;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public  void startClient(GameEngine gameEngine) {
        try{
            Socket s=new Socket("localhost",666);
            ItemsBuilder itemsBuilder = new ItemsBuilder();
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            int[][] arr1 = (int[][]) in.readObject();
            List<List<Line>> listLine = new ArrayList<>();
            System.out.println(itemsBuilder.arrToList(arr1, listLine));
            for(List<Line> listLines: listLine){
                for (Line lines : listLines){
                    System.out.println(lines.getFillStatus());
                }
            }
            s.close();
        }catch(Exception e){System.out.println(e);}
    }

}
