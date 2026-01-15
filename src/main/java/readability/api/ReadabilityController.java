package readability.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import readability.model.Counts;
import readability.model.ScoreDetail;
import readability.model.ScoreType;
import readability.persistence.ReadabilityResult;
import readability.service.ReadabilityService;

@RestController
@RequestMapping("/api/readability")
public class ReadabilityController {
    private final ReadabilityService readabilityService;

    public ReadabilityController(ReadabilityService readabilityService) {
        this.readabilityService = readabilityService;
    }

    @PostMapping
    public ReadabilityResponse analyze(@Valid @RequestBody ReadabilityRequest request) {
        ReadabilityResult stored = readabilityService.analyzeAndStore(request.getText());
        return toResponse(stored, request.getScores());
    }

    @PostMapping("/batch")
    public BatchReadabilityResponse analyzeBatch(@Valid @RequestBody BatchReadabilityRequest request) {
        List<ReadabilityResponse> responses = new ArrayList<>();
        for (ReadabilityRequest item : request.getItems()) {
            ReadabilityResult stored = readabilityService.analyzeAndStore(item.getText());
            responses.add(toResponse(stored, item.getScores()));
        }
        return new BatchReadabilityResponse(responses);
    }

    @GetMapping("/history")
    public HistoryResponse history() {
        List<HistoryItem> historyItems = new ArrayList<>();
        for (ReadabilityResult result : readabilityService.getHistory()) {
            historyItems.add(toHistoryItem(result));
        }
        return new HistoryResponse(historyItems);
    }

    private ReadabilityResponse toResponse(ReadabilityResult result, List<ScoreType> requestedScores) {
        Counts counts = new Counts(result.getWordCount(),
                result.getSentenceCount(),
                result.getCharacterCount(),
                result.getSyllableCount(),
                result.getPolysyllableCount());
        Map<String, ScoreDetail> selectedScores = selectScores(result, requestedScores);
        double averageAge = selectedScores.values().stream()
                .mapToInt(ScoreDetail::getAge)
                .average()
                .orElse(0);
        return new ReadabilityResponse(result.getId(), counts, selectedScores, averageAge);
    }

    private HistoryItem toHistoryItem(ReadabilityResult result) {
        Counts counts = new Counts(result.getWordCount(),
                result.getSentenceCount(),
                result.getCharacterCount(),
                result.getSyllableCount(),
                result.getPolysyllableCount());
        Map<String, ScoreDetail> allScores = selectScores(result, Collections.singletonList(ScoreType.ALL));
        double averageAge = allScores.values().stream()
                .mapToInt(ScoreDetail::getAge)
                .average()
                .orElse(0);
        return new HistoryItem(result.getId(), result.getText(), counts, allScores, averageAge, result.getCreatedAt());
    }

    private Map<String, ScoreDetail> selectScores(ReadabilityResult result, List<ScoreType> requestedScores) {
        List<ScoreType> normalized = normalizeScores(requestedScores);
        Map<String, ScoreDetail> scores = new LinkedHashMap<>();
        for (ScoreType scoreType : normalized) {
            switch (scoreType) {
                case ARI:
                    scores.put("ARI", new ScoreDetail(result.getAriScore(), result.getAriAge()));
                    break;
                case FK:
                    scores.put("FK", new ScoreDetail(result.getFkScore(), result.getFkAge()));
                    break;
                case SMOG:
                    scores.put("SMOG", new ScoreDetail(result.getSmogScore(), result.getSmogAge()));
                    break;
                case CL:
                    scores.put("CL", new ScoreDetail(result.getClScore(), result.getClAge()));
                    break;
                default:
                    break;
            }
        }
        return scores;
    }

    private List<ScoreType> normalizeScores(List<ScoreType> requestedScores) {
        if (requestedScores == null || requestedScores.isEmpty() || requestedScores.contains(ScoreType.ALL)) {
            return new ArrayList<>(EnumSet.of(ScoreType.ARI, ScoreType.FK, ScoreType.SMOG, ScoreType.CL));
        }
        LinkedHashSet<ScoreType> uniqueScores = new LinkedHashSet<>(requestedScores);
        uniqueScores.remove(ScoreType.ALL);
        return new ArrayList<>(uniqueScores);
    }
}
