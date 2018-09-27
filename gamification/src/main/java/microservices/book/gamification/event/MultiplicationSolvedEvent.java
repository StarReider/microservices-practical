package microservices.book.gamification.event;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
* Event that models the fact that a {@link microservices.book.multiplication.domain.Multiplication}
* has been solved in the system. Provides some context information about the multiplication.
*/
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {
	
	private static final long serialVersionUID = -7081602888477238800L;
	
	private final Long multiplicationResultAttemptId;
	private final Long userId;
	private final boolean correct;
	
	@JsonCreator
    public MultiplicationSolvedEvent(@JsonProperty("multiplicationResultAttemptId") Long multiplicationResultAttemptId,
    		@JsonProperty("userId") Long userId,
    		@JsonProperty("correct") boolean correct) {
		this.multiplicationResultAttemptId = multiplicationResultAttemptId;
		this.userId = userId;
        this.correct = correct;
    } 
}