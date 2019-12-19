package main.java.Sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Server {


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int a = in.readInt();
            while (a != 0){
                a = in.readInt();
                System.out.println(a);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
