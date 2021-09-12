import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TwentyThird {
    public static void main(String[] args) throws Exception {
        String value = "userRoot.name:smith|userRoot.job:clerk|userRoot.dept.name:研发部门|userRoot.dept.company.name:LJHY|userRoot.dept.company.address:北京天安门";
        SetParam setParam = new SetParam();
        setParam.setValue(value);
        System.out.println(setParam.get(value));
        String value1 = "dept.name:Mike|dept.loc:Dont know";
        DeptSetParam deptSetParam = new DeptSetParam();
        deptSetParam.setValue(value1);
        System.out.println(deptSetParam.get(value1));
    }
}

class UserRoots {
    private String name;
    private String job;
    private Depts dept = new Depts();

    public void setDept(Depts dept) {
        this.dept = dept;
    }

    public Depts getDept() {
        return dept;
    }

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
                '}' + this.dept;
    }
}

class SetParam{
    private UserRoots rootUser = new UserRoots();

    public void setValue(String value) throws Exception {
        BeanOperations.setBeanValue(rootUser,value);
    }

    public String get(String value){
        return BeanOperations.getBeanValue(rootUser,value);
    }
}

class BeanOperations {
    private BeanOperations(){}

    public static void setBeanValue(Object actionObject,String msg) throws Exception {
        String result[] = msg.split("\\|");
        for (String s : result) {
            String temp[] = s.split(":");
            String value = temp[1];
            String fields[] = temp[0].split("\\.");
            if (fields.length > 2){
                //多级
                //确定属性的操作对象: 一层层找出每一个getter方法返回的内容
                Object currentObject = actionObject;
                for (int y = 1 ; y < fields.length-1 ; y++){
                    currentObject = ObjectUtil.getObject(currentObject,fields[y]);
                }
                ObjectUtil.setObjectValue(currentObject,fields[fields.length-1],value);
            }else {
                ObjectUtil.setObjectValue(actionObject,fields[1],value);
            }
        }
    }

    public static String getBeanValue(Object actionObject,String msg){
        String result[] = msg.split("\\|");
        String one = StringUtil.initcap(actionObject.toString().split("\\{")[0]) + "< ";
        String total = null;
        for (String s : result) {
            String temp[] = s.split(":");
            String fields[] = temp[0].split("\\.");
            if (fields.length > 2){
                String tmp = null;
                for (int x = 1 ; x < fields.length ; x++){
                    if (tmp == null){
                        tmp = fields[x];
                    }else {
                        tmp += ".";
                        tmp += fields[x];
                    }
                }
                if (total == null){
                    total = tmp + "=" + temp[1] + ",";
                }else {
                    total += tmp + "=" + temp[1] + ",";
                }
            }else {
                if (total == null){
                    total = fields[1] + "=" + temp[1] + ",";
                }else {
                    total += fields[1] + "=" + temp[1] + ",";
                }
            }
        }
        String total1 = one + total;
        return total1.substring(0,total1.length()-1) + ">";
    }
}

class StringUtil {
    private StringUtil(){}

    public static String initcap(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}

class ObjectUtil {
    private ObjectUtil(){}

    public static Object getObject(Object wrapObject,String attribute) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "get" + StringUtil.initcap(attribute);
        Field field = wrapObject.getClass().getDeclaredField(attribute);
        if (field == null){
            field = wrapObject.getClass().getField(attribute);
        }
        if (field == null){
            return null;
        }
        Method method = wrapObject.getClass().getMethod(methodName);
        return method.invoke(wrapObject);
    }

    public static void setObjectValue(Object wrapObject,String attribute,String args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field field = wrapObject.getClass().getDeclaredField(attribute);
        if (field == null){
            field = wrapObject.getClass().getField(attribute);
        }
        if (field == null){
            return;
        }
        String methodName = "set" + StringUtil.initcap(attribute);
        Method method = wrapObject.getClass().getMethod(methodName,field.getType());
        method.invoke(wrapObject,args);
    }
}

class Depts {
    private String name;
    private String loc;
    private Company company = new Company();

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

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
        return "Depts{" +
                "name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                ", company=" + company +
                '}';
    }
}

class DeptSetParam {
    private Depts dept = new Depts();

    public void setValue(String value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        BeanOperation.setBeanValue(dept,value);
    }

    public String get(String value){
        return BeanOperation.getBeanValue(dept,value);
    }
}

class Company {
    private String name;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
