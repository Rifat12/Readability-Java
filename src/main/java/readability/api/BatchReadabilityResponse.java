package readability.api;

import java.util.List;

public class BatchReadabilityResponse {
    private final List<ReadabilityResponse> results;

    public BatchReadabilityResponse(List<ReadabilityResponse> results) {
        this.results = results;
    }

    public List<ReadabilityResponse> getResults() {
        return results;
    }
}
