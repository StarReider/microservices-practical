package microservices.book.gamification.service;

import org.springframework.stereotype.Service;

import microservices.book.gamification.domain.GameStats;

@Service
public class GameServiceImpl implements GameService {

	@Override
	public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameStats retrieveStatsForUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}