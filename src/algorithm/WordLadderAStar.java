package algorithm;

import utils.Utils;
import java.util.*;

public class WordLadderAStar extends WordLadder implements Utils {

    public WordLadderAStar(List<String> path, long executionTime, int nodesVisited) {
        super(path, executionTime, nodesVisited);
    }

    public void print_queue(Queue<Node> queue) {
        Queue<Node> tempQueue = new PriorityQueue<>(queue);
        tempQueue.addAll(queue);    

        while (!tempQueue.isEmpty()) {
            Node node = tempQueue.poll();
            System.out.print(node.word + " "); 
            System.out.print("f" + node.f);
            System.out.print("g" + node.g);
        }
        System.out.println();
    }

    private List<String> make_path_from_node(Node current) {
        List<String> path = new ArrayList<>();
        while (current != null) {
            path.add(0, current.word);
            current = current.parent;
        }
        return path;
    }

    public void find_path_solution_AStar(String starting_word, String target_word) {
        long startTime = System.nanoTime();
        if (starting_word.length() != target_word.length()) {
            this.setValue(new ArrayList<>(), 0, 0);
            return;
        }

        PriorityQueue<Node> priority_queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
        Map<String, Integer> g_score = new HashMap<>();

        g_score.put(starting_word, 0);

        priority_queue.add(new Node(starting_word, null, 0, count_mismatch_letter(starting_word, target_word)));

        while (!priority_queue.isEmpty()) {
            Node current_node = priority_queue.poll();
            // print_queue(priority_queue);
            nodesVisited++;

            if (current_node.word.equals(target_word)) {
                long endTime = System.nanoTime();
                this.setValue(make_path_from_node(current_node), endTime - startTime, nodesVisited);
                return;
            }

            for (String children : find_word_possibility(current_node.word)) {
                int temp_g_score = current_node.g + 1;
                if (temp_g_score < g_score.getOrDefault(children, Integer.MAX_VALUE)) {
                    g_score.put(children, temp_g_score);
                    int f_count = temp_g_score + count_mismatch_letter(children, target_word);
                    if (!priority_queue.contains(new Node(children, current_node, temp_g_score, f_count))) {
                        priority_queue.add(new Node(children, current_node, temp_g_score, f_count));
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        this.setValue(new ArrayList<>(), endTime - startTime, nodesVisited);
    }

    class Node {
        String word;
        Node parent;
        int g; // Cost dari start hingga node yang ini
        int f; // Cost g + (heuristic mismatch count)

        public Node(String word, Node parent, int g, int f) {
            this.word = word;
            this.parent = parent;
            this.g = g;
            this.f = f;
        }
    }
}
