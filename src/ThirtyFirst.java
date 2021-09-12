import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThirtyFirst {
    //服务器端
    private static class MyThread implements Runnable {
        private Socket socket;

        public MyThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            Scanner scanner = null;
            PrintStream printStream = null;
            try {
                scanner = new Scanner(socket.getInputStream());
                printStream = new PrintStream(socket.getOutputStream());
                scanner.useDelimiter("\n");
                boolean flag = true;
                while (flag) {
                    if (scanner.hasNext()) {
                        String str = scanner.next().trim();
                        if ("byebye".equalsIgnoreCase(str)) {
                            printStream.println("ヾ(•ω•`)o 程序结束 O(∩_∩)O");
                            flag = false;
                            break;
                        }
                        printStream.println("ECHO: " + str);
                    }
                    printStream.close();
                    scanner.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        boolean flag = true;
        while (flag) {
            //等待客户连接
            Socket socket = serverSocket.accept();
            new Thread(new MyThread(socket)).start();
        }
        serverSocket.close();
    }
}

//客户端
class Customer {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost" , 8888);
        //用户输入信息
        Scanner keyScan = new Scanner(System.in);
        keyScan.useDelimiter("\n");
        Scanner netScan = new Scanner(socket.getInputStream());
        netScan.useDelimiter("\n");
        //PrintStream: 字节打印流
        PrintStream netOut = new PrintStream(socket.getOutputStream());
        boolean flag = true;
        while (flag) {
            System.out.println("请输入要发送的信息: ");
            String str = null;
            if (keyScan.hasNext()) {
                str = keyScan.next().trim();
                //发送输入的信息给服务器端
                netOut.println(str);
                if (netScan.hasNext()) {
                    System.out.println(netScan.next());
                }
            }
            if ("byebye".equalsIgnoreCase(str)) {
                flag = false;
                break;
            }
        }
        keyScan.close();
        netScan.close();
        socket.close();
    }
}
