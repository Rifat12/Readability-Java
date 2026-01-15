package readability.api;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import readability.model.ScoreType;

public class ReadabilityRequest {
    @NotBlank
    private String text;
    private List<ScoreType> scores;

    public ReadabilityRequest() {
    }

    public ReadabilityRequest(String text, List<ScoreType> scores) {
        this.text = text;
        this.scores = scores;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ScoreType> getScores() {
        return scores;
    }

    public void setScores(List<ScoreType> scores) {
        this.scores = scores;
    }
}
