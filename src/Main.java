import java.util.*;
import java.util.concurrent.TimeUnit;

import algorithm.WordLadderAStar;
import algorithm.WordLadderGBFS;
import algorithm.WordLadderUCS;
import utils.Dictionary;

public class Main {
    public static void print_choice() {
        System.out.println("Choose which algorithm you gotta use to solve Word Ladder Game !");
        System.out.println("1. UCS (Uniformed Cost Search) Algorithm");
        System.out.println("2. Greedy Best First Search Algorithm");
        System.out.println("3. A-Star Algorithm");
        System.out.println("4. Exit program");
    }
    
    public static String get_information(List<String> path, long exec, int node_number) {
        long convert = TimeUnit.MILLISECONDS.convert(exec, TimeUnit.NANOSECONDS);
        String resultText = "";
        if (path.isEmpty()) {
            resultText += "No path found\n";
        } else {
            resultText += "Result path: " + path.toString() + "\n";
        }
        resultText += "Execution time: " + convert + " ms.\n";
        resultText += "Total node visited: " + node_number + " nodes.\n";
        return resultText;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Dictionary.load_word("./utils/words.txt");

        while (true) {
            String starting_word = "";
            String ending_word = "";

            boolean valid_input = false;
            while (!valid_input) {
                System.out.println("Enter the starting word:");
                if (scan.hasNext()) {
                    starting_word = scan.next();
                    if (Dictionary.word_valid_checker(starting_word)) {
                        valid_input = true;
                    } else {
                        System.out.println("Word is not found in dictionary. Input a valid english word.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid word.");
                    scan.next();
                }
            }

            valid_input = false;
            while (!valid_input) {
                System.out.println("Enter the ending word:");
                if (scan.hasNext()) {
                    ending_word = scan.next();
                    if (Dictionary.word_valid_checker(ending_word)) {
                        valid_input = true;
                    } else {
                        System.out.println("Word is not found in dictionary. Input a valid english word.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid word.");
                    scan.next();
                }
            }

            if (starting_word.length() != ending_word.length()) {
                System.out.println("Words must be of the same length.");
                continue;
            }

            print_choice();

            System.out.print(">> ");
            int choice;
            try {
                choice = scan.nextInt();
                if (choice == 4) {
                    break;
                } else if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scan.next();
                continue;
            }

            switch (choice) {
                case 1:
                    WordLadderUCS solverUCS = new WordLadderUCS(new ArrayList<>(), 0, 0);
                    solverUCS.find_path_solution_UCS(starting_word, ending_word);
                    System.out.println(get_information(solverUCS.getPath(), solverUCS.getExecutionTime(), solverUCS.getNodesVisited()));
                    break;
                case 2:
                    WordLadderGBFS solverGBFS = new WordLadderGBFS(new ArrayList<>(), 0, 0);
                    solverGBFS.find_path_solution_GBFS(starting_word, ending_word);
                    System.out.println(get_information(solverGBFS.getPath(), solverGBFS.getExecutionTime(), solverGBFS.getNodesVisited()));
                    break;
                case 3:
                    WordLadderAStar solverAStar = new WordLadderAStar(new ArrayList<>(), 0, 0);
                    solverAStar.find_path_solution_AStar(starting_word, ending_word);
                    System.out.println(get_information(solverAStar.getPath(), solverAStar.getExecutionTime(), solverAStar.getNodesVisited()));
                    break;
            }
        }

        scan.close();
    }
}
