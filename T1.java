import java.util.*;

public class T1 implements Runnable {
    Thread t;

    public void run() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.printf("");
            return;
        }
        System.out.println("TIMEOUT!!");
        System.exit(0);
    }
}