import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // Create an HTTP server instance
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // Set up a handler for the root path
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "Hello, World!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                
                // Write the response to the output stream
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        
        // Set the server to run indefinitely
        server.setExecutor(null); // Uses the default executor
        System.out.println("Server is running on http://localhost:8000/");
        server.start();
    }
}
