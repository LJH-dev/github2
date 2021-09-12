import java.util.Date;

public class Nineteenth {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        two();
    }

    public static void one() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Date date = new Date();//正常情况下需要导包才能实例化创建类对象
        System.out.println(date.getClass());//通过对象取得对象的来源,这就是"反"射的本质
        //所谓的"反"射指的是根据Object对象(一个看似无关联的对象)获得我们想要的类的信息(反射真正注重的是类的信息并不是类对象)
        //通过反射获得类对象: newInstance()
        Class<?> cls = new Date().getClass();
        System.out.println(cls.getName());

        System.out.println(Date.class.getName());

        Class<?> cl = Class.forName("java.util.Date");
        System.out.println(cl.getName());

        Object obj = cls.newInstance();
        System.out.println(obj);
    }

    public static void two() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        IFruit fruit = Factory.getInstance("Apple");
        fruit.eat();
        fruit = Factory.getInstance("Orange");
        fruit.eat();

        IMeat meat = Factory.getInstance("Beef");
        meat.eat();
        meat = Factory.getInstance("Chicken");
        meat.eat();
    }
}

interface IFruit{
    void eat();
}

interface IMeat{
    void eat();
}

class Beef implements IMeat {
    @Override
    public void eat() {
        System.out.println("吃牛肉");
    }
}

class Chicken implements IMeat {
    @Override
    public void eat() {
        System.out.println("吃鸡肉");
    }
}

class Apple implements IFruit{
    @Override
    public void eat(){
        System.out.println("吃苹果");
    }
}

class Orange implements IFruit {
    @Override
    public void eat(){
        System.out.println("吃橙子");
    }
}

class Factory{
    private Factory(){}

    public static<T> T getInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        T fruit = (T) Class.forName(className).newInstance();
        return fruit;
    }
}
