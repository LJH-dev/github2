import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TwentySeventh {
    public static void main(String[] args) throws NoSuchMethodException {
        two();
    }

    public static void one() throws NoSuchMethodException {
        //getAnnotation(): 取得指定的Annotation
        Annotation annotation[] = MyAnnotation.class.getMethod("toString").getAnnotations();
        for (Annotation annotation1 : annotation) {
            System.out.println(annotation1);
        }

        //getAnnotations(): 取得全部的Annotation
        Annotation annotation1[] = MyAnnotation.class.getAnnotations();
        for (Annotation annotation2 : annotation1) {
            System.out.println(annotation2);
        }
    }

    public static void two(){
        CustomAnnotation cus = MyAnnotation.class.getDeclaredAnnotation(CustomAnnotation.class);
        System.out.println(cus.sex() + " " + cus.age());
    }
}

@SuppressWarnings("all")
@Deprecated
@CustomAnnotation(sex = "男")
class MyAnnotation implements Serializable {
    @Override
    @Deprecated
    public String toString() {
        return super.toString();
    }
}

@Retention(RetentionPolicy.RUNTIME)//@Retention: 设置该Annotation在运行时生效
@interface CustomAnnotation {
    //定义属性(default指定属性默认值)
    String sex() default "创业成功";
    int age() default 25;
}