public class First {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(new DataProvider(data)).start();
        new Thread(new DataConsumer(data)).start();
    }
}

class Data{
    private String title;
    private String note;
    // flag = false 表示允许消费,但是不允许生产者添加
    // flag = true 表示允许生产,但是不允许消费者取走
    private boolean flag = false;

    public synchronized void get() throws InterruptedException {
        if (!this.flag){//允许消费
            //已经生产了,不允许重复生产
            super.wait();//wait(): 挂起一个线程
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.title + " = " + this.note);
        this.flag = false;//已经生产了,不允许重复生产(再次get的时候会挂起该线程,阻止其消费操作)
        super.notify();
    }

    public synchronized void set(String title,String note) throws InterruptedException {
        if (this.flag){//允许生产
            //现在不允许取走
            super.wait();
        }
        this.title = title;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.note = note;
        this.flag = true;
        super.notify();
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }
}

class DataProvider implements Runnable {
    private Data data;

    public DataProvider(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        for (int x = 0 ; x < 50 ; x++){
            if (x % 2 == 0){
                try {
                    this.data.set("李家辉","总经理");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    this.data.set("李家毅","董事长");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class DataConsumer implements Runnable {
    private Data data;

    public DataConsumer(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        for (int x = 0 ; x < 50 ; x++){
            try {
                this.data.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
