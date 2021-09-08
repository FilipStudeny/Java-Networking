package com.TCPServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{

    private Socket socket;
    TCPServerMultithread serverMultithread;

    public  ServerThread(Socket socket,TCPServerMultithread serverMultithread){
        this.socket = socket;
        this.serverMultithread = serverMultithread;
    }

    @Override
    public void run(){
        try{
            int clientNumber = serverMultithread.getClientCounter();
            System.out.println("New client " + clientNumber + "has connected");

            //Input/Output buffers
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Read incoming data from client
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); //Send response back to the client

            printWriter.println("Welcome ! "); //Send response to the client
            printWriter.println("Write your name ");

            String message = bufferedReader.readLine();
            System.out.println("Client "+ clientNumber + socket.getInetAddress() + " says: " + message); //Display message from client

            socket.close();
            System.out.println("***** Client " + clientNumber + " has disconected *****");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error ----> " + e.getMessage());
        }
    }


}
