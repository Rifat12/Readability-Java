package readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Syllables {
    private String[] strings;
    private int count;
    private int poly;

    public Syllables(String[] strings) {
        this.strings = strings;
        this.count = 0;
        this.poly = 0;
    }

    public int countSyllables() {
        for (String s : this.strings) {
            count += countWithRegex(s);
            if (countWithRegex(s) > 2)
                poly += 1;
        }
        return count;
    }

    public int countPoly() {
        return poly;
    }

    private static int countWithRegex(String word) {
        String i = "(?i)[aeiouy][aeiouy]*|e[aeiouy]*(?!d?\\b)";
        Matcher m = Pattern.compile(i).matcher(word);
        int count = 0;

        while (m.find()) {
            count++;
        }
        if (word.endsWith("e")) {
            return Math.max(count, 1) - 1;
        } else {
            return Math.max(count, 1);
        }
    }
}