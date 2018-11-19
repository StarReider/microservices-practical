package microservices.book.gamification.service;

public interface AdminService {

	/**
	 * This service provides methods to an administrator of the application to perform some high risk operations.
	 * It should only be used when the application is being tested, never during runtime.
	 *
	 */
	void deleteDatabaseContents();
}