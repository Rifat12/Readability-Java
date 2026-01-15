package readability.api;

import java.time.LocalDateTime;
import java.util.Map;

import readability.model.Counts;
import readability.model.ScoreDetail;

public class HistoryItem {
    private final Long id;
    private final String text;
    private final Counts counts;
    private final Map<String, ScoreDetail> scores;
    private final double averageAge;
    private final LocalDateTime createdAt;

    public HistoryItem(Long id,
                       String text,
                       Counts counts,
                       Map<String, ScoreDetail> scores,
                       double averageAge,
                       LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.counts = counts;
        this.scores = scores;
        this.averageAge = averageAge;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Counts getCounts() {
        return counts;
    }

    public Map<String, ScoreDetail> getScores() {
        return scores;
    }

    public double getAverageAge() {
        return averageAge;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
