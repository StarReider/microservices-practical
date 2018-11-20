package microservices.book;

import java.util.List;
import java.util.stream.IntStream;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import microservices.book.testutils.MultiplicationApplication;
import microservices.book.testutils.beans.AttemptResponse;
import microservices.book.testutils.beans.Stats;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplicationFeatureSteps {
	
	private MultiplicationApplication app;
	private AttemptResponse lastAttemptResponse;
	private Stats lastStatsResponse;
	
	public MultiplicationFeatureSteps() {
		this.app = new MultiplicationApplication();
	}
	
	@Before
	public void cleanUp() {
		app.deleteData();
	}
	
	@When("^the user ([^\\s]+) sends (\\d+) ([^\\s]+) attempts$")
	public void the_user_sends_attempts(final String userAlias, final int attempts, final String rightOrWrong) throws Throwable {
	    int attemptsSent = IntStream.range(0, attempts).
	    		mapToObj(i -> app.sendAttempt(userAlias, 10, 10,
								    		  "right".equals(rightOrWrong) ? 100 : 258))
								    		  // store last attempt for later use
								    		  .peek(response -> lastAttemptResponse = response)
								    		  .mapToInt(response -> response.isCorrect() ? 1 : 0)
								    		  .sum();
	    
	    assertThat(attemptsSent).isEqualTo("right".equals(rightOrWrong) ? attempts : 0)
	    		.withFailMessage("Error sending attempts to the application");
	}

	@Then("^the user gets a response indicating the attempt is ([^\\s]+)$")
	public void the_user_gets_a_response_indicating_the_attempt_is(final String rightOrWrong) throws Throwable {
			assertThat(lastAttemptResponse.isCorrect())
			.isEqualTo("right".equals(rightOrWrong))
			.withFailMessage("Expecting a response with a " + rightOrWrong + " attempt");
	}

	@Then("^the user gets (\\d+) points for the attempt$")
	public void the_user_gets_points_for_the_attempt(final int points) throws Throwable {
		long attemptId = lastAttemptResponse.getId();
		Thread.sleep(2000);
		int score = app.getScoreForAttempt(attemptId).getScore();
		assertThat(score).isEqualTo(points);
	}

	@Then("^the user gets the ([^\\s]+) badge$")
	public void the_user_gets_the_type_badge(final String badgeType) throws Throwable {
		long userId = lastAttemptResponse.getUser().getId();
		Thread.sleep(200);
		lastStatsResponse = app.getStatsForUser(userId);
		List<String> userBadges = lastStatsResponse.getBadges();
		assertThat(userBadges).contains(badgeType);
	}

	@Given("^the user john_snow sends (\\d+) right attempts$")
	public void the_user_john_snow_sends_right_attempts(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^the user gets the FIRST_WON badge$")
	public void the_user_gets_the_FIRST_WON_badge() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the user does not get any badge$")
	public void the_user_does_not_get_any_badge() throws Throwable {
		long userId = lastAttemptResponse.getUser().getId();
		Stats stats = app.getStatsForUser(userId);
		
		List<String> userBadges = stats.getBadges();
		
		if (stats.getScore() == 0) {
			assertThat(stats.getBadges()).isNullOrEmpty();
		} else {
			assertThat(userBadges).isEqualTo(lastStatsResponse.getBadges());
		}
	}

	@When("^the user john_snow sends (\\d+) wrong attempts$")
	public void the_user_john_snow_sends_wrong_attempts(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the user gets a response indicating the attempt is wrong$")
	public void the_user_gets_a_response_indicating_the_attempt_is_wrong() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the user gets the BRONZE_MULTIPLICATOR badge$")
	public void the_user_gets_the_BRONZE_MULTIPLICATOR_badge() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the user gets the SILVER_MULTIPLICATOR badge$")
	public void the_user_gets_the_SILVER_MULTIPLICATOR_badge() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the user gets the GOLD_MULTIPLICATOR badge$")
	public void the_user_gets_the_GOLD_MULTIPLICATOR_badge() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Given("^the user has (\\d+) points$")
	public void the_user_has_points(final int points) throws Throwable {
		long userId = lastAttemptResponse.getUser().getId();
		int statPoints = app.getStatsForUser(userId).getScore();
		
		assertThat(points).isEqualTo(statPoints);
	}
	
	public AttemptResponse getLastAttemptResponse() {
		return lastAttemptResponse;
	}
	
	public Stats getLastStatsResponse() {
		return lastStatsResponse;
	}
	
	public MultiplicationApplication getApp() {
		return app;
	}
}