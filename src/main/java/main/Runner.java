package main;

import server.BackendMockHttpServer;

public class Runner {
    
    public static void main(String[] args) {
        Thread t = new Thread(new BackendMockHttpServer(new String[0]));
        t.start();
    }
    
}
