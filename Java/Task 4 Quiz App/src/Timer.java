import java.util.concurrent.TimeUnit;

public class Timer implements Runnable {
    int duration;
    Thread thread;

    public Timer(int durationInSec){
        this.duration = durationInSec;
    }

    public void startTimer() throws InterruptedException {
        start();
    }

    public void run() {
        try {
            while(duration >= 0) {
                System.out.println(duration);
                TimeUnit.SECONDS.sleep(1);
                --duration;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        if(thread == null) {
            thread = new Thread(this, "testing");
        }
        thread.start();
    }

    public void stopTimer() {
        try{
            thread.interrupt();
        } catch (Exception ex) {
            System.out.println("Thread stopped");
        }
    }

}
