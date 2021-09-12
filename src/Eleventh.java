import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Eleventh {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(),1000,1000);
    }
}

class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("当前时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
