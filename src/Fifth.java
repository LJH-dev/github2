public class Fifth {
    public static void main(String[] args) throws CloneNotSupportedException {
        RootUser user1 = new RootUser("张三",2);
        //深克隆
        RootUser user2 = (RootUser) user1.clone();
        System.out.println(user1);
        System.out.println(user2);
    }
}

class RootUser implements Cloneable {
    private String name;
    private int age;

    public RootUser(String name , int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}