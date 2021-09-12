import java.util.*;

public class ThirtyFifth {
    public static void main(String[] args) {
        four();
    }

    public static void one(){
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        ListIterator<String> iterator = list.listIterator();
        System.out.print("由前向后输出: ");
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "、");
        }
        System.out.print("\n有后向前输出: ");
        while (iterator.hasPrevious()){
            System.out.print(iterator.previous() + "、");
        }
    }

    public static void two(){
        Vector<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.add("B");
        vector.add("C");
        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()){
            System.out.print(enumeration.nextElement() + "、");
        }
    }

    public static void three(){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"A");
        map.put(2,"B");
        map.put(3,"C");
        map.put(4,"D");
        System.out.println(map);
        System.out.println(map.get(3));
        System.out.println("==================");
        //keySet(): 取得所有的key
        Set<Integer> set = map.keySet();
        for (Integer integer : set) {
            System.out.println(integer + "、");
        }
        System.out.println("\n==================");
        //values(): 取得所有的values
        Collection<String> list = map.values();
        for (String s : list) {
            System.out.println(s + "、");
        }
    }

    public static void four(){
        for (int x = 0 ; x < 10 ; x++){
            int finalX = x;
            new Thread(()->{
                if (finalX == 5){
                    System.out.println();
                }
                Random random = new Random();
                int temp = random.nextInt(9999);
                int result = temp % 3;
                switch (result){
                    case 0:
                        System.out.print("第0桶" + temp + "、");
                        break;
                    case 1:
                        System.out.print("第1桶" + temp + "、");
                        break;
                    case 2:
                        System.out.print("第2桶" + temp + "、");
                        break;
                }
            }).start();
        }
    }
}
