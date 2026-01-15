package readability.service;

import java.util.EnumMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import readability.model.Counts;
import readability.model.ReadabilityAnalysis;
import readability.model.ScoreDetail;
import readability.model.ScoreType;

@Service
public class ReadabilityCalculator {
    private static final Pattern SYLLABLE_PATTERN = Pattern.compile("(?i)[aeiouy][aeiouy]*|e[aeiouy]*(?!d?\\b)");

    public ReadabilityAnalysis analyze(String text) {
        String trimmed = text == null ? "" : text.trim();
        String[] words = trimmed.isEmpty() ? new String[0] : trimmed.split("\\s+");

        int wordCount = words.length;
        int sentenceCount = countSentences(words);
        int characterCount = countCharacters(trimmed);
        int syllableCount = countSyllables(words);
        int polysyllableCount = countPolysyllables(words);

        Counts counts = new Counts(wordCount, sentenceCount, characterCount, syllableCount, polysyllableCount);
        double ari = calculateAri(counts);
        double fk = calculateFk(counts);
        double smog = calculateSmog(counts);
        double cl = calculateCl(counts);

        EnumMap<ScoreType, ScoreDetail> scores = new EnumMap<>(ScoreType.class);
        scores.put(ScoreType.ARI, new ScoreDetail(ari, ageFromScore(ari)));
        scores.put(ScoreType.FK, new ScoreDetail(fk, ageFromScore(fk)));
        scores.put(ScoreType.SMOG, new ScoreDetail(smog, ageFromScore(smog)));
        scores.put(ScoreType.CL, new ScoreDetail(cl, ageFromScore(cl)));

        return new ReadabilityAnalysis(counts, scores);
    }

    private int countSentences(String[] words) {
        if (words.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean isLastWord = i == words.length - 1;
            if (word.endsWith("!") || word.endsWith(".") || word.endsWith("?") || isLastWord) {
                count++;
            }
        }
        return count;
    }

    private int countCharacters(String text) {
        int count = 0;
        for (char character : text.toCharArray()) {
            if (!Character.isWhitespace(character)) {
                count++;
            }
        }
        return count;
    }

    private int countSyllables(String[] words) {
        int count = 0;
        for (String word : words) {
            count += countSyllablesInWord(word);
        }
        return count;
    }

    private int countPolysyllables(String[] words) {
        int count = 0;
        for (String word : words) {
            if (countSyllablesInWord(word) > 2) {
                count++;
            }
        }
        return count;
    }

    private int countSyllablesInWord(String word) {
        Matcher matcher = SYLLABLE_PATTERN.matcher(word.toLowerCase(Locale.US));
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        if (word.endsWith("e")) {
            return Math.max(count, 1) - 1;
        }
        return Math.max(count, 1);
    }

    private double calculateAri(Counts counts) {
        if (counts.getWords() == 0 || counts.getSentences() == 0) {
            return 0;
        }
        return (4.71 * ((double) counts.getCharacters() / counts.getWords()))
                + (0.5 * ((double) counts.getWords() / counts.getSentences()))
                - 21.43;
    }

    private double calculateFk(Counts counts) {
        if (counts.getWords() == 0 || counts.getSentences() == 0) {
            return 0;
        }
        return (0.39 * ((double) counts.getWords() / counts.getSentences()))
                + (11.8 * ((double) counts.getSyllables() / counts.getWords()))
                - 15.59;
    }

    private double calculateSmog(Counts counts) {
        if (counts.getSentences() == 0) {
            return 0;
        }
        return (1.043 * Math.sqrt(counts.getPolysyllables() * (30.0 / counts.getSentences()))) + 3.1291;
    }

    private double calculateCl(Counts counts) {
        if (counts.getWords() == 0) {
            return 0;
        }
        return (0.0588 * ((double) counts.getCharacters() / counts.getWords() * 100))
                - (0.296 * ((double) counts.getSentences() / counts.getWords() * 100))
                - 15.8;
    }

    private int ageFromScore(double score) {
        int level = (int) Math.round(score);
        switch (level) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
                return 24;
            default:
                return 0;
        }
    }
}
