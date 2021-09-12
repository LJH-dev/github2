import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Thirteenth {
    public static void main(String[] args) throws ParseException {
        String str = "ajfiawe0rut0qawe523095ihrnfgonzdcjvq893453491258";
        String regex = "[^a-zA-Z]";
        System.out.println(str.replaceAll(regex,""));
        System.out.println("========================");

        String str1 = "hasdnfasdjh4352834hjbbk";
        String regex1 = "\\d+";
        String result[] = str1.split(regex1);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("========================");

        String str2 = "113123.9090832";
        String regex2 = "\\d+(\\.\\d+)";
        if (str2.matches(regex2)){
            double data = Double.parseDouble(str2);
            System.out.println(data);
        }
        System.out.println("========================");

        String str3 = "2021-09-05";
        String regex3 = "\\d{4}-\\d{2}-\\d{2}";
        if (str3.matches(regex3)){
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(str3));
        }
        System.out.println("========================");

        String str4 = "2300785";
        String regex4 = "\\d{7,8}";
        System.out.println(str4.matches(regex4));

        String str5 = "0102067785";
        String regex5 = "(\\d{3,4})?\\d{7,8}";
        System.out.println(str5.matches(regex5));

        String str6 = "(010)-4967385";
        String regex6 = "((\\(\\d{3,4}\\)-)|(\\d{3,4}))?\\d{7,8}";
        System.out.println(str6.matches(regex6));
        System.out.println("========================");

        String str7 = "ljh@qq.com";
        String regex7 = "\\w+@\\w+\\.\\w+";
        System.out.println(str7.matches(regex7));

        String str8 = "fjekv45-3.7@mgkdnvko.cn";
        String regex8 = "[a-zA-Z][a-zA-Z\\._\\-0-9]{5,14}@[a-zA-Z\\._\\-0-9]+\\.(cn|com|net|org|com\\.cn|net\\.cn)";
        System.out.println(str8.matches(regex8));
    }
}
