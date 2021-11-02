
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
                ClientHandler ch = new ClientHandler(client);
                Thread t = new Thread(ch);
                t.start();
            }

            
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
