package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptService {
	
	MultiplicationResultAttempt getMultiplicationResultAttempt(final Long attemptId);
}
