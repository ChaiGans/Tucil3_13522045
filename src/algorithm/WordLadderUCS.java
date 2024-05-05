package algorithm;

import java.util.*;
import utils.*;

public class WordLadderUCS extends WordLadder implements Utils {

    public WordLadderUCS(List<String> path, long executionTime, int nodesVisited) {
        super(path, executionTime, nodesVisited);
    }

    public void print_queue(Queue<Node> queue) {
        Queue<Node> tempQueue = new PriorityQueue<>(queue);
        tempQueue.addAll(queue);    

        while (!tempQueue.isEmpty()) {
            Node node = tempQueue.poll();
            System.out.print(node.word + " ");
            System.out.print(node.depth + " ");
        }
        System.out.println();
    }

    public void find_path_solution_UCS(String starting_word, String target_word) {
        long startTime = System.nanoTime();
        if (starting_word.length() != target_word.length()) {
            this.setValue(new ArrayList<>(), 0, 0);
            return;
        }

        Queue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.depth));
        Map<String, Boolean> visited = new HashMap<>();
        int nodesVisited = 0;

        priorityQueue.add(new Node(starting_word, null, 0));

        while (!priorityQueue.isEmpty()) {
            Node current_node = priorityQueue.poll();
            nodesVisited++;

            if (current_node.word.equals(target_word)) {
                List<String> temp = make_path_from_node(current_node);
                long endTime = System.nanoTime();
                this.setValue(temp, endTime - startTime, nodesVisited);
                return;
            }

            for (String neighbor : find_word_possibility(current_node.word)) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, true);
                    priorityQueue.add(new Node(neighbor, current_node, current_node.depth + 1));
                }
            }
        }

        long endTime = System.nanoTime();
        this.setValue(new ArrayList<>(), endTime - startTime, nodesVisited);
    }

    
    private List<String> make_path_from_node(Node current) {
        List<String> path = new ArrayList<>();
        while (current != null) {
            path.add(0, current.word);
            current = current.parent;
        }
        return path;
    }

    class Node {
        String word;
        Node parent;
        int depth;

        public Node(String word, Node parent, int depth) {
            this.word = word;
            this.parent = parent;
            this.depth = depth;
        }
    }
}
