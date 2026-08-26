/** 점수 범위 오류 — Unchecked (RuntimeException) */
public class InvalidScoreException extends RuntimeException {
    private int score;

    public InvalidScoreException(int score) {
        super("유효하지 않은 점수: " + score + " (0~100)");
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
