//https://www.geeksforgeeks.org/minimum-time-to-burn-a-tree-starting-from-a-leaf-node/
import java.util.Objects;
import java.util.Stack;

public class BurnTree {
    private static Node createTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.left.left = new Node(8);

        root.left.right = new Node(5);
        root.left.right.left = new Node(9);
        root.left.right.left.left = new Node(11);
        root.left.right.right = new Node(10);

        root.right = new Node(3);
        root.right.left = new Node(6);
        return root;
    }

    public static int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public static int getMinimumTimeToBurn(Node node, int burningLeaf) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            Node current = stack.peek();
            if (current.key == burningLeaf) {
                break;
            }
            if (current.left != null) {
                stack.push(current.left);
            } else if (current.right != null) {
                stack.push(current.right);
            } else {
                Node visited = null;
                visited = stack.pop(); //Can not be traveled further
                while (!stack.empty()) {
                    Node parent = stack.peek();
                    if (parent.left == visited) {
                        if (parent.right != null) {
                            stack.push(parent.right);
                            break;
                        } else {
                            visited = stack.pop();
                        }
                    } else {
                        visited = stack.pop();
                    }
                }
            }
        }
        int time = 0;
        int ans = 0;
        Node last = null;
        while (!stack.empty()) {
            time++;
            if (last == stack.peek().left) {
                ans = Math.max(ans, getHeight(stack.peek().right) + time);
            } else if (last == stack.peek().right) {
                ans = Math.max(ans, getHeight(stack.peek().left) + time);
            }
            last = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        Node root = createTree();
        int time = getMinimumTimeToBurn(root, 11);
        System.out.println(time);

    }

    public static class Node {
        final int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, left, right);
        }
    }
}
