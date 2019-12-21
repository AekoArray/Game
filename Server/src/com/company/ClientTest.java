package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientTest {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost",666);
//            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            int[][] arr1 = (int[][]) in.readObject();
            for (int i = 0; i<arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    System.out.println(arr1[i][j]);
                }
            }
            s.close();
        }catch(Exception e){System.out.println(e);}
    }
}
