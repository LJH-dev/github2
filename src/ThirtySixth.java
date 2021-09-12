import java.util.*;

public class ThirtySixth {
    public static void main(String[] args) {
        three();
    }

    public static void one(){
        Map<String,AdminPerson> map = new HashMap<>();
        map.put(new String("张三"),new AdminPerson("刘四"));
        System.out.println(map.get(new String("张三")));

        Map<AdminPerson,String> map1 = new HashMap<>();
        map1.put(new AdminPerson("张三"),new String("刘四"));
        System.out.println(map1.get(new AdminPerson("张三")));

        Map<RootAdmin,String> map2 = new HashMap<>();
        map2.put(new RootAdmin("张三"),new String("刘四"));
        System.out.println(map2.get(new RootAdmin("张三")));
    }

    public static void two(){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"Hello");
        map.put(2,"World");
        //将Map变Set
        //entrySet(): 返回此map中包含的映射的Set视图
        Set<Map.Entry<Integer,String>> set = map.entrySet();
        Iterator<Map.Entry<Integer,String>> iterator = set.iterator();
        //取出每一个Map.Entry对象
        while (iterator.hasNext()){
            Map.Entry<Integer,String> map1 = iterator.next();
            System.out.println(map1.getKey() + " = " + map1.getValue());
        }
    }

    public static void three(){
        Map<UserAdminRoot,String> map = new TreeMap<>();
        map.put(new UserAdminRoot("刘四"),new String("张三"));
        System.out.println(map.get(new UserAdminRoot("刘四")));
    }
}

class AdminPerson {
    private String name;
    public AdminPerson(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminPerson{" +
                "name='" + name + '\'' +
                '}';
    }
}

class RootAdmin {
    private String name;
    public RootAdmin(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "RootAdmin{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RootAdmin rootAdmin = (RootAdmin) o;

        return name != null ? name.equals(rootAdmin.name) : rootAdmin.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

class UserAdminRoot implements Comparable<UserAdminRoot> {
    private String name;
    public UserAdminRoot(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserAdminRoot{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(UserAdminRoot o) {
        return 0;
    }
}
