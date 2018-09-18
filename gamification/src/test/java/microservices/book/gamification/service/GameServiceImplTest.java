package microservices.book.gamification.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import microservices.book.gamification.domain.Badge;
import microservices.book.gamification.domain.GameStats;
import microservices.book.gamification.domain.ScoreCard;
import microservices.book.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.repository.ScoreCardRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collections;

public class GameServiceImplTest {

	private GameServiceImpl gameService;
	
	@Mock
    private ScoreCardRepository scoreCardRepository;

    @Mock
    private BadgeCardRepository badgeCardRepository;
	
	@Before
    public void setUp() {
		
		// With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void processFirstCorrectAttemptTest() {
		
		// given
        Long userId = 1L;
        Long attemptId = 8L;
        int totalScore = 10;
        ScoreCard scoreCard = new ScoreCard(userId, attemptId);
        
        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(totalScore);
        
        given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId))
        	.willReturn(Collections.singletonList(scoreCard));
        
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
        	.willReturn(Collections.emptyList());
        
        // when
		GameStats iteration = gameService.newAttemptForUser(userId, attemptId, true);
		
		// assert - should score one card and win the badge FIRST_WON
        assertThat(iteration.getScore()).isEqualTo(scoreCard.getScore());
        assertThat(iteration.getBadges()).containsOnly(Badge.FIRST_WON);
	}
}