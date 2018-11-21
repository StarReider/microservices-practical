package microservices.book.multiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.service.MultiplicationService;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	private final MultiplicationService multiplicationService;
	private final int serverPort;
	
	@Autowired
	public UserController(final MultiplicationService multiplicationService, @Value("${server.port}") int serverPort) {
		this.multiplicationService = multiplicationService;
		this.serverPort = serverPort;
	}
	
	@GetMapping("/{userId}")
	User getUser(@PathVariable("userId") final Long userId) {
		log.info("Retrieving user {} from server @ {}", userId, serverPort);
		return multiplicationService.getUserById(userId);
	}
}