package microservices.book.gamification.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices.book.gamification.domain.LeaderBoardRow;
import microservices.book.gamification.service.LeaderBoardService;

@RunWith(SpringRunner.class)
@WebMvcTest(LeaderBoardController.class)
public class LeaderBoardControllerTest {

	@Autowired
	private MockMvc mvc;
	
	// This object will be magically initialized by the initFields method below.
	private JacksonTester<List<LeaderBoardRow>> json;
	
	@MockBean
	private LeaderBoardService leaderBoardService;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void getLeaderBoardTest() throws Exception {
		
		// given
		List<LeaderBoardRow> leaderBoard = createLeaderBoardRows(5);
		given(leaderBoardService.getCurrentLeaderBoard()).willReturn(leaderBoard);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				get("/leaders")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(leaderBoard).getJson());
	}
	
	private List<LeaderBoardRow> createLeaderBoardRows(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> new LeaderBoardRow())
                .collect(Collectors.toList());
    }
}