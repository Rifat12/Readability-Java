package readability;

import java.util.ArrayList;
import java.util.List;

public class Words {

    private final String text;
    private final String[] words;
    List<Integer> list;

    public Words(String text) {
        this.text = text;
        words = text.split(" ");
    }

    public int wordCount() {
        return words.length;
    }

    public int sentenceCount() {
        list = new ArrayList<>();
        int count = 0;
        for (String s : words) {
            if (s.endsWith("!") || s.endsWith(".") || s.endsWith("?") || s.equals(words[words.length - 1])) {
                count += 1;
            }
        }
        return count;
    }

    public int characterCount() {
        char[] array = text.toCharArray();
        int count = 0;
        for (char c : array) {
            if (c != ' ') {
                count += 1;
            }
        }
        return count;
    }
}