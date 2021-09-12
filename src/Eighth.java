import java.util.Arrays;

public class Eighth {
    public static void main(String[] args) {
        ComparableTest();
    }

    public static void ArraysTest(){
        int data1 [] = new int[]{1,2,3,4,5,6};
        int data2 [] = new int[]{1,2,3,4,5,6};
        System.out.println(Arrays.toString(data1));
        System.out.println(Arrays.equals(data1,data2));
        //寻找5在数组data1的位置(返回索引)
        System.out.println(Arrays.binarySearch(data1,5));
    }

    public static void ComparableTest(){
        People people [] = new People[]{
                new People("张三",20),
                new People("宋五",34),
                new People("刘四",65)
        };
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));
    }
}

class People implements Comparable<People> {
    private String name;
    private int age;

    public People(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'+"\n";
    }

    @Override
    public int compareTo(People o) {
        if (this.age > o.age){
            return 1;
        }else if (this.age < o.age){
            return -1;
        }else {
            return 0;
        }
    }
}