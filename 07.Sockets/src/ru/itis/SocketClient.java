package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private Socket client;

    private PrintWriter toServer;//на сервер
    private BufferedReader fromServer;

    public SocketClient(String host, int port) {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            new Thread(receiverMessageTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toServer.println(message);
    }

    private Runnable receiverMessageTask = () -> {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    };
}
