package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DotsAndBoxesServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(666);
            Socket client = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            int[][] arr = {
                    {0, 0, 0},
                    {1, 0, 0, 0},
                    {0, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0, 0}

            };
            out.writeObject(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
