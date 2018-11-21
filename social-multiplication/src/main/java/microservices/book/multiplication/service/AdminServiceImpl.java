package microservices.book.multiplication.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import microservices.book.multiplication.repository.MultiplicationRepository;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;

@Profile("test")
@Service
public class AdminServiceImpl implements AdminService {

	private MultiplicationRepository multiplicationRepository;
    private UserRepository userRepository;
    private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;
    
    public AdminServiceImpl(final MultiplicationRepository multiplicationRepository, 
    						final UserRepository userRepository,
    						final MultiplicationResultAttemptRepository multiplicationResultAttemptRepository) {
    	this.multiplicationRepository = multiplicationRepository;
    	this.userRepository = userRepository;
    }
    
	@Override
	public void deleteDatabaseContents() {
		multiplicationResultAttemptRepository.deleteAll();
		multiplicationRepository.deleteAll();
		userRepository.deleteAll();
	}
}