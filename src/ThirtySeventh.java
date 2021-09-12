import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ThirtySeventh {
    public static void main(String[] args) {
        two();
    }

    public static void one(){
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("V");
        stack.push("F");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public static void two(){
        Queue<String> queue = new LinkedList<>();
        queue.add("D");
        queue.add("L");
        queue.add("P");
        System.out.println(queue.poll() + "、");
        System.out.println(queue.poll() + "、");
        System.out.println(queue.poll() + "、");
        System.out.println(queue.poll() + "、");
    }
}
