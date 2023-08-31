import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Timer implements Runnable {
    private int duration;
    private int _duration;
    public Boolean timeout;
    private Thread thread;
    private JLabel time;
    private JButton submit;

    public Timer(int durationInSec, JLabel time, JButton submit){
        this.duration = durationInSec;
        this.timeout = false;
        this._duration = durationInSec;
        this.time = time;
        this.submit = submit;
    }

    public void startTimer() throws InterruptedException {
        start();
    }

    public void run() {
        try {
            while(duration > 0) {
                this.time.setText("left : " + duration);
                TimeUnit.SECONDS.sleep(1);
                --duration;
            }
            timeout = true;
            if(timeout) {
                submit.doClick();
            }
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
//                thread.wait();

            }
        } catch (Exception ex) {
            System.out.println("Thread stopped");
        }
//        try {
//            thread.wait();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void reset() {
        this.stopTimer();
        this.duration = this._duration;
        this.thread = new Thread(this, "Testing");
        this.timeout = false;
    }

}
