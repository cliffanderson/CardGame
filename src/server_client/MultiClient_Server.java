package server_client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClient_Server {

    private static int connectionNum = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(50000);
        Socket clientSocket = null;
        Socket[] sockets = new Socket[10];

        while (true) {
            clientSocket = serverSocket.accept();

            new ClientConnection();
        }
    }
}
