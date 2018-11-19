package microservices.book.multiplication.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import microservices.book.multiplication.repository.MultiplicationRepository;
import microservices.book.multiplication.repository.UserRepository;

@Profile("test")
@Service
public class AdminServiceImpl implements AdminService {

	private MultiplicationRepository multiplicationRepository;
    private UserRepository userRepository;
    
    public AdminServiceImpl(final MultiplicationRepository multiplicationRepository, final UserRepository userRepository) {
    	this.multiplicationRepository = multiplicationRepository;
    	this.userRepository = userRepository;
    }
    
	@Override
	public void deleteDatabaseContents() {
		multiplicationRepository.deleteAll();
		userRepository.deleteAll();
	}
}