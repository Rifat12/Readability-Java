package readability.model;

public class ScoreDetail {
    private final double score;
    private final int age;

    public ScoreDetail(double score, int age) {
        this.score = score;
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public int getAge() {
        return age;
    }
}
