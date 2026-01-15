package readability.api;

import java.util.List;

public class HistoryResponse {
    private final List<HistoryItem> results;

    public HistoryResponse(List<HistoryItem> results) {
        this.results = results;
    }

    public List<HistoryItem> getResults() {
        return results;
    }
}
