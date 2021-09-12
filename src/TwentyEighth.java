import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TwentyEighth {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Food food = SuperFactory.getInstance();
        food.eat();
    }
}

interface Food {
    void eat();
}

class Durian implements Food {
    @Override
    public void eat() {
        System.out.println("Eat Durian");
    }
}

class Mango implements Food {
    @Override
    public void eat() {
        System.out.println("Eat Mango");
    }
}

@OwnAnnotation(target = Mango.class)
class SuperFactory {
    public static <T> T getInstance() throws IllegalAccessException, InstantiationException {
        OwnAnnotation own = SuperFactory.class.getAnnotation(OwnAnnotation.class);
        //通过反射实现工厂模式
        return (T) own.target().newInstance();
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface OwnAnnotation {
    Class<?> target();
}
