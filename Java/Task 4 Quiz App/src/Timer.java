import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Timer implements Runnable {
    private int duration;
    private int _duration;
    public Boolean timeout;
    private Thread thread;
    private JLabel time;

    public Timer(int durationInSec, JLabel time){
        this.duration = durationInSec;
        this.timeout = false;
        this._duration = durationInSec;
        this.time = time;
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
        } catch (InterruptedException e) {
            System.out.println("Thread terminated!");
        }

    }

    public void start() {
        if(thread == null) {
            thread = new Thread(this, "testing");
        }
        if(thread.getState() == Thread.State.WAITING){
            thread.notify();
            return;
        }
        if(thread.getState() != Thread.State.RUNNABLE) {
            thread.start();

        }
    }

    public void stopTimer() {
//        try{
//            if(!thread.isInterrupted()) {
////                thread.interrupt();
//                thread.wait();
//
//            }
//        } catch (Exception ex) {
//            System.out.println("Thread stopped");
//        }
        try {
            thread.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() {
//        this.stopTimer();
        this.duration = this._duration;
    }

}
