import java.util.Arrays;
import java.util.Comparator;

public class Tenth {
    public static void main(String[] args) {
        UserPeople people [] = new UserPeople[]{
                new UserPeople("张三",22),
                new UserPeople("刘四",33),
                new UserPeople("宋五",24)
        };
        Arrays.sort(people,new UserComparator());
        System.out.println(Arrays.toString(people));
    }
}

class UserPeople{
    private String name;
    private int age;

    public UserPeople(String name,int age){
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' + "\n";
    }
}

class UserComparator implements Comparator<UserPeople> {
    @Override
    public int compare(UserPeople o1, UserPeople o2) {
        if (o1.getAge() > o2.getAge()){
            return 1;
        }
        else if (o1.getAge() < o2.getAge()){
            return -1;
        }
        return 0;
    }
}
