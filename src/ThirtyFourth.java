import java.util.Set;
import java.util.TreeSet;

public class ThirtyFourth {
    public static void main(String[] args) {
        Set<UserPerson> set = new TreeSet<>();
        set.add(new UserPerson("张三" , 23));
        set.add(new UserPerson("张四" , 24));
        set.add(new UserPerson("张五" , 25));
        set.add(new UserPerson("张六" , 26));
        System.out.println(set);
    }
}

//对象所在的类一定要实现Comparable接口,并且实现cpmpareTo()方法,因为只有通过这个方法才能知道大小关系
class UserPerson implements Comparable<UserPerson> {
    private String name;
    private Integer age;

    public UserPerson(String name,Integer age){
        this.age = age;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' + "\n";
    }
    @Override
    public int compareTo(UserPerson o) {
        if (this.age > o.age){
            return 1;
        }else if (this.age < o.age){
            return -1;
        }else {
            return this.name.compareTo(o.name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPerson that = (UserPerson) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return age != null ? age.equals(that.age) : that.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}