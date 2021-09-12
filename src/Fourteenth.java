import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fourteenth {
    public static void main(String[] args) throws IOException {
        four();
    }

    //创建文件,文件存在则删除,文件不存在则创建
    public static void one() throws IOException {
        File file = new File("D:\\hello.tet");
        if (file.exists()){
            file.delete();
        }else {
            file.createNewFile();
        }
    }

    //创建多级目录(getParentFile:得到父目录、mkdirs:创建父目录)
    public static void two() throws IOException {
        File file = new File("D:" + File.separator + "One" + File.separator + "Abc" + File.separator + "hello.txt");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        if (file.exists()){
            file.delete();
        }else {
            file.createNewFile();
        }
    }

    //测试方法: length:文件大小、lastModified:文件最后一次修改日期
    public static void three(){
        File file = new File("D:" + File.separator + "1.jpg");
        if (file.exists() && file.isFile()){
            System.out.println("文件大小: " + MyPath.round((file.length()/(double) 1024/1024),2));
            System.out.println("最后一次修改日期: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())));
        }
    }

    //测试方法: isDirectory:判断路径是否是目录、listFiles:列出目录中的全部组成
    public static void four(){
        new Thread(()->{
            File file = new File("C:" + File.separator);
            listDir(file);
        },"列出目录线程").start();
    }

    public static void listDir(File file){
        if (file.exists() && file.isDirectory()){
            File result[] = file.listFiles();
            for (File file1 : result) {
                System.out.println(file1 + " (文件大小 = " + file1.length() + ") ");
            }
        }
    }
}

class MyPath{
    public static double round(double num,int scale){
        return Math.round(num*Math.pow(10,scale)/Math.pow(10,scale));
    }
}