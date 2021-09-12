import java.util.LinkedList;
import java.util.Queue;

public class ThirtyEighth {
    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue();
        new Thread(new DataProviders(dataQueue)).start();
        new Thread(new DataConsumers(dataQueue)).start();
    }
}

//数据的生产队列
class DataQueue {
    private class Data{
        private String title;
        private String note;

        public synchronized void set(String title,String note) throws InterruptedException {
            this.title = title;
            Thread.sleep(100);
            this.note = note;
        }

        public synchronized Data get() throws InterruptedException {
            Thread.sleep(1000);
            return this;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "title='" + title + '\'' +
                    ", note='" + note + '\'' +
                    '}';
        }
    }

    private boolean flag = false;
    private Queue<Data> queue = new LinkedList<>();

    public synchronized void product(String title,String note) throws InterruptedException {
        Data data = new Data();
        data.set(title,note);
        //add(): 生产者行为
        this.queue.add(data);
    }

    public synchronized void consumer() throws InterruptedException {
        //poll(): 消费者行为
        Data data = this.queue.poll();
        if (data == null){
            super.wait(1000);
        }
        System.out.println(data.get());
    }
}

class DataProviders implements Runnable {
    private DataQueue dataQueue;

    public DataProviders(DataQueue dataQueue){
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        for (int x = 0 ; x < 50 ; x++){
            if (x % 2 == 0){
                try {
                    this.dataQueue.product("李家辉","年薪百万");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    this.dataQueue.product("李家毅","年薪百万");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class DataConsumers implements Runnable {
    private DataQueue dataQueue;

    public DataConsumers(DataQueue dataQueue){
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        for (int x = 0 ; x < 50 ; x++){
            try {
                this.dataQueue.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
