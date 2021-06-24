package seabattle.shared.loop;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Timer {

    protected Thread thread;
    private volatile boolean running = false;
    long time;

    public Timer(int time) {
        this.time = time;
        this.thread = new Thread(this::run);
    }

    public void start() {
        running = true;
        thread.start();
    }

    private void run() {
        LocalTime lastTime = LocalTime.now();
        while(running) {
            long diff = ChronoUnit.MILLIS.between(lastTime, LocalTime.now());
            if (diff > time) {
                break;
            }
        }
        System.out.println("TIME FINISHED timer run");
    }

}
