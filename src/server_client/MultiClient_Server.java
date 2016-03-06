package server_client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/** Class that has the capabilities to act as a server, constantly accept multiple connections requests from clients, broadcast to those clients, and
 * listen to input from those clients.
 *
 * */

public class MultiClient_Server {

    private ServerSocket serverSocket;
    private LinkedList<Socket> sockets;
    private LinkedList<DataOutputStream> toClientStreams;
    private LinkedList<DataInputStream> fromClientStreams;

    private ServerStatus status;
    private int numberConnections;

    /** Setups the server with a default port number of 50000.
     * */
    public MultiClient_Server() throws IOException{
        status = ServerStatus.RUNNING;
        numberConnections = 0;

        serverSocket = new ServerSocket(50000);

        sockets = new LinkedList<>();
        toClientStreams = new LinkedList<>();
        fromClientStreams = new LinkedList<>();
    }

    /** Setups the server with a specified port number.
     * @param portNumber the port number that the server runs on.
     * */
    public MultiClient_Server(int portNumber) throws IOException{
        status = ServerStatus.RUNNING;
        numberConnections = 0;

        serverSocket = new ServerSocket(portNumber);

        sockets = new LinkedList<>();
        toClientStreams = new LinkedList<>();
        fromClientStreams = new LinkedList<>();
    }

    private enum ServerStatus {RUNNING, SHUTTING_DOWN};

    public static void main(String[] args) throws IOException {

        MultiClient_Server server = new MultiClient_Server(50000);

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

                        sockets.add(clientSocket);
                        toClientStreams.add(new DataOutputStream(clientSocket.getOutputStream()));
                        fromClientStreams.add(new DataInputStream(clientSocket.getInputStream()));

                        numberConnections++;
                    } catch (IOException e) {
                        System.out.println("Exception: " + e.getMessage() + " in acceptConnections().");
                    }
                }
            }
        }.start();
    }

    /** Constantly listens to clients, and broadcasts any messages from the client to all the clients connected to the server.
     * Each client receives their own thread on the server.
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

    /** Inner class thread that is used by handleClients() to handle reading messages from clients and broadcast those messages to all clients.
     * */
    public class HandleClientThread implements Runnable {

        private int clientNumber;

        HandleClientThread(int clientNumber) {
            this.clientNumber = clientNumber;
        }

        @Override
        public void run() {
            try {
                while (status == ServerStatus.RUNNING) {
                    String message = fromClientStreams.get(clientNumber).readUTF();
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
    private void broadcastClientMessage(String message) throws IOException {
        for (int i = 0; i < numberConnections; i++) {
            try {
                toClientStreams.get(i).writeUTF(message);
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage() + " in broadClientMessage().");
                closeClientConnection(i);
            }
        }
    }

    /** Closes a client connection.
     * @param clientNumber the client connection number to be closed.
     * */
    public void closeClientConnection(int clientNumber) throws IOException {
        try {
            toClientStreams.get(clientNumber).close();
            fromClientStreams.get(clientNumber).close();
            sockets.get(clientNumber).close();
            toClientStreams.remove(clientNumber);
            fromClientStreams.remove(clientNumber);
            sockets.get(clientNumber);

            System.out.println("Client connection" + clientNumber + " successfully closed");
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage() + "in closeClientConnection");
        }
    }

    /** Broadcasts a single message from the server to all clients
     * @param message the message to be broadcast.
     * */
    public void broadcastServerMessage(String message) {
        try {
            for (int i = 0; i < numberConnections; i++) {
                toClientStreams.get(i).writeUTF(message);
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
            toClientStreams.get(clientNumber).writeUTF(message);
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage() + " in broadcastToClient().");
        }
    }
}
