import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TwentySixth {
    public static void main(String[] args) {
        Subjects subject = (Subjects) new ProxySubjects().bind(new RealSubjects());
        subject.eat("土豆丝",100);
    }
}

interface Subjects {
    void eat(String msg , int num);
}

class RealSubjects implements Subjects {
    @Override
    public void eat(String msg, int num) {
        System.out.println("我要吃" + num + "分量的: " + msg);
    }
}

//InvocationHandler: 动态代理实现的接口,只有实现此接口的子类才具有动态代理功能
class ProxySubjects implements InvocationHandler {
    private Object target;

    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public void prepare(){
        System.out.println("prepare");
    }

    public void over(){
        System.out.println("over");
    }

    public void test(){
        System.out.println("test");
    }

    /**
     * invoke表示的是调用执行的方法,但是所有的代理类返回给用户的接口对象都属于代理对象
     * 当用户执行接口方法的时候所调用的实例化对象就是该代理主题动态创建的一个接口对象
     * proxy: 被代理的对象信息
     * method: 返回被调用的方法对象,取得了Method对象意味着可以使用invoke()反射调用方法
     * args: 表示方法中接受的参数
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.prepare();
        Object ret = method.invoke(this.target,args);
        this.test();
        this.over();
        return ret;
    }
}
