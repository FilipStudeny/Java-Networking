package com.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try{
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoString;

            do{
                System.out.println("Enter message to send: ");
                echoString = scanner.nextLine();

                byte[] buffer = echoString.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,5000);
                datagramSocket.send(packet);

                // Client -> receive response from server
                byte[] buffer2 = new byte[50];
                packet = new DatagramPacket(buffer2,buffer2.length);
                datagramSocket.receive(packet);
                System.out.println("Received response: " + new String(buffer2,0,packet.getLength()));

            }while (!echoString.equals("exit"));


        }catch (SocketTimeoutException e){
            System.out.println("Error Socket timeout " + e.getMessage());
        }catch (IOException e){
            System.out.println("Error IO exception " + e.getMessage());
        }


    }
}
