import java.io.*;
import java.util.Scanner;

public class Seventeenth {
    public static void main(String[] args) throws IOException {
        eight();
    }

    public static void one() throws IOException{
        String msg = "hello world";
        InputStream input = new ByteArrayInputStream(msg.getBytes());
        OutputStream output = new ByteArrayOutputStream();
        int temp = 0;
        while ((temp = input.read()) != -1){
            output.write(Character.toUpperCase(temp));
        }
        System.out.println(output);
        input.close();
        output.close();
    }

    public static void two() throws Exception {
        File file[] = new File[]{
                new File("D:" + File.separator + "a.txt"),
                new File("D:" + File.separator + "b.txt")
        };
        System.out.println(readFile(file[0]));
        String data[] = new String[2];
        for (int x = 0 ; x < file.length ; x++){
            // file[0]:a.txt    file[1]:b.txt
            data[x] = readFile(file[x]);
        }
        //StringBuffer: 效率低,线程安全(从文件读取的内容大小不确定)
        StringBuffer buffer = new StringBuffer();
        String contentA[] = data[0].split(" ");
        String contentB[] = data[1].split(" ");
        //字节输出流:OutputStream
        OutputStream output = new FileOutputStream(new File("D:" + File.separator + "data.txt"));
        for (int x = 0 ; x < contentA.length ; x++){
            String str = contentA[x] + "(" + contentB[x] + ")";
            buffer.append(contentA[x]).append("(").append(contentB[x]).append(")").append(" ");
            output.write(str.getBytes());
        }
        output.close();
        System.out.println(buffer);
    }

    public static String readFile(File file) throws Exception{
        if (file.exists()){
            //字节输入流:InputStream
            InputStream input = new FileInputStream(file);
            //内存操作流:ByteArrayOutputStream
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte data[] = new byte[10];
            int temp = 0;
            while ((temp = input.read(data)) != -1){
                bos.write(data,0,temp);
            }
            bos.close();
            input.close();
            //toByteArray(): 创建一个新分配的字节数组,其大小是此输出流的当前大小,缓冲区的有效内容已被复制到其中
            return new String(bos.toByteArray());
        }
        return null;
    }

    public static void three() throws FileNotFoundException {
        PrintUtil print = new PrintUtil(new FileOutputStream(new File("D:" + File.separator + "hello.txt")));
        print.print("姓名: ");
        print.println("李家辉");
        print.print("年龄: ");
        print.println(21);
        print.print("峰值: ");
        print.println("+∞");
        print.close();
    }

    public static void four() throws FileNotFoundException {
        String name = "小日本";
        int age = 0;
        double salary = -43244365.23423;
        PrintWriter writer = new PrintWriter(new FileOutputStream(new File("D:" + File.separator + "hello.txt")));
        writer.printf("姓名: %s、年龄: %d、工资: %8.2f",name,age,salary);
        writer.close();

        String str = String.format("姓名: %s、年龄: %d、工资: %8.2f",name,age,salary);
        System.out.println(str);
    }

    public static void five() throws IOException {
        //System.in原本的支持度并不高,对于英文操作可以勉强使用,对于中文操作必须结合内存流完成
        InputStream input = System.in;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte data[] = new byte[1024];
        System.out.println("请输入信息: ");
        int temp = 0;
        while ((temp = input.read(data)) != -1){
            bos.write(data,0,temp);
            if (temp < data.length){
                break;
            }
        }
        System.out.println("输入内容为: " + new String(bos.toByteArray()));
        bos.close();
        input.close();
    }

    public static void six() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入信息: ");
        String str = bufferedReader.readLine();
        System.out.println("输入信息为: " + str);
    }

    public static void seven(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入生日(格式: YYYY-mm-dd): ");
        if (scanner.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
            String birthDay = scanner.next("\\d{4}-\\d{2}-\\d{2}");
            System.out.println(birthDay);
        }else{
            System.out.println("输入格式错误");
        }
        scanner.close();
    }

    public static void eight() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(new File("D:" + File.separator + "hello.txt")));
        scanner.useDelimiter("\r\n");
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
        scanner.close();
    }
}

class PrintUtil{
    private OutputStream output;
    public PrintUtil(OutputStream output){
        this.output = output;
    }

    public void print(String str){
        try {
            this.output.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String str){
        this.print(str + "\r\n");
    }

    public void print(int data){
        this.print(String.valueOf(data));
    }

    public void println(int data){
        this.println(String.valueOf(data));
    }

    public void print(double data){
        this.print(String.valueOf(data));
    }

    public void println(double data){
        this.println(String.valueOf(data));
    }

    public void close(){
        try {
            this.output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
