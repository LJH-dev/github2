import java.util.Arrays;

public class Ninth {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add("A");
        tree.add("B");
        tree.add("C");
        tree.add("a");
        tree.add("b");
        tree.add("c");
        System.out.println(Arrays.toString(tree.toArray()));
    }
}

class BinaryTree{
    private class Node{
        private Comparable data;//保存的操作数据
        private Node left;//左节点
        private Node right;//右节点

        public Node(Comparable data){
            this.data = data;
        }

        public void addNode(Node newNode){
            if (this.data.compareTo(newNode.data) > 0){
                //传过来的节点数据小于根节点数据,所以是根节点的左子树上的节点数据
                if (this.left == null){
                    this.left = newNode;
                }else {
                    this.left.addNode(newNode);
                }
            }else {
                //传过来的节点数据大于根节点数据,所以是根节点的右子树上的节点数据
                if (this.right == null){
                    this.right = newNode;
                }else {
                    this.right.addNode(newNode);
                }
            }
        }

        public void toArrayNode(){
            if (this.left != null){
                //有左节点
                this.left.toArrayNode();
            }
            BinaryTree.this.retData[BinaryTree.this.foot ++] = this.data;
            if (this.right != null){
                this.right.toArrayNode();
            }
        }
    }

    private Node root;//根节点
    private int count;//保存个数
    private int foot = 0;
    private Object[] retData;//要返回的数据
    public Object[] toArray(){
        this.foot = 0;//角标清0
        this.retData = new Object[this.count];
        this.root.toArrayNode();
        return this.retData;
    }

    public void add(Object data){
        Node newNode = new Node((Comparable) data);
        if (this.root == null){
            this.root = newNode;
        }else {
            this.root.addNode(newNode);
        }
        this.count++;
    }
}