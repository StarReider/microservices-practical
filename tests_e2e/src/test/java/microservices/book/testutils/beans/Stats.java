package microservices.book.testutils.beans;

import java.util.List;

public class Stats {
	private Long userId;
	private Integer score;
	private List<String> badges;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}

	public List<String> getBadges() {
		return badges;
	}

	public void setBadges(List<String> badges) {
		this.badges = badges;
	}		
}