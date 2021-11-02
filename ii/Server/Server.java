import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    private static final int port = 5050;
    
    public static void main(String[] args)
    {
        try
        {
            
            ServerSocket listener = new ServerSocket(port);
            System.out.println("Waiting for client connection");
            while (true)
            {
                Socket client = listener.accept();
                System.out.println("Connected to client");
                DataInputStream dis = new DataInputStream(client.getInputStream());
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                
                //perform IO with client
                while (true)
                {
                    dos.writeUTF("Please enter starting point.");
                    dos.flush();
                    String start = dis.readUTF();
                    dos.writeUTF("Please enter ending point.");
                    dos.flush();
                    String end = dis.readUTF();
                    // do calculations and send recommendation
                    dos.writeUTF("Your recommendation is Salah Salem St " +", do you want another one?[y/n]");
                    dos.flush();
                    String clientMsg = dis.readUTF();
                    if(clientMsg.equals("n"))
                    {
                        System.out.println("Ended session with client");
                        dos.writeUTF("bye");
                        dos.flush();
                    break;
                    }
                }
                // close connections
                dis.close();
                dos.close();
                client.close();
            }

            
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
