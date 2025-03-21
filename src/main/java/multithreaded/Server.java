//package multithreaded;
package main.java.multithreaded;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {


    public Consumer<Socket> getConsumer(){
        return(clientSocket)->{
            int clientMsgCnt=0;
            try{ 
                PrintWriter toClient=new PrintWriter(clientSocket.getOutputStream());
                toClient.println("hello from the server "+clientMsgCnt+" to client ");
                clientMsgCnt++;
                toClient.flush();
                // toClient.close();
                // clientSocket.close();

            }catch (java.net.SocketTimeoutException e) {
                System.out.println("No client connected within the timeout period. Retrying...");
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }
    
    public static void main(String[] args) throws IOException {
    
        Server server = new Server();

        int port=8010;
        ServerSocket serverSocket = new ServerSocket(port);
        //serverSocket.setSoTimeout(10000);
        System.out.println("server is listining on port :- "+port);
        
        while (true) {
            Socket acceptedSocket=serverSocket.accept();
            Thread thread=new Thread(()->server.getConsumer().accept(acceptedSocket));
            thread.start();
            //serverSocket.close();
        }
    }
}
