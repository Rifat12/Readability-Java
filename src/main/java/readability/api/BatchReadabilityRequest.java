package readability.api;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class BatchReadabilityRequest {
    @NotEmpty
    @Valid
    private List<ReadabilityRequest> items;

    public BatchReadabilityRequest() {
    }

    public BatchReadabilityRequest(List<ReadabilityRequest> items) {
        this.items = items;
    }

    public List<ReadabilityRequest> getItems() {
        return items;
    }

    public void setItems(List<ReadabilityRequest> items) {
        this.items = items;
    }
}
