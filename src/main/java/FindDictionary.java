import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

// https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/

public class FindDictionary {
    public static void main(String[] args) {
        // b -> a
        // d -> a
        // a -> c
        // b -> d
        String[] words = {"caa", "aaa", "aab"};
        LinkedList<Character> answer = findDictionary(words);
        for (char c : answer) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public static LinkedList<Character> findDictionary(String[] words) {
        MyGraph<Character> graph = new MyGraph<>();

        for (int i = 1; i < words.length; i++) {
            int len = Math.min(words[i - 1].length(), words[i].length());
            for (int j = 0; j < len; j++) {
                if (words[i - 1].charAt(j) != words[i].charAt(j)) {
                    LinkedList<Character> adjacencyList = graph.get(words[i - 1].charAt(j));
                    if (adjacencyList == null) {
                        adjacencyList = new LinkedList<>();
                    }
                    adjacencyList.add(words[i].charAt(j));
                    graph.put(words[i - 1].charAt(j), adjacencyList);
                    break;
                }
            }
        }
        return topologicalSort(graph);
    }

    public static LinkedList<Character> topologicalSort(MyGraph<Character> graph) {
        HashSet<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < 26; i++) {
            char root = (char) ((int) 'a' + i);
            if (visited.contains(root)) {
                continue;
            }
            if (!graph.containsKey(root)) {
                continue;
            }
            topologicalSortUtil(stack, graph, root, visited);
        }
        LinkedList<Character> answer = new LinkedList<>();
        while (!stack.isEmpty()) {
            answer.add(stack.pop());
        }
        return answer;
    }

    public static void topologicalSortUtil(Stack<Character> stack,
                                           MyGraph<Character> graph,
                                           char root,
                                           HashSet<Character> visited) {
        visited.add(root);
        LinkedList<Character> adjacencyList = graph.get(root);
        if (adjacencyList != null) {
            for (Character c : adjacencyList) {
                if (visited.contains(c)) {
                    continue;
                }
                topologicalSortUtil(stack, graph, c, visited);
            }
        }
        stack.add(root);
    }

    public static class MyGraph<T> extends HashMap<T, LinkedList<T>> {

    }
}
