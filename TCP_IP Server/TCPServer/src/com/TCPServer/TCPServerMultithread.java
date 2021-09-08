package com.TCPServer;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerMultithread {

    public TCPServerMultithread() throws Exception{

        int PORT = 8000;
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("PORT " + PORT + " ----- OPEN");

        while (true){
            Socket clientSocket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(clientSocket, this);
            Thread thread = new Thread(serverThread); //Create new thread
            thread.start(); // Start new thread
        }
    }

    private int clientCounter = 1;

    public int getClientCounter(){
        return clientCounter++;
    }

    public static void main(String[] args) {


        try {
            new TCPServerMultithread();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error ---> " + e.getMessage());

        }
    }
}
