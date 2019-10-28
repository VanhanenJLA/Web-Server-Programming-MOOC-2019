
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Scanner socketScanner;
        Socket socket;

        System.out.println("================\n"
                + "THE INTERNETS!\n"
                + "================\n"
                + "Where to?");

        String site = scanner.nextLine();
        int port = 80;

        try {
            socket = new Socket(site, port);
            
                    socketScanner = new Scanner(socket.getInputStream());

        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("GET / HTTP/1.1");
        writer.println("Host: " + site);
        writer.println();
        writer.flush();

        System.out.println("==========\n"
                + "RESPONSE\n"
                + "==========");

        while (socketScanner.hasNextLine()) {
            System.out.println(socketScanner.nextLine());
        }
        
        } catch (IOException e) {
            throw e;
        }
        
        
        


    }
}
