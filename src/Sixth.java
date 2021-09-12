import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sixth {
    public static void main(String[] args) throws ParseException {
        two();
    }

    public static void one() throws ParseException {
        String str = "1911-11-11 11:11:11.111";
        String pat = "yyyy-MM-dd HH:mm:ss.SSS";
        SimpleDateFormat format = new SimpleDateFormat(pat);
        Date date = format.parse(str);
        System.out.println(date);
    }

    public static void two(){
        System.out.println(Math.round(12345.6789));
        System.out.println(Math.round(15.5));
        System.out.println(Math.round(15.51));
        //负数的小数小于等于0.5的都不进位
        System.out.println(Math.round(-15.5));
        System.out.println(Math.round(-15.51));
        System.out.println(MyMath.round(1234.1234,2));
    }
}

class MyMath{
    public static double round(double num,double scale){
        return Math.round(num * Math.pow(10 , scale)) / Math.pow(10,scale);
    }
}