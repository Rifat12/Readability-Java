package readability.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import readability.model.ReadabilityAnalysis;
import readability.model.ScoreType;
import readability.persistence.ReadabilityResult;
import readability.persistence.ReadabilityResultRepository;

@Service
public class ReadabilityService {
    private final ReadabilityCalculator calculator;
    private final ReadabilityResultRepository repository;

    public ReadabilityService(ReadabilityCalculator calculator, ReadabilityResultRepository repository) {
        this.calculator = calculator;
        this.repository = repository;
    }

    public ReadabilityResult analyzeAndStore(String text) {
        ReadabilityAnalysis analysis = calculator.analyze(text);
        ReadabilityResult entity = new ReadabilityResult(
                text,
                analysis.getCounts().getWords(),
                analysis.getCounts().getSentences(),
                analysis.getCounts().getCharacters(),
                analysis.getCounts().getSyllables(),
                analysis.getCounts().getPolysyllables(),
                analysis.getScores().get(ScoreType.ARI).getScore(),
                analysis.getScores().get(ScoreType.FK).getScore(),
                analysis.getScores().get(ScoreType.SMOG).getScore(),
                analysis.getScores().get(ScoreType.CL).getScore(),
                analysis.getScores().get(ScoreType.ARI).getAge(),
                analysis.getScores().get(ScoreType.FK).getAge(),
                analysis.getScores().get(ScoreType.SMOG).getAge(),
                analysis.getScores().get(ScoreType.CL).getAge(),
                LocalDateTime.now());
        return repository.save(entity);
    }

    public List<ReadabilityResult> getHistory() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
