import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Twentieth {
    public static void main(String[] args) throws Exception{
        Class<?> cls = Per.class;
        // getSuperclass(): 取得父类的Class对象
        System.out.println(cls.getSuperclass().getName());
        // getInterfaces(): 取得父接口
        Class<?> as[] = cls.getInterfaces();
        for (Class<?> a : as) {
            System.out.println(a.getName());
        }

        System.out.println("==================================================");

        //getConstructors(): 取得类的所有构造方法
        Constructor<?> constructor[] = cls.getConstructors();
        for (Constructor<?> constructor1 : constructor) {
            // getModifiers(): 获得构造方法权限名
            System.out.println(Modifier.toString(constructor1.getModifiers()) + " ---> ");
            System.out.println(constructor1 + " ---> ");
            // getParameterTypes(): 获得构造方法的形式参数类型
            Class<?> param[] = constructor1.getParameterTypes();
            for (Class<?> aClass : param) {
                System.out.println(aClass.getName() + " ");
            }

            // getExceptionTypes(): 获得构造方法抛出的异常
            Class<?> exp[] = constructor1.getExceptionTypes();
            if (exp.length > 1){
                for (Class<?> aClass : exp) {
                    System.out.println(aClass.getName() + " ---> ");
                }
            }
        }

        System.out.println("==================================================");

        //newInstance(): 实例化对象
        System.out.println(cls.newInstance());
        //通过Constructor实例化对象
        Constructor<?> constructor1 = cls.getConstructor(String.class,int.class);
        System.out.println(constructor1.newInstance("张三",34).toString());
        // getMethods(): 取得全部方法
        for (Method method : cls.getMethods()) {
            System.out.println(method);
        }

        System.out.println("==================================================");

        String att = "name";
        String value = "MLDN";
        Class<?> cls1 = Per.class;
        Object obj = cls1.newInstance();
        Method setMethod = cls1.getMethod("set" + initcap(att),String.class);
        setMethod.invoke(obj,value);//相当于: Per对象名.setName(value)
        Method getMethod = cls1.getMethod("get" + initcap(att));
        Object ret = getMethod.invoke(obj);//相当于: Per对象名.getName(value)
        System.out.println(ret);

        System.out.println("==================================================");

        Class<?> cls2 = Class.forName("Per");
        {
            //getFields(): 取得父类中全部属性
            Field fields[] = cls2.getFields();
            for (Field field : fields) {
                System.out.println(field);
            }
        }
        System.out.println("==================================================");
        {
            //getDeclaredFields(): 取得子类本类中的全部属性
            Field fields[] = cls2.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
            }
        }
        System.out.println("==================================================");

    }

    public static String initcap(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}

interface Message {}

interface Welcome {}

class Per implements Message,Welcome {
    private String name;
    private int age;

    public Per(){}
    public Per(String name){}
    public Per(String name , int age){
        this.name = name;
        this.age = age;
    }
    public Per(String name , int age , double salary){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Per{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
