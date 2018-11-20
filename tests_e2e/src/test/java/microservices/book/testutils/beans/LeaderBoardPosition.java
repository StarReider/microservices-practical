package microservices.book.testutils.beans;

public class LeaderBoardPosition {
	private Long userId;
	private Long totalScore;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getTotalScore() {
		return totalScore;
	}
	
	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}	
}