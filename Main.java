package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        String text = readFileasString(args[0]);
        Words words = new Words(text);
        Syllables syllables = new Syllables(text.split(" "));

        System.out.println(text + "\n");
        new Automated(words.wordCount(), words.sentenceCount(), words.characterCount(), syllables.countSyllables(),
                syllables.countPoly()).printAll();

    }

    public static String readFileasString(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
}