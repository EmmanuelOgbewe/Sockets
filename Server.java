
/*
 *A java program for a Server
 * @author Emmanuel Ogbewe
 */
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;
import java.io.*;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;


    //constructor with port

    public Server(int port){
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();

            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            //reads message from client until "Over" is sent

            while(!line.equals("Over")){
                try
                {
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException i)
                {
                    System.out.println(i.getMessage());
                }
            }
            System.out.println("Closing connection");

            //close connection
            socket.close();
            in.close();

        }
        catch(IOException i )
        {
            System.out.println(i.getMessage());
        }
    }
    public static void main(String args[]){
        Server server = new Server(5000);
    }
}
