//package multithreaded;//package main.java.multithreaded;
package main.java.multithreaded;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable(){
        
        return new Runnable() {
            int serverCnt=0;
            @Override
            public void run(){
                int port=8010;
                try{
                    InetAddress address=InetAddress.getLocalHost();
                    Socket socket=new Socket(address,port);
                    try{
                        PrintWriter toServer=new PrintWriter(socket.getOutputStream()); // sending the msg to server from client by creating the output stream 
                        BufferedReader fromServer= new BufferedReader(new InputStreamReader(socket.getInputStream())); // reading the msg from server 

                        toServer.println("hello from client to server "+serverCnt);
                        toServer.flush();
                        serverCnt++;
                        String lineFromServer=fromServer.readLine();
                        System.out.println("the msg from server is :- "+lineFromServer);
                        
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Error in getting the input stream");
                    }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
    }
    
    public static void main(String[] args) {
        Client client=new Client();

        for(int i=0;i<200;i++)
        {
            try{
                Thread thread=new Thread(client.getRunnable());
                thread.start();

            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
