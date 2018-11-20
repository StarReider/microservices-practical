package microservices.book.testutils.beans;

public class ScoreResponse {
	private Long cardId;
	private Long userId;
	private Long attemptId;
	private long scoreTimestamp;
	private int score;
	
	public ScoreResponse(int score) {
		this.score = score;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}

	public long getScoreTimestamp() {
		return scoreTimestamp;
	}

	public void setScoreTimestamp(long scoreTimestamp) {
		this.scoreTimestamp = scoreTimestamp;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}