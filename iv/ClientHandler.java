import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class ClientHandler implements Runnable {

    private Socket client;
    public ClientHandler(Socket client){
        this.client = client;
    }

    @Override
    public void run()
    {
        
        String SensorIP = "127.0.0.1";
        int Sensorport = 4999;

        try
        {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            // sockets and streams for sensor
            Socket sensor = new Socket(SensorIP,Sensorport);
            DataInputStream sis = new DataInputStream(sensor.getInputStream());
            DataOutputStream sos = new DataOutputStream(sensor.getOutputStream());
            
            while (true)
            {
                // take information from client
                dos.writeUTF("Please enter starting point.");
                dos.flush();
                String start = dis.readUTF();
                dos.writeUTF("Please enter ending point.");
                dos.flush();
                String end = dis.readUTF();
                // takes start and end and know the suitable sensor to ask for input
                // ask sensor for input
                
                sos.writeUTF("Get me a reading");
                sos.flush();
                sis.readUTF();
                sos.writeUTF(start);
                sos.flush();
                sis.readUTF();
                sos.writeUTF(end);
                sos.flush();
                String reading = sis.readUTF();
                System.out.println("[sensor] "+reading);
                // do calculations on input
                String recom = getRecommendation(reading);
                // sen client recommendation
                dos.writeUTF("Your recommendation is "+recom + ", do you want another one? [y/n]");
                dos.flush();
                String clientMsg = dis.readUTF();
                if(clientMsg.equals("n"))
                {
                    System.out.println("Ended session with client");
                    dos.writeUTF("bye");
                    dos.flush();
                    System.out.println("Session ended with sensor");
                    sos.writeUTF("bye");
                    sos.flush();
                    break;
                }
                
            }
            // close connections with sensor
            sis.close();
            sos.close();
            sensor.close();
            // close connections with client
            dis.close();
            dos.close();
            client.close();
           
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }

    }
    
    public String getRecommendation(String reading)
    {
        // does calculations on sensor input and return recommendation
        return("Salah Salem St");
    }
}
