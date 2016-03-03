package server_client;

public class ClientConnection implements Runnable {

    private static int connectionNum = 0;

    @Override
    public void run() {

        connectionNum++;

        while (true) {
            System.out.println(connectionNum);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Connection: " + connectionNum + " can't sleep");
            }
        }
    }

    public void startConnection() {
        Thread thread = new Thread(this);
        thread.start();
    }
}