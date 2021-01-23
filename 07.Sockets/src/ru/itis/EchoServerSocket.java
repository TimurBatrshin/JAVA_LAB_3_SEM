package ru.itis;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSocket {

    public void start(int port) {
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            Socket client = server.accept();

            BufferedReader fromClient = new BufferedReader(new InputStreamReader( client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);

            String messageFromClient = fromClient.readLine();
            while (messageFromClient != null) {
                System.out.println("Message from client: " + messageFromClient);
                toClient.println("From server: " + messageFromClient);
                messageFromClient = fromClient.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
