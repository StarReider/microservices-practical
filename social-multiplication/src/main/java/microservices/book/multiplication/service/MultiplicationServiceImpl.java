package microservices.book.multiplication.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	private RandomGeneratorService randomGeneratorService;
	private MultiplicationResultAttemptRepository attemptRepository;
	private UserRepository userRepository;
	
	@Autowired
	public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
			final MultiplicationResultAttemptRepository attemptRepository,
			final UserRepository userRepository) {
				this.randomGeneratorService = randomGeneratorService;
				this.attemptRepository = attemptRepository;
				this.userRepository = userRepository;
	}
	
	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();
		
		return new Multiplication(factorA, factorB);
	}

	@Transactional
	@Override
	public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
		
		// Check if the user already exists for that alias
		Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());
		
		// Avoids 'hack' attempts
		Assert.isTrue(!resultAttempt.isCorrect(), "You can't send an attempt marked as correct!!");		
		
		// Checks if it's correct
		boolean correct =  resultAttempt.getResultAttempt() ==
				resultAttempt.getMultiplication().getFactorA() *
				resultAttempt.getMultiplication().getFactorB();
		
		// Creates a copy, now setting the 'correct' field accordingly
		MultiplicationResultAttempt checkedAttempt =
				new MultiplicationResultAttempt(user.orElse(resultAttempt.getUser()),
						resultAttempt.getMultiplication(),
						resultAttempt.getResultAttempt(),
						correct);
		
		// Stores the attempt
		attemptRepository.save(checkedAttempt);
		
		// Returns the result		
		return correct;
	}
}