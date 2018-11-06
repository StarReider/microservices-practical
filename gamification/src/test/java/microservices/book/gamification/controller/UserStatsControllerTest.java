package microservices.book.gamification.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import microservices.book.gamification.domain.GameStats;
import microservices.book.gamification.service.GameService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserStatsController.class)
public class UserStatsControllerTest {

	@Autowired
	private MockMvc mvc;
	
	// This object will be magically initialized by the initFields method below.
	private JacksonTester<GameStats> json;
	
	@MockBean
	private GameService gameService;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void getStatsForUserTest() throws Exception {
		
		// given
		Long userId  = 1L;
		GameStats gameStats = GameStats.emptyStats(userId);
		given(gameService.retrieveStatsForUser(userId)).willReturn(gameStats);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				get("/stats").param("userId", String.valueOf(userId))
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(gameStats).getJson());
	}
}
