package readability.model;

public class Counts {
    private final int words;
    private final int sentences;
    private final int characters;
    private final int syllables;
    private final int polysyllables;

    public Counts(int words, int sentences, int characters, int syllables, int polysyllables) {
        this.words = words;
        this.sentences = sentences;
        this.characters = characters;
        this.syllables = syllables;
        this.polysyllables = polysyllables;
    }

    public int getWords() {
        return words;
    }

    public int getSentences() {
        return sentences;
    }

    public int getCharacters() {
        return characters;
    }

    public int getSyllables() {
        return syllables;
    }

    public int getPolysyllables() {
        return polysyllables;
    }
}
