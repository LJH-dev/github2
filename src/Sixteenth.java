import java.io.*;

public class Sixteenth {
    public static void main(String[] args) throws IOException {
        if (args.length != 2){
            System.out.println("错误的执行方式,命令调用: java copy 源文件路径 目标文件路径");
            System.exit(1);
        }

        if (CopyUtil.fileExists(args[0])){
            CopyUtil.createDir(args[1]);
            System.out.println(CopyUtil.copy(args[0],args[1]) ? "文件拷贝成功" : "文件拷贝失败");
        }else{
            System.out.println("源文件不存在");
        }
    }
}

class CopyUtil{
    private CopyUtil(){}

    public static boolean fileExists(String path){
        return new File(path).exists();
    }

    public static void createDir(String path){
        File file = new File(path);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
    }

    public static boolean copy(String srcPath,String desPath) throws IOException {
        boolean flag = false;
        File inFile = new File(srcPath);
        File outFile = new File(desPath);
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(inFile);
            output = new FileOutputStream(outFile);
            copyHandle(input,output);
            flag = false;
        } catch (FileNotFoundException e) {
            flag = false;
        } finally {
            try {
                input.close();
                output.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private static void copyHandle(InputStream input,OutputStream output) throws IOException {
        int temp = 0;
        byte data[] = new byte[2048];
        while ((temp = input.read(data)) != -1){
            /**
             * public void write(byte[] b,
             *                   int off,
             *                   int len)
             * 方法参数解释: 从指定的字节数组写入len字节，从偏移off开始输出到此输出流。
             *               write(b, off, len)的一般合同是数组b中的一些字节按顺序写入输出流;
             *               元素b[off]是写入的第一个字节， b[off+len-1]是此操作写入的最后一个字节。
             * */
            
            /**
             * public int read(byte[] b,
             *                 int off,
             *                 int len)
             * 方法参数解释: 从输入流读取len字节的数据到一个字节数组。 尝试读取多达len个字节，但可以
             *              读取较小的数字。 实际读取的字节数作为整数返回。第一个字节读取存储在元素b[off],
             *              下一个字节存入b[off+1],等等。
             * */
            output.write(data,0,temp);
        }
    }
}
