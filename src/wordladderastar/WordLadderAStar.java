package wordladderastar;

import utils.Dictionary;
import java.util.*;

public class WordLadderAStar {
    private List<String> path;
    private long executionTime;
    private int nodesVisited;
    private Dictionary dictionary;

    public WordLadderAStar(List<String> path, long executionTime, int nodesVisited, Dictionary dictionary) {
        this.path = path;
        this.executionTime = executionTime;
        this.nodesVisited = nodesVisited;
        this.dictionary = dictionary;
    }

     public List<String> getPath() {
        return path;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public void setValue(List<String> path, long executionTime, int nodesVisited) {
        this.path = path;
        this.executionTime = executionTime;
        this.nodesVisited = nodesVisited;
    }

    public void reset() {
        this.path = new ArrayList<>();
        this.nodesVisited = 0;
        this.executionTime = 0;
    }

    private List<String> find_word_possibility(String word) {
        List<String> valid_words = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != original) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (dictionary.word_valid_checker(newWord)) {
                        valid_words.add(newWord);
                    }
                }
            }
            chars[i] = original;
        }
        return valid_words;
    }

    public int count_mismatch_letter(String current, String target) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != target.charAt(i)) {
                count += 1;
            }
        }
        return count;
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
        Map<String, Integer> f_score = new HashMap<>();

        g_score.put(starting_word, 0);
        f_score.put(starting_word, count_mismatch_letter(starting_word, target_word));

        priority_queue.add(new Node(starting_word, null, 0, f_score.get(starting_word)));

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
                int temp_g_score = g_score.get(current_node.word) + 1;
                if (temp_g_score < g_score.getOrDefault(children, Integer.MAX_VALUE)) {
                    g_score.put(children, temp_g_score);
                    f_score.put(children, temp_g_score + count_mismatch_letter(children, target_word));
                    if (!priority_queue.contains(new Node(children, current_node, temp_g_score, f_score.get(children)))) {
                        priority_queue.add(new Node(children, current_node, temp_g_score, f_score.get(children)));
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