import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable {

    private Socket server = new Socket();
    public ServerHandler(Socket server){
        this.server = server;
    }

    @Override
    public void run()
    {
        try
        {

            DataInputStream dis = new DataInputStream(server.getInputStream());
            DataOutputStream dos = new DataOutputStream(server.getOutputStream());
            
            // perform IO with server
            while (true)
            {
                String svrMsg = dis.readUTF();
                if(svrMsg.equals("bye"))
                {
                     dos.writeUTF("bye");
                     System.out.println("Ended Session with server");
                     
                     break;
                   
                 }
                 System.out.println("[Server] "+svrMsg);
                 dos.writeUTF("Send starting point");
                 dos.flush();
                 String start = dis.readUTF();
                 System.out.println("[Server] "+start);
                 dos.writeUTF("Send ending point");
                 dos.flush();
                 String end = dis.readUTF();
                 System.out.println("[Server] "+end);
                 // knows points and sends reading
                 dos.writeUTF("345");
                 dos.flush();

            }
            // close connections with sensor
            dis.close();
            dos.close();
            server.close();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }
    }

}
