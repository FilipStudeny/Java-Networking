package com.TCPClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        try{
            int PORT = 8000;
            Socket socket = new Socket("localhost",PORT);

            System.out.println("***** Connected to the server *****");

            //Input/Output buffers
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Read incoming data from server
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); //Send response back to the server

            String message = bufferedReader.readLine();
            System.out.println("Server response: " + message);

            Scanner clientInput = new Scanner(System.in);
            System.out.println("Type message to send: ");
            message = clientInput.nextLine();

            printWriter.println(message); // Send message to the server

            String guessedNumber;
            while ((bufferedReader.readLine().startsWith("Guess"))){
                System.out.println("Server says guess a number ---> 0 - 10");
                guessedNumber = clientInput.nextLine();

                printWriter.println(guessedNumber);
            }


            socket.close();
            System.out.println("***** Disconnected from server ! *****");



        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error ---> " + e.getMessage());
        }
    }
}
