package server_client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClient_Server {

    private ServerSocket serverSocket;
    private Socket[] sockets;
    private DataOutputStream[] toClientStreams;
    private DataInputStream[] fromClientStreams;

    private ServerStatus status;
    private int numberConnections;

    MultiClient_Server() throws IOException{
        status = ServerStatus.RUNNING;
        numberConnections = 0;

        serverSocket = new ServerSocket(50000);

        sockets = new Socket[10];
        toClientStreams = new DataOutputStream[10];
        fromClientStreams = new DataInputStream[10];
    }

    MultiClient_Server(int portNumber, int maxConnections) throws IOException{
        status = ServerStatus.RUNNING;
        numberConnections = 0;

        serverSocket = new ServerSocket(50000);

        sockets = new Socket[maxConnections];
        toClientStreams = new DataOutputStream[maxConnections];
        fromClientStreams = new DataInputStream[maxConnections];
    }

    public enum ServerStatus {RUNNING, SHUTTING_DOWN};

    public static void main(String[] args) throws IOException {

        MultiClient_Server server = new MultiClient_Server(50000, 10);

        server.acceptConnections();
        server.broadcastToClients("How are you doing client?");
    }

    /** Accepts all connection requests by starting a new thread that constantly waits for requests.
     * */
    public void acceptConnections() throws IOException {

        new Thread() {
            public void run() {
                while (status == ServerStatus.RUNNING) {
                    try {
                        Socket clientSocket = null;
                        clientSocket = serverSocket.accept();

                        sockets[numberConnections] = clientSocket;
                        toClientStreams[numberConnections] = new DataOutputStream(clientSocket.getOutputStream());
                        fromClientStreams[numberConnections] = new DataInputStream(clientSocket.getInputStream());

                        numberConnections++;
                    } catch (IOException e) {
                        System.out.println("Exception: " + e.getMessage() + " in acceptConnections().");
                    }
                }
            }
        }.start();
    }

    /** Broadcasts to all clients connected to the server.
     * @param message the message to be broadcast.
     * */
    public void broadcastToClients(String message) {

        new Thread() {
            public void run() {
                while (status == ServerStatus.RUNNING) {
                    try {
                        for (int i = 0; i < numberConnections; i++) {
                            toClientStreams[i].writeUTF(message);
                        }
                        Thread.sleep(10000);
                    } catch (IOException e) {
                        System.out.println("Exception: " + e.getMessage() + " in broadcastToClients().");
                    } catch (InterruptedException e) {
                        System.out.println("Exception: " + e.getMessage() + " in broadcastToClients().");
                    }
                }
            }
        }.start();
    }
}
