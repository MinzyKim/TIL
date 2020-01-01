package lab.spring.di.persist;

import org.springframework.stereotype.Component;

@Component("simple")
public class SimpleMessage implements Message {
	public String getMessage() {
		return "Simple Message";
	}
}
