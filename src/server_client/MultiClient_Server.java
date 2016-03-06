package server_client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/** Class that has the capabilities to act as a server, constantly accept multiple connections requests from clients, broadcast to those clients, and
 * listen to input from those clients.
 *
 * */

public class MultiClient_Server {

    private ServerSocket serverSocket;
    private Socket[] sockets;
    private DataOutputStream[] toClientStreams;
    private DataInputStream[] fromClientStreams;

    private ServerStatus status;
    private int numberConnections;

    /** Setups the server with a default port number of 50000, and a max number of connections equal to 10.
     * */
    public MultiClient_Server() throws IOException{
        status = ServerStatus.RUNNING;
        numberConnections = 0;

        serverSocket = new ServerSocket(50000);

        sockets = new Socket[10];
        toClientStreams = new DataOutputStream[10];
        fromClientStreams = new DataInputStream[10];
    }

    /** Setups the server with a specified port number and a max number of connections possible.
     * @param portNumber the port number that the server runs on.
     * @param maxConnections the max number of connections possible for this server.
     * */
    public MultiClient_Server(int portNumber, int maxConnections) throws IOException{
        status = ServerStatus.RUNNING;
        numberConnections = 0;

        serverSocket = new ServerSocket(portNumber);

        sockets = new Socket[maxConnections];
        toClientStreams = new DataOutputStream[maxConnections];
        fromClientStreams = new DataInputStream[maxConnections];
    }

    private enum ServerStatus {RUNNING, SHUTTING_DOWN};

    public static void main(String[] args) throws IOException {

        MultiClient_Server server = new MultiClient_Server(50000, 10);

        server.acceptConnections();
        server.handleClients();
    }

    /** Constantly accepts all connection requests by starting a new thread that waits for requests and accepts them.
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

    /** Constantly listens to clients, and broadcasts any messages from the client to all the clients connected to the server.
     * */
    public void handleClients() {

        new Thread() {
            public void run() {
                int connectionsListeningTo = 0;

                while (status == ServerStatus.RUNNING) {
                    try {
                        if (connectionsListeningTo < numberConnections) {
                            int clientNumber = connectionsListeningTo;
                            HandleClientThread thread = new HandleClientThread(clientNumber);
                            thread.startThread();
                            connectionsListeningTo++;
                        }

                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Exception: " + e.getMessage() + " in listenToClients().");
                    }
                }
            }
        }.start();
    }

    public class HandleClientThread implements Runnable {

        private int clientNumber;

        HandleClientThread(int clientNumber) {
            this.clientNumber = clientNumber;
        }

        @Override
        public void run() {
            try {
                while (status == ServerStatus.RUNNING) {
                    String message = fromClientStreams[clientNumber].readUTF();
                    broadcastClientMessage(message);
                    //System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage() + " in listenToClients().");
            }
        }

        public void startThread() {
            Thread aThread = new Thread(this);
            aThread.start();
        }
    }

    /** Broadcasts a client message once to every client connected to the server.
     * */
    private void broadcastClientMessage(String message) {
        for (int i = 0; i < numberConnections; i++) {
            try {
                toClientStreams[i].writeUTF(message);
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage() + " in broadClientMessage().");
            }
        }
    }

    /** Broadcasts a single message from the server to all clients
     * @param message the message to be broadcast.
     * */
    public void broadcastServerMessage(String message) {
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

    /** Sends a message once to a specific client.
     * @param clientNumber the specific client to broadcast to.
     * @param message the message to be sent to the client.
     * */
    public void sendToClient(String message, int clientNumber) {
        try {
            toClientStreams[clientNumber].writeUTF(message);
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage() + " in broadcastToClient().");
        }
    }
}
