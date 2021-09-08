package com.TCPServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        try {
            int PORT = 8000;
            ServerSocket serverSocket = new ServerSocket(PORT); // Open server on this port
            System.out.println("***** Port " + PORT + " --- OPEN *****");

            Socket socket = serverSocket.accept(); // Accept incoming connections
            System.out.println("Client " + socket.getInetAddress() + " has connected");

            //Input/Output buffers
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Read incoming data from client
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); //Send response back to the client

            printWriter.println("Welcome !"); //Send response to the client
            String message = bufferedReader.readLine();

            System.out.println("Client " + socket.getInetAddress() + " says: " + message); //Display message from client

            /*
            //Random number guesser
            int randomNumber = (int)(Math.random()*10 + 1); // 0 - 10

            do{
                printWriter.println("Guess number --> 0 - 10");
                message = bufferedReader.readLine();

            }while ( !(Integer.parseInt(message) == randomNumber));

            printWriter.println("CORRECT");
            System.out.println("Random number is " + randomNumber);
           */

            socket.close();
            System.out.println("***** Server is closed *****");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error ---> " + e.getMessage());
        }
    }
}
