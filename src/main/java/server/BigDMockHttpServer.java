package server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import controller.MockController;

public class BigDMockHttpServer implements Runnable {

    private static int port = 8084;
    private static String ip = "127.0.0.1";

    private static MockController controller;

    public static void main(String[] args) {
        if (args.length >= 3) {
            port = Integer.parseInt(args[0]);
            ip = args[1];
        }
        new BigDMockHttpServer().run();
    }

    @Override
    public void run() {
        try {
            controller = MockController.getInstance();

            HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);

            //HTTP Server Routes
            server.createContext("/api", new ServerAPIHandler(controller));

            server.start();
            System.out.println("Java HTTP Server Started ! IP: " + ip + ", PORT: " + port);
        } catch (IOException e) {
            System.out.println("Java HTTP Server Started ! IP: " + ip + ", PORT: " + port
                    + "\n The error is : " + e);
        }
    }
}
