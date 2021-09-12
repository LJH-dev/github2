import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TwentySecond {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String value = "userRoot.name:smith|userRoot.job:clerk";
        SetParas setParas = new SetParas();
        setParas.setValue(value);
        System.out.println(setParas.get(value));

        String value1 = "dept.name:Mike|dept.loc:Dont know";
        DeptSetParas deptSetParas = new DeptSetParas();
        deptSetParas.setValue(value1);
        System.out.println(deptSetParas.get(value1));
    }
}

class UserRoot {
    private String name;
    private String job;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    @Override
    public String toString() {
        return "UserRoot{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}

class SetParas {
    private UserRoot userRoot = new UserRoot();

    public void setValue(String value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        BeanOperation.setBeanValue(userRoot,value);
    }

    public String get(String value){
        return BeanOperation.getBeanValue(userRoot,value);
    }
}

//负责实现自动的VO匹配处理操作,本身不需要通过实例化对象完成,所以构造方法私有化
class BeanOperation {
    private BeanOperation(){}

    //负责设置类中的属性操作(actionObject: 表示当前发出设置请求的程序类的当前对象、msg: 所有的属性的具体内容)
    public static void setBeanValue(Object actionObject,String msg) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String result[] = msg.split("\\|");
        for (String s : result) {
            String temp[] = s.split(":");
            String value = temp[1];
            String fields[] = temp[0].split("\\.");
            ObjectUtils.setObjectValue(actionObject,fields[1],value);
        }
    }

    public static String getBeanValue(Object actionObject,String msg){
        String result[] = msg.split("\\|");
        String one = StringUtils.initcap(actionObject.toString().split("\\{")[0]) + "<";
        String total = null;
        for (String s : result) {
            String temp[] = s.split(":");
            String fields[] = temp[0].split("\\.");
            if (total == null){
                total = fields[1] + "=" + temp[1] + ",";
            }else {
                total += fields[1] + "=" + temp[1] + ",";
            }
        }
        String total1 = one + total + ">";
        return total1;
    }
}

//针对字符串进行处理操作(首字母大写)
class StringUtils {
    private StringUtils(){}

    public static String initcap(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}

//根据属性名称调用相应类中的 setter 和 getter 方法
class ObjectUtils {
    private ObjectUtils(){}

    //负责调用指定类中的 getter 方法(wrapObject: 表示要调用的方法的所在类对象)
    public static Object getObject(Object wrapObject,String attribute) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "get" + StringUtils.initcap(attribute);
        Field field = wrapObject.getClass().getDeclaredField(attribute);
        if (field == null){
            field = wrapObject.getClass().getField(attribute);
        }
        if (field == null){
            return null;
        }
        Method method = wrapObject.getClass().getMethod(methodName);
        System.out.println(method.invoke(wrapObject)+"   (⊙o⊙)？   ");
        return method.invoke(wrapObject);
    }

    //根据指定的类对象设置指定类中的属性,调用 setter 方法
    //wrapObject: 属性所在类的实例化对象  attribute:属性名称  args:属性内容
    public static void setObjectValue(Object wrapObject,String attribute,String args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field field = wrapObject.getClass().getDeclaredField(attribute);
        if (field == null){
            field = wrapObject.getClass().getField(attribute);
        }
        if (field == null){
            return;
        }
        String methodName = "set" + StringUtils.initcap(attribute);
        Method method = wrapObject.getClass().getMethod(methodName,field.getType());
        method.invoke(wrapObject,args);
    }
}

class Dept {
    private String name;
    private String loc;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}

class DeptSetParas {
    private Dept dept = new Dept();

    public void setValue(String value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        BeanOperation.setBeanValue(dept,value);
    }

    public String get(String value){
        return BeanOperation.getBeanValue(dept,value);
    }
}
