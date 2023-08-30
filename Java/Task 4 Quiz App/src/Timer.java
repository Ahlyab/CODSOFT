import java.util.concurrent.TimeUnit;

public class Timer implements Runnable {
    private int duration;
    private int _duration;
    public Boolean timeout;
    private Thread thread;

    public Timer(int durationInSec){
        this.duration = durationInSec;
        this.timeout = false;
        this._duration = durationInSec;
    }

    public void startTimer() throws InterruptedException {
        start();
    }

    public void run() {
        try {
            while(duration > 0) {
                System.out.println(duration);
                TimeUnit.SECONDS.sleep(1);
                --duration;
            }
            timeout = true;
        } catch (InterruptedException e) {
            System.out.println("Thread terminated!");
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
            if(!thread.isInterrupted()) {
                thread.interrupt();
            }
        } catch (Exception ex) {
            System.out.println("Thread stopped");
        }
    }

    public void reset() {
        this.stopTimer();
        this.duration = this._duration;
    }

}
