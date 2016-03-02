package server_client;

import java.io.*;
import java.net.Socket;

public class SimpleClient{

    public static void main(String[] args) throws Exception {
        Socket socket = null;
        String hostName = "10.200.26.120";
        int portNumber = 50000;

        if (args.length == 0) {
            socket = new Socket(hostName, portNumber);
        } else {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
            socket = new Socket(hostName, portNumber);
        }

        final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        final DataInputStream inputStream = new DataInputStream(socket.getInputStream());

        final BufferedReader clientKeyboard = new BufferedReader(new InputStreamReader(System.in));

        //Thread for output to server.
        new Thread() {
            public void run() {
                String output = null;

                while (true) {
                    try {
                        output = clientKeyboard.readLine();
                        outputStream.writeUTF(output);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();

        //Thread for getting input from server.
        new Thread() {
            public void run() {
                String input = null;

                while (true) {
                    try {
                        input = inputStream.readUTF();
                        System.out.println(input);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();
    }
}