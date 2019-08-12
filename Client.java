
/*
 *A java program for a Client
 * @author Emmanuel Ogbewe
 */
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;
import java.io.*;


public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Client(String address, int port){
        //establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            //takes input from terminal
            input = new DataInputStream(System.in);

            //sends output to the socket
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u.getMessage());

        }
        catch(IOException i)
        {
            System.out.println(i.getLocalizedMessage());
        }
        //string to read message from input
        String line = "";

        //keep reading until "Over" is input
        while(!line.equals("Over"))
        {
            try
            {
                line = input.readUTF();
                output.writeUTF(line);
            }
            catch (IOException i)
            {
                System.out.println(i.getMessage());
            }

        }

        //close the connection
        try
        {
            input.close();
            output.close();
            socket.close();
        }
        catch(IOException i )
        {
            System.out.println(i.getMessage());
        }
    }

    public static void main (String args[]){
        Client client = new Client("127.0.0.1", 5000);
    }



}
