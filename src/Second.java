import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Second {
    public static void main(String[] args){
        four();
    }

    public static void one() throws InterruptedException {
        //newCachedThreadPool(): 创建无大小限制的线程池
        ExecutorService service = Executors.newCachedThreadPool();
        for (int x = 0 ; x < 10 ; x++){
            Thread.sleep(200);
            int index = x;
            //submit: 提交一个可运行的任务执行
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "、 x = " + index);
                }
            });
        }
        service.shutdown();
    }

    public static void two(){
        //newSingleThreadExecutor(): 创建单线程池
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int x = 0 ; x < 10 ; x++){
            int index = x;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "、 x = " + index);
                }
            });
        }
        service.shutdown();
    }

    public static void three(){
        //newFixedThreadPool(): 创建固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int x = 0 ; x < 10 ; x++){
            int index = x;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "、 x = " + index);
                }
            });
        }
        service.shutdown();
    }

    public static void four(){
        /**
         * 创建定时线程池:
         * newScheduledThreadPool(Runnable command,
         *                        long initialDelay,
         *                        long delay,
         *                        TimeUnit unit)
         * 参数解析:
         *   command - 要执行的任务
         *   initialDelay - 延迟第一次执行的时间
         *   delay - 一个执行终止与下一个执行的开始之间的延迟
         *   unit - initialDelay和delay参数的时间单位
         *
         * */
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        for (int x = 0 ; x < 20 ; x++){
            int index = x;
            service.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "、 x = " + index);
                }
            },3,2, TimeUnit.MICROSECONDS);
        }
    }
}
