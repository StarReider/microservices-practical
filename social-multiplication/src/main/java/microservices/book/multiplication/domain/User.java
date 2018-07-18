package microservices.book.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
* Identifies the attempt from a {@link User} to solve a
* {@link Multiplication}.
*/
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class User {

	private final String alias;
	
	// Empty constructor for JSON (de)serialization
	protected User() {
		alias = null;
	}
}