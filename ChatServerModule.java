/*
    The ChatServerModule class sets up and runs a simple
    chat server module on port 5320.
*/
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerModule {
 
    ArrayList clientOutputStreams;
    
    // Inner class ClientHandler will read and write to clients
    public class ClientHandler implements Runnable {
        // Use a buffered reader for improved efficiency
        BufferedReader reader;
        Socket socket;
        
        public ClientHandler(Socket clientSocket) {
            try {
                // Attach connection stream and chain stream to socket
                socket = clientSocket;
                InputStreamReader inReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(inReader);
            }
            // Error handling is nothing fancy, just printStackTrace
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // Implement run from Runnable interface to fulfill its contract
        public void run() {
            
            String chatMessage;
            try {
                while ((chatMessage = reader.readLine()) != null) {
                    System.out.println("read " + chatMessage);
                    deliver(chatMessage);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[]args) {
        new ChatServerModule().go();
    }
    
    public void go() {
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(5320);
            
            // Keep program running in perpetuity
            while(true) {
                Socket clientSocket = serverSocket.accept();
                // A PrintWriter writer will write to socket
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);
                
                // Start a new thread to connect to clients
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Connection successful");
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Deliver a chat message to clients
    public void deliver(String aMessage) {
        
        // Iterate over clientOutputStreams
        Iterator iter = clientOutputStreams.iterator();
        while (iter.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) iter.next();
                writer.println(aMessage);
                writer.flush();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
