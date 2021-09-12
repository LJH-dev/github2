import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TwentyFifth {
    public static void main(String[] args) throws Exception {
        Subject subject = Factorys.getInstance("ProxySubject",Factorys.getInstance("RealSubject"));
        subject.eat();
    }
}

interface Subject {
    void eat();
}

class RealSubject implements Subject {
    @Override
    public void eat(){
        System.out.println("吃火腿肠、花生米");
    }
}

class ProxySubject implements Subject {
    private Subject subject;

    public ProxySubject(Subject subject){
        this.subject = subject;
    }

    public void clear(){
        System.out.println("洗碗刷锅");
    }

    public void prepare(){
        System.out.println("准备食物");
    }

    @Override
    public void eat() {
        this.prepare();
        this.subject.eat();
        this.clear();
    }
}

class Factorys {
    private Factorys(){}

    public static <T> T getInstance (String className) throws Exception {
        T t = null;
        t = (T) Class.forName(className).newInstance();
        return t;
    }

    public static <T> T getInstance (String proxyClassName,String realClassName) throws Exception {
        T t = null;
        T obj = getInstance(realClassName);
        Constructor<?> constructor = Class.forName(proxyClassName).getConstructor(obj.getClass().getInterfaces()[0]);
        t = (T) constructor.newInstance(obj);
        return t;
    }
}
