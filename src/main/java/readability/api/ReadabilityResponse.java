package readability.api;

import java.util.Map;

import readability.model.Counts;
import readability.model.ScoreDetail;

public class ReadabilityResponse {
    private final Long id;
    private final Counts counts;
    private final Map<String, ScoreDetail> scores;
    private final double averageAge;

    public ReadabilityResponse(Long id, Counts counts, Map<String, ScoreDetail> scores, double averageAge) {
        this.id = id;
        this.counts = counts;
        this.scores = scores;
        this.averageAge = averageAge;
    }

    public Long getId() {
        return id;
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
}
