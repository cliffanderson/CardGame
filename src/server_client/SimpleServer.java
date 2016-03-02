package server_client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        int portNumber;

        if (args.length == 0) {
            serverSocket = new ServerSocket(50000);
        } else {
            portNumber = Integer.parseInt(args[0]);
            serverSocket = new ServerSocket(portNumber);
        }

        Socket clientSocket = serverSocket.accept();

        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

        BufferedReader serverKeyboard = new BufferedReader(new InputStreamReader(System.in));

        //Thread for output to client.
        new Thread() {
            public void run() {
                String output = null;

                while (true) {
                    try {
                        output = serverKeyboard.readLine();
                        outputStream.writeUTF("Server said: " + output);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();

        //Thread for input from client.
        new Thread() {
            public void run() {
                String input = null;

                while (true) {
                    try {
                        input = inputStream.readUTF();
                        System.out.println("Client said: " + input);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();
    }
}