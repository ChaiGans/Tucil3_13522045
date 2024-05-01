import java.util.*;
import wordladderucs.WordLadderUCS;
import utils.Dictionary;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String starting_word = "";
        String ending_word = "";

        System.out.println("Enter the starting word:");
        starting_word = scan.next();

        System.out.println("Enter the ending word:");
        ending_word = scan.next();

        if (starting_word.length() != ending_word.length()) {
            System.out.println("Words must be of the same length.");
            scan.close();
            return;
        }

        Dictionary dictionary = new Dictionary("./utils/words_alpha.txt");
        // dictionary.print_dictionary();
        WordLadderUCS solver = new WordLadderUCS(new ArrayList<>(), 0, 0, dictionary);
        solver.find_path_solution_GBFS(starting_word, ending_word);
        if (solver.getPath().isEmpty()) {
            System.out.println("No path found from " + starting_word + " to " + ending_word);
        } else {
            System.out.println("Path found:");
            solver.print_information();
        }
        scan.close();
    }
}
