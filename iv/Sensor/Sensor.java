
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Sensor
 */
public class Sensor {

    private static final int port = 4999;
    public static void main(String[] args) {
        try
        {
            
            ServerSocket sensor = new ServerSocket(port);
            System.out.println("Waiting for server connection");
            while (true)
            {
                Socket server = sensor.accept();
                System.out.println("Connected to server");
                ServerHandler sh = new ServerHandler(server);
                Thread th = new Thread(sh);
                th.start();

            
            }
            

        }
        
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

