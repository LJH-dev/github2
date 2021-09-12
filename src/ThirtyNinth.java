import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ThirtyNinth {
    public static void main(String[] args) throws IOException {
        two();
        one();
    }

    public static void one() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("SH","上海");
        properties.setProperty("XA","西安");
        System.out.println(properties.getProperty("SH"));
        System.out.println(properties.getProperty("XA"));
        properties.store(new FileOutputStream(new File("D:" + File.separator + "area.properties")),"Area Message");
        System.out.println("============================");
        Properties properties1 = new Properties();
        properties1.load(new FileInputStream(new File("D:" + File.separator + "area.properties")));
        System.out.println(properties1.getProperty("SH"));
    }

    public static void two(){
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("Y");
        list.add("D");
        list.add("java");
        list.forEach(System.out :: println);
        System.out.println("============================");
        Stream<String> stream = list.stream();
        System.out.println(stream.count());
        Stream<String> stream1 = list.stream();
        List<String> java = stream1.filter((e)->e.contains("java")).collect(Collectors.toList());
        System.out.println(java);
    }

    public static void three(){
        List<String> list = new ArrayList<>();
        list.add("1.java");
        list.add("2.Y");
        list.add("3.D");
        list.add("4.java");
        list.add("5.C");
        list.add("6.LJH");
        list.add("7.java");
        Stream<String> stream = list.stream();
        List<String> java = stream.skip(2).limit(3)
                .map((s)->s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(java);
    }

    public static void four(){
        List<Order> list = new ArrayList<>();
        list.add(new Order("苹果手机",6999.99,1));
        list.add(new Order("苹果电脑",15000.99,1));
        list.add(new Order("苹果手表",2500.99,1));
        //在Stream接口里面提供有一个map结果变为Double型的操作
        DoubleSummaryStatistics allPrice = list.stream()
                .mapToDouble((org)->org.getPrice()*org.getCount())
                .summaryStatistics();
        System.out.println("总量: " + allPrice.getCount());
        System.out.println("平均值: " + allPrice.getAverage());
        System.out.println("最大值: " + allPrice.getMax());
        System.out.println("最小值: " + allPrice.getMin());
        System.out.println("总和: " + allPrice.getSum());
        System.out.println("==================");
        double test = list.stream().map((obj)->obj.getPrice()*obj.getCount()).reduce((sum,x)->sum+x).get();
        System.out.println("总和: " + test);
    }
}

class Order {
    private String title;
    private double price;
    private int count;
    public Order(String title,double price,int count) {
        super();
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
