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
    private int connectionsListeningTo;

    /** Setups the server with a default port number of 50000.
     * */
    public MultiClient_Server() throws IOException{
        status = ServerStatus.RUNNING;
        numberConnections = 0;
        connectionsListeningTo = 0;

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
    public void acceptConnections() {

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
                while (status == ServerStatus.RUNNING) {
                    try {
                        if (connectionsListeningTo < numberConnections) {

                            int clientNumber = connectionsListeningTo;

                            HandleClientThread thread = new HandleClientThread(
                                    clientNumber,
                                    "Anon",
                                    fromClientStreams.get(clientNumber),
                                    toClientStreams.get(clientNumber),
                                    sockets.get(clientNumber));

                            thread.startThread();

                            connectionsListeningTo++;
                        }

                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Exception: " + e.getMessage() + " in handleClients().");
                    }
                }
            }
        }.start();
    }

    /** Inner class thread that is used by handleClients() to handle reading messages from clients and broadcasting those messages to all clients.
     * * */
    public class HandleClientThread implements Runnable {

        private int clientNumber;
        private String clientName;
        private DataInputStream inputStream;
        private DataOutputStream outputStream;
        private Socket socket;

        HandleClientThread(int clientNumber, String clientName, DataInputStream inputStream, DataOutputStream outputStream, Socket socket) {
            this.clientNumber = clientNumber;
            this.clientName = clientName;
            this.inputStream = inputStream;
            this.outputStream = outputStream;
            this.socket = socket;
        }

        public void startThread() {
            Thread aThread = new Thread(this);
            aThread.start();
        }

        @Override
        public void run() {
            handleClient();
        }

        private void handleClient() {

            String message;

            try {
                while (status == ServerStatus.RUNNING) {
                    message = inputStream.readUTF();
                    broadcastClientMessage(message);
                }
            } catch (IOException e) {
                closeClientConnection();
                return;
            }
        }

        private void broadcastClientMessage(String message) {
            for (int i = 0; i < numberConnections; i++) {
                try {
                    toClientStreams.get(i).writeUTF(message);
                } catch (IOException e) {
                    System.out.println("Exception: " + e.getMessage() + " in broadClientMessage().");
                }
            }
        }

        private void closeClientConnection() {
            try {
                outputStream.close();
                inputStream.close();
                socket.close();

                toClientStreams.remove(clientNumber);
                fromClientStreams.remove(clientNumber);
                sockets.get(clientNumber);

                numberConnections--;
                connectionsListeningTo--;

                System.out.println("Client:  " + clientNumber + ", also known as, " + clientName + ", successfully closed");
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage() + "in closeClientConnection");
            }
        }
    }

    /** Shuts down the server, and alerts all clients before it does so.
     * */
    public void shutDown() {

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
