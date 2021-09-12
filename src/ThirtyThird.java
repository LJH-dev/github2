import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThirtyThird {
    public static void main(String[] args) {
        two();
    }

    public static void one(){
        List<String> list = new ArrayList<>();
        for (int x = 1 ; x <= 10 ; x++){
            //valueOf(): 将任何类型数据转换为字符串
            list.add(String.valueOf(x));
        }
        System.out.println(list.size());
        System.out.println(list.get(2));
        list.set(6,"66");
        System.out.println(list.get(6));
        System.out.println(list.isEmpty());
        System.out.println(list.contains("6"));
        list.remove(1);
        System.out.println(list.size());
        System.out.println(list.get(1));
        for (int x = 0 ; x < list.size() ; x++){
            if (x == 5) System.out.println();
            System.out.println("list[" + x + "]" + " = " + list.get(x) + "、");
        }
        System.out.println();
        Object[] result = list.toArray();
        System.out.println(Arrays.toString(result));
    }

    public static void two(){
        List<Persons> list = new ArrayList<>();
        list.add(new Persons("张三",20));
        list.add(new Persons("刘四",20));
        list.add(new Persons("马六",20));
        for (int x = 0 ; x < list.size() ; x++){
            System.out.println(list.get(x));
        }
        System.out.println("=============================");

        //验证remove()方法需要equals()方法的支持（contains()方法也需要equals()方法的支持）
        list.remove(new Persons("张三",20));
        for (int x = 0 ; x < list.size() ; x++) {
            System.out.println(list.get(x));
        }
    }
}

class Persons {
    private String name;
    private Integer age;

    public Persons(String name,Integer age){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Persons)) return false;

        Persons person = (Persons) o;
        return this.name.equals(person.name) && (this.age.equals(person.age));
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
