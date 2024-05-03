package utils;

import java.util.ArrayList;
import java.util.List;

public interface Utils {
    default List<String> find_word_possibility(String word) {
        List<String> valid_words = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != original) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (Dictionary.word_valid_checker(newWord)) {
                        valid_words.add(newWord);
                    }
                }
            }
            chars[i] = original;
        }
        return valid_words;
    }

    default int count_mismatch_letter(String current, String target) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != target.charAt(i)) {
                count += 1;
            }
        }
        return count;
    }

    default int count_same_letter(String current, String target) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) == target.charAt(i)) {
                count += 1;
            }
        }
        return count;
    }
}