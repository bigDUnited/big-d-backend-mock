package server;

public class MyRunner {
    
    public static void main(String[] args) {
        Thread t = new Thread(new BackendMockHttpServer(new String[0]));
        t.start();
    }
    
}
