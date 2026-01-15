package readability.model;

import java.util.EnumMap;
import java.util.Map;

public class ReadabilityAnalysis {
    private final Counts counts;
    private final EnumMap<ScoreType, ScoreDetail> scores;

    public ReadabilityAnalysis(Counts counts, EnumMap<ScoreType, ScoreDetail> scores) {
        this.counts = counts;
        this.scores = scores;
    }

    public Counts getCounts() {
        return counts;
    }

    public Map<ScoreType, ScoreDetail> getScores() {
        return scores;
    }
}
