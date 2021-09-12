import java.io.*;

public class TwentyFourth {
    public static void main(String[] args) throws Exception {
        two();
    }

    public static void one() throws Exception {
        Class<?> cls = Member.class;
        System.out.println(cls.getClassLoader());
        System.out.println(cls.getClassLoader().getParent());
        System.out.println(cls.getClassLoader().getParent().getParent());
        System.out.println(Class.forName("Member").getClassLoader().loadClass("Member").newInstance());
    }

    public static void two() throws IOException, IllegalAccessException, InstantiationException {
        Class<?> cls = new MyClassLoader().loadMyClass("Member");
        System.out.println(cls.newInstance());
        System.out.println(cls.getClassLoader());
        System.out.println(cls.getClassLoader().getParent());
        System.out.println(cls.getClassLoader().getParent().getParent());
    }
}

class Member {
    @Override
    public String toString(){
        return "Member";
    }
}

class MyClassLoader extends ClassLoader {
    public Class<?> loadMyClass(String className) throws IOException {
        byte classData[] = this.loadClassDate();
        return super.defineClass(className,classData,0,classData.length);
    }

    private byte[] loadClassDate() throws IOException {
        InputStream input = new FileInputStream("D:" + File.separator + "Member.class");
        //内存操作流(发生IO处理却不希望产生文件)
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[20];
        int temp = 0;
        while ((temp = input.read(data)) != -1){
            bos.write(data,0,temp);
        }
        byte ret[] = bos.toByteArray();
        input.close();
        bos.close();
        return ret;
    }
}
