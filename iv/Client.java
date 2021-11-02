
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client
{

    private static final String ServerIP = "127.0.0.1";
    private static final int ServerPort = 5050;
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            
            Socket socket = new Socket(ServerIP, ServerPort);
           
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            // prform IO with client
            while (true)
            {
                
                String serverMsg = dis.readUTF();                
                if(serverMsg.equals("bye"))
                {
                    System.out.println("Session ended");
                    break;
                }
                System.out.println("[Server] "+serverMsg);
               
                String userMsg = sc.next();
                dos.writeUTF(userMsg);
                dos.flush();
            }
          // close connection with server
            dis.close();
            dos.close();
            socket.close();
            
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }   
}