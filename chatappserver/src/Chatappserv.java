//This is chat application
//first run server (chatappserver) and then run client (chatappclient)

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Chatappserv {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12000);
        System.out.println("Server started... waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        Thread receive = new Thread(() -> {
            try {
                while(true) {
                    String msg = in.readUTF();
                    System.out.println("Mounesh: " + msg);
                }
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        });

        receive.setDaemon(true);
        receive.start();

        while(true) {
            System.out.print("You: ");
            String message = scanner.nextLine();
            out.writeUTF(message);
        }
    }
}
