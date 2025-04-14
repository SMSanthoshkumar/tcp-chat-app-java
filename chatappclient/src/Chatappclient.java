//This is chat application
//first run server (chatappserver) and then run client (chatappclient)

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Chatappclient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Connected as Mounesh");

        Thread receive = new Thread(() -> {
            try {
                while(true) {
                    String msg = in.readUTF();
                    System.out.println("Santhosh: " + msg);
                }
            }catch(IOException e) {
                System.out.println("Server disconnected.");
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
