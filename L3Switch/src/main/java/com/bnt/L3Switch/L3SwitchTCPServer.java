package com.bnt.L3Switch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class L3SwitchTCPServer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println(" L3 Switch  Server started on port 9999...\n ");
        System.out.println(" L3 Switch Server Waiting for Clients... \n");

        Socket clientSocket = serverSocket.accept();

        if (clientSocket.isConnected()) {
            System.out.println("Connection Esatblished with client..!  ");
            System.out.println("Client IP: " + clientSocket.getInetAddress());
            System.out.println("Client Port: " + clientSocket.getPort());
        } else {
            System.out.println(" Client connection failed!");
        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message = reader.readLine(); 
        System.out.println("\n Received msg from Tango Client: " + message);

        

        //sending msg back to client
        Thread.sleep(10000);
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        writer.println("Hi from  L3 Server!");

        System.out.println("\n Msg sent from L3 server " );



        
      
    }
}
