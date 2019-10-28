
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        // luodaan palvelin porttiin 8080
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            // odotetaan pyyntöä
            Socket socket = server.accept();

            // luetaan pyyntö
            Scanner lukija = new Scanner(socket.getInputStream());
            if (lukija.nextLine().contains("/quit")) {
                break;
            }

            // kirjoitetaan vastaus
            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 302 Found\n"
                    + "Location: http://localhost:8080");
            Files.lines(Paths.get("index.html")).forEach(line -> kirjoittaja.println(line));
            kirjoittaja.flush();

            // vapautetaan resurssit
            lukija.close();
            kirjoittaja.close();
            socket.close();

        }
        server.close();
    }

}
