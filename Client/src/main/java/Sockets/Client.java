package main.java.Sockets;

import javafx.stage.Stage;
import main.java.Controller.BoardController;
import main.java.Model.BoardBuilder;
import main.java.Model.GUIInitializer;
import main.java.Model.GameEngine;

import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost",6666);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            GUIInitializer guiInitializer = new GUIInitializer();
            Stage stage = new Stage();
            GameEngine gameEngine = new GameEngine(guiInitializer, stage);

//            int b = System.in.read();
//            while(b != 0) {
//                dout.writeInt(b);
//                b = System.in.read();
//            }
//            dout.flush();
//            dout.close();
//            s.close();
        }catch(Exception e){System.out.println(e);}
    }
}
