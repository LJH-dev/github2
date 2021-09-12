public class Fourth {
    public static void main(String[] args) {
        //getRuntime(): 返回与当前Java应用程序关联的运行时对象
        Runtime runtime = Runtime.getRuntime();
        System.out.println("JVM将尝试使用的最大内存量 = " + byteToM(runtime.maxMemory()));
        System.out.println("JVM的内存总量 = " + byteToM(runtime.totalMemory()));
        System.out.println("JVM的可用内存量 = " + byteToM(runtime.freeMemory()));
        System.out.println("===================================");
        String str = "";
        for (int x = 0 ; x < 99999 ; x++){
            str += x;
        }
        System.out.println("JVM将尝试使用的最大内存量 = " + byteToM(runtime.maxMemory()));
        System.out.println("JVM的内存总量 = " + byteToM(runtime.totalMemory()));
        System.out.println("JVM的可用内存量 = " + byteToM(runtime.freeMemory()));
        System.out.println("===================================");

        runtime.gc();

        System.out.println("JVM将尝试使用的最大内存量 = " + byteToM(runtime.maxMemory()));
        System.out.println("JVM的内存总量 = " + byteToM(runtime.totalMemory()));
        System.out.println("JVM的可用内存量 = " + byteToM(runtime.freeMemory()));
        System.out.println("===================================");
    }

    public static double byteToM(long num){
        return (double)num / 1024 / 1024;
    }
}
