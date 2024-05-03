package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionary {
    private static HashSet<String> words = new HashSet<>();

    public static void load_word(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Failed to load dictionary: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean word_valid_checker(String word) {
        return words.contains(word);
    }

    public static void print_dictionary() {
        System.out.println(words.toString());
    }
}
