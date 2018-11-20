package microservices.book.testutils.beans;

public class AttemptResponse {
	private Long id;
	private User user;
	private int resultAttempt;
	private boolean correct;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getResultAttempt() {
		return resultAttempt;
	}
	
	public void setResultAttempt(int resultAttempt) {
		this.resultAttempt = resultAttempt;
	}
	
	public boolean isCorrect() {
		return correct;
	}
	
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}		
}