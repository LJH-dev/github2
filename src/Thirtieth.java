import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//服务端程序
public class Thirtieth {
    public static void main(String[] args) throws IOException {
        //创建一个服务器端的服务对象,所有的服务一定要有一个服务端口
        ServerSocket serverSocket = new ServerSocket(8866);
        System.out.println("等客户来连接");
        //等待连接(此时进入阻塞状态,连接的是客户)
        Socket socket = serverSocket.accept();
        //向客户输出
        PrintStream out = new PrintStream(socket.getOutputStream());
        out.print("hello world");
        serverSocket.close();
    }
}

//客户端程序
class Client {
    public static void main(String[] args) throws IOException {
        //连接到指定的服务器端的主机名称和端口
        Socket socket = new Socket("localhost",8866);
        Scanner scanner = new Scanner(socket.getInputStream());
        scanner.useDelimiter("\n");
        if (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        socket.close();
    }
}
