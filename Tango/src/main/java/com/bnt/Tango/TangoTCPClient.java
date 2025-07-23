package com.bnt.Tango;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TangoTCPClient implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("\n Tango Client Started.. \n");
   

        Socket socket = new Socket("localhost", 9999); // Server IP & Port

        if (socket.isConnected()) {
            System.out.println("Connection to L3 server established!");
            System.out.println("Server IP: " + socket.getInetAddress());
            System.out.println("Server Port: " + socket.getPort());
        } else {
            System.out.println(" Failed to connect to server.");
        }

        Thread.sleep(15000);// wait 15  seconds before send msg

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        
        System.out.println("\n Sending msg to L3 Server...");
          
        writer.println("Hello from Tango!"); // Send message

        System.out.println(" msg  sent to L3 Server");
             

        //msg recinving from server
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = reader.readLine(); 
        System.out.println("\n Received msg from L3 Server: " + message);
    }
}
