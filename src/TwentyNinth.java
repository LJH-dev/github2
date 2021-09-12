public class TwentyNinth {
    public static void main(String[] args) {
        new Thread( () -> Singleton.getInstance() , "线程一").start();
        new Thread( () -> Singleton.getInstance() , "线程二").start();
        new Thread( () -> Singleton.getInstance() , "线程三").start();
        new Thread( () -> Singleton.getInstance() , "线程四").start();
        new Thread( () -> Singleton.getInstance() , "线程五").start();
    }
}

class Singleton {
    //懒汉式不会进行对象实例化,会在单例类提供的方法中创建这个唯一实例
    private volatile static Singleton instance;

    private Singleton(){
        System.out.println("单例模式 懒汉式" + Thread.currentThread().getName());
    }

    //缺陷: 第一次实例化Singleton类对象的时候,有可能有多个线程同时进入getInstance()方法,就有可能实例化多次对象
    /**
     * 解决方案
     *   一: synchronized -> 导致的问题: 高并发的访问状态下,本操作直接导致性能下降
     *   二: volatile -> volatile定义的变量表示将直接使用原始数据进行处理,更改后立即生效
     * */
    public static synchronized Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
