import java.io.*;

public class Fifteenth {
    public static void main(String[] args) throws IOException {
        five();
    }

    public static void one() throws IOException {
        File file = new File("D:" + File.separator + "hello.txt");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        OutputStream output = new FileOutputStream(file,true);
        String msg = "www.baidu.com\r\n";
        output.write(msg.getBytes(),1,6);
        output.close();
    }

    public static void two(){
        try(UserFile file = new UserFile()) {
            file.print();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void three() throws IOException {
        File file = new File("D:" + File.separator + "hello.txt");
        if (file.exists()){
            InputStream input = new FileInputStream(file);
            byte data[] = new byte[1024];
            int read = input.read(data);
            System.out.println("读取的内容:\n" + new String(data,0,read));
            input.close();
        }
    }

    public static void four() throws IOException {
        File file = new File("D:" + File.separator + "hello.txt");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        String msg = "第二次世界大战";
        Writer writer = new FileWriter(file);
        writer.write(msg);
        writer.close();
    }

    public static void five() throws IOException {
        File file = new File("D:" + File.separator + "hello.txt");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        Reader reader = new FileReader(file);
        char data[] = new char[1024];
        int len = reader.read(data);
        System.out.println(new String(data,0,len));
        reader.close();
    }
}

class UserFile implements AutoCloseable {
    public UserFile(){
        System.out.println("创建一条新消息");
    }

    public void print(){
        System.out.println("www.LJH.com");
    }

    @Override
    public void close() throws Exception {
        System.out.println("【AutoCloseable接口】进行自动关闭");
        throw new Exception("抛出异常依然会自动关闭");
    }
}
