package ru.itis;

import java.util.Scanner;

public class MainForClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient("localhost", 777);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }
}
