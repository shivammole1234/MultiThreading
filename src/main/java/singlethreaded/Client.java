
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws IOException {
        int port=8010;
        InetAddress address=InetAddress.getByName("localhost");
        Socket serverSocket=new Socket(address,port);
        PrintWriter toSocket=new PrintWriter(serverSocket.getOutputStream());
        BufferedReader fromSocket=new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        toSocket.println("hello from client");
        System.out.println(fromSocket.readLine());
        String line=fromSocket.readLine();
        System.out.println("response from socket server is :- "+line);
        toSocket.close();
        fromSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client=new Client();
        try{
            client.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
