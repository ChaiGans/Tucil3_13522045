package wordladderucs;

import utils.Dictionary;
import java.util.*;

public class WordLadderUCS {

    private List<String> path;
    private long executionTime;
    private int nodesVisited;
    private Dictionary dictionary;

    public WordLadderUCS(List<String> path, long executionTime, int nodesVisited, Dictionary dictionary) {
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

    public void print_information() {
        System.out.print("The path is : ");
        System.out.println(this.getPath().toString());
        System.out.printf("The execution time :  %d", this.getExecutionTime());
        System.out.printf("The node visited number : %d", this.getNodesVisited());
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

    public int count_same_letter(String current, String target) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) == target.charAt(i)) {
                count += 1;
            }
        }
        return count;
    }

    public void print_queue(Queue<Node> queue) {
        // Create a temporary queue to help in printing without altering the original queue
        Queue<Node> tempQueue = new PriorityQueue<>(queue);
        tempQueue.addAll(queue);    

        // Dequeue from the temporary queue to maintain priority order
        // System.out.println("Queue contents (in priority order):");
        while (!tempQueue.isEmpty()) {
            Node node = tempQueue.poll();
            System.out.print(node.word + " ");  // Print the word of each node
            System.out.print(node.depth + " ");
        }
        System.out.println();  // Move to a new line after printing all contents
    }


    public void find_path_solution_UCS(String starting_word, String target_word) {
        long startTime = System.nanoTime();
        if (starting_word.length() != target_word.length()) {
            this.setValue(new ArrayList<>(), 0, 0);
            return;
        }

        Queue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.depth));
        Map<String, Integer> visited = new HashMap<>();
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
                if (!visited.containsKey(neighbor) || visited.get(neighbor) > current_node.depth + 1) {
                    visited.put(neighbor, current_node.depth + 1);
                    priorityQueue.add(new Node(neighbor, current_node, current_node.depth + 1));
                }
            }
        }

        long endTime = System.nanoTime();
        this.setValue(new ArrayList<>(), endTime - startTime, nodesVisited);
    }

    public void find_path_solution_GBFS(String starting_word, String target_word) {
        long startTime = System.nanoTime();
        if (starting_word.length() != target_word.length()) {
            this.setValue(new ArrayList<>(), 0, 0);
            return;
        }

        Queue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> -count_same_letter(node.word, target_word)));
        Map<String, Integer> visited = new HashMap<>();
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
                if (!visited.containsKey(neighbor) || visited.get(neighbor) > current_node.depth + 1) {
                    visited.put(neighbor, current_node.depth + 1);
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
