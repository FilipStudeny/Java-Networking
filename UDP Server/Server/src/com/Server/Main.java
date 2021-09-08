package com.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        try{
            DatagramSocket socket = new DatagramSocket(5000);

            while (true){
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                socket.receive(packet);

                System.out.println("Text received ----> " + new String(buffer,0,packet.getLength()));

                //Server -> send response to the server
                String returnString = "echo: " + new String(buffer,0,packet.getLength());

                byte[] bufffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                packet = new DatagramPacket(bufffer2,bufffer2.length,address,port);
                socket.send(packet);

            }
        }catch (SocketException e){
            System.out.println("Error Socket exception: " + e.getMessage());
        }catch (IOException e){
            System.out.println("Error IO exception: " + e.getMessage());

        }

    }
}
