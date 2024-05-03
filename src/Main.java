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
    
    public static void print_information(List<String> path, long exec, int node_number) {
        long convert = TimeUnit.MILLISECONDS.convert(exec, TimeUnit.NANOSECONDS);

        System.out.print("The path is : ");
        System.out.println(path.toString());
        System.out.printf("The execution time : ");
        System.out.print(convert + " ms.\n");
        System.out.printf("The node visited number : ");
        System.out.print(node_number + " nodes.\n");
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

            boolean found = false;
            switch (choice) {
                case 1:
                    WordLadderUCS solverUCS = new WordLadderUCS(new ArrayList<>(), 0, 0);
                    solverUCS.find_path_solution_UCS(starting_word, ending_word);
                    if (!solverUCS.getPath().isEmpty()) {
                        print_information(solverUCS.getPath(), solverUCS.getExecutionTime(), solverUCS.getNodesVisited());
                        found = true;
                    }
                    break;
                case 2:
                    WordLadderGBFS solverGBFS = new WordLadderGBFS(new ArrayList<>(), 0, 0);
                    solverGBFS.find_path_solution_GBFS(starting_word, ending_word);
                    if (!solverGBFS.getPath().isEmpty()) {
                        print_information(solverGBFS.getPath(), solverGBFS.getExecutionTime(), solverGBFS.getNodesVisited());
                        found = true;
                    }
                    break;
                case 3:
                    WordLadderAStar solverAStar = new WordLadderAStar(new ArrayList<>(), 0, 0);
                    solverAStar.find_path_solution_AStar(starting_word, ending_word);
                    if (!solverAStar.getPath().isEmpty()) {
                        print_information(solverAStar.getPath(), solverAStar.getExecutionTime(), solverAStar.getNodesVisited());
                        found = true;
                    }
                    break;
            }

            if (!found) {
                System.out.println("Path not found. So sadd :(");
            }
        }

        scan.close();
    }
}
