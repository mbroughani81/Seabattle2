package seabattle.shared.loop;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Timer {

    protected Thread thread;
    private volatile boolean running = false;
    private LocalTime startTime;
    long time;

    public Timer(long time) {
        this.time = time;
        this.thread = new Thread(this::run);
    }

    public void start() {
        running = true;
        startTime = LocalTime.now();
        thread.start();
    }

    private void run() {
        while(running) {
            if (isFinished()) {
                break;
            }
        }
        System.out.println("TIME FINISHED timer run");
    }

    private long getDiff(LocalTime time) {
        return Math.abs(ChronoUnit.MILLIS.between(time, startTime));
    }

    public boolean isFinished() {
        return getDiff(LocalTime.now()) > time;
    }

    public int getRemainingSec() {
        return (int)time/1000 - (int)getDiff(LocalTime.now())/1000;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
