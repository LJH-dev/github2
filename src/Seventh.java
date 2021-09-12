import java.math.BigDecimal;
import java.math.BigInteger;

public class Seventh {
    public static void main(String[] args) {
        two();
    }

    public static void one(){
        BigInteger bigInteger1 = new BigInteger("348632847638456738472542956829680256");
        BigInteger bigInteger2 = new BigInteger("97568935960212728469878191358092489064587354634");
        System.out.println("加法: " + bigInteger1.add(bigInteger2));
        System.out.println("减法: " + bigInteger2.subtract(bigInteger1));
        System.out.println("乘法: " + bigInteger1.multiply(bigInteger2));
        System.out.println("除法: " + bigInteger2.divide(bigInteger1));

        BigInteger result[] = bigInteger2.divideAndRemainder(bigInteger1);
        System.out.println(" 商: "+result[0] + " 余数 = " + result[1]);
    }

    public static void two(){
        long start = System.currentTimeMillis();
        double num = 3748267829693487628436.45835;
        System.out.println("乘法: " + new BigDecimal(num).pow(6934876));
        long end = System.currentTimeMillis();
        System.out.println(end  - start);
        System.out.println(UserMath.round(12313.4122,2));
    }
}

class UserMath{
    //实现四舍五入
    public static double round(double num , int scale){
        return new BigDecimal(num).divide(new BigDecimal(1),scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}