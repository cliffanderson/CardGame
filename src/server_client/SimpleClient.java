package server_client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SimpleClient{

    public static void main(String[] args) throws Exception {
        final Socket socket;
        String hostName = "localhost";
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

                while (socket.isConnected()) {
                    try {
                        output = clientKeyboard.readLine();
                        outputStream.writeUTF(output);
                    } catch (SocketException e) {
                        System.out.println(e.getMessage() + " - Ending application due to server being down.");
                        System.exit(-1);
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

                while (socket.isConnected()) {
                    try {
                        input = inputStream.readUTF();
                        System.out.println(input);
                    } catch (SocketException e) {
                        System.out.println(e.getMessage() + " - Ending application due to server being down.");
                        System.exit(-1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }.start();
    }
}