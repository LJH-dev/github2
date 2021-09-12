import java.io.*;

public class Eighteenth {
    public static final File FILE = new File("D:" + File.separator + "UserAdmin.ser");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //通过 ObjectOutputStream 和 ObjectInputStream 实现序列化和反序列化
        ser(new UserAdmin("张三",545));
        dser();
    }

    public static void ser(Object obj) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE));
        output.writeObject(obj);
        output.close();
    }

    public static void dser() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE));
        System.out.println(input.readObject());
        input.close();
    }
}

class UserAdmin implements Serializable {
    //transient: 防止被序列化
    private transient String name;
    private int age;
    public UserAdmin(String name,int age){
        super();
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserAdmin{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
