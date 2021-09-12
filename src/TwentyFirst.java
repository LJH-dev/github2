import java.lang.reflect.Field;

public class TwentyFirst {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        one();
    }

    public static void one() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> cls = Class.forName("Pers");
        Object obj = cls.newInstance();
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj,"张三");
        System.out.println(field.get(obj));
        System.out.println(field.getType().getName());
        System.out.println(field.getType().getSimpleName());
    }
}

class Pers{
    private String name;
}

