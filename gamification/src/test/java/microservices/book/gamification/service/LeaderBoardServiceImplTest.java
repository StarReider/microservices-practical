package microservices.book.gamification.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices.book.gamification.domain.LeaderBoardRow;
import microservices.book.gamification.repository.ScoreCardRepository;

public class LeaderBoardServiceImplTest {

	private LeaderBoardServiceImpl leaderBoardService;
	
	@Mock
    private ScoreCardRepository scoreCardRepository;
	
	@Before
    public void setUp() {		
		// With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void retrieveTop10LeadersTest() {
		
		List<LeaderBoardRow> expectedRows = createLeaderBoardRows(5);
		given(scoreCardRepository.findFirst10()).willReturn(expectedRows);
		
		List<LeaderBoardRow> actualRows = leaderBoardService.getCurrentLeaderBoard();
		
		assertThat(actualRows).isEqualTo(expectedRows);
	}
	
	private List<LeaderBoardRow> createLeaderBoardRows(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> new LeaderBoardRow())
                .collect(Collectors.toList());
    }
}