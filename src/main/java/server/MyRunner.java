package server;

/**
 *
 * @author Marek
 */
public class MyRunner {

    public static void main(String[] args) {
        Thread t = new Thread(new BackendMockHttpServer());
        t.start();
    }

}
