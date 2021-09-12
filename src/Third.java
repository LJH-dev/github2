public class Third {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("hello").append("world").append("\n").append("www.baidu.com");
        System.out.println(buffer);

        String str = "SHANXI AGRICULTURAL UNIVERSITY";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        System.out.println("StringBuffer -> 字符串反转: " + stringBuffer.reverse());
        System.out.println("StringBuffer -> 删除指定范围的数据: " + stringBuffer.delete(7,18));
        System.out.println("StringBuffer -> 追加数据: " + stringBuffer.insert(stringBuffer.length()-1,"study".toUpperCase()));

    }
}
