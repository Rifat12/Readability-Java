package readability.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "readability_results")
public class ReadabilityResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String text;

    private int wordCount;
    private int sentenceCount;
    private int characterCount;
    private int syllableCount;
    private int polysyllableCount;

    private double ariScore;
    private double fkScore;
    private double smogScore;
    private double clScore;

    private int ariAge;
    private int fkAge;
    private int smogAge;
    private int clAge;

    private LocalDateTime createdAt;

    protected ReadabilityResult() {
    }

    public ReadabilityResult(String text,
                             int wordCount,
                             int sentenceCount,
                             int characterCount,
                             int syllableCount,
                             int polysyllableCount,
                             double ariScore,
                             double fkScore,
                             double smogScore,
                             double clScore,
                             int ariAge,
                             int fkAge,
                             int smogAge,
                             int clAge,
                             LocalDateTime createdAt) {
        this.text = text;
        this.wordCount = wordCount;
        this.sentenceCount = sentenceCount;
        this.characterCount = characterCount;
        this.syllableCount = syllableCount;
        this.polysyllableCount = polysyllableCount;
        this.ariScore = ariScore;
        this.fkScore = fkScore;
        this.smogScore = smogScore;
        this.clScore = clScore;
        this.ariAge = ariAge;
        this.fkAge = fkAge;
        this.smogAge = smogAge;
        this.clAge = clAge;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public int getSyllableCount() {
        return syllableCount;
    }

    public int getPolysyllableCount() {
        return polysyllableCount;
    }

    public double getAriScore() {
        return ariScore;
    }

    public double getFkScore() {
        return fkScore;
    }

    public double getSmogScore() {
        return smogScore;
    }

    public double getClScore() {
        return clScore;
    }

    public int getAriAge() {
        return ariAge;
    }

    public int getFkAge() {
        return fkAge;
    }

    public int getSmogAge() {
        return smogAge;
    }

    public int getClAge() {
        return clAge;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
