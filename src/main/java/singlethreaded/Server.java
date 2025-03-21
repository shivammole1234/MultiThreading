 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port=8010;
        ServerSocket socket=new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true){
            try {
                System.out.println("waiting for connection");
                Socket acceptedConnection=socket.accept();
                System.out.println("connection accepted from client " +acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("hellow from server 1");
                toClient.println("hello from server 2");
                toClient.flush();
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            }catch (Exception e)
            {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Server server =new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
