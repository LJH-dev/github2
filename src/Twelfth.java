public class Twelfth {
    public static void main(String[] args) {
        new Thread(()->{
            MyThreadLocal thread = new MyThreadLocal();
            thread.setNode("www.LJH.com");
            MyUtil.set(thread);
            new Auxiliary().print();
        },"BOSS A").start();

        new Thread(()->{
            MyThreadLocal thread = new MyThreadLocal();
            thread.setNode("www.LJY.com");
            MyUtil.set(thread);
            new Auxiliary().print();
        },"BOSS B").start();
    }
}

class MyThreadLocal{
    private String node;

    public void setNode(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}

class Auxiliary{
    public void print(){
        System.out.println(Thread.currentThread().getName() + " = " + MyUtil.get().getNode());
    }
}

class MyUtil{
    public static ThreadLocal<MyThreadLocal> threadLocal = new ThreadLocal<>();

    public static void set(MyThreadLocal thread){
        threadLocal.set(thread);
    }

    public static MyThreadLocal get(){
        return threadLocal.get();
    }
}
