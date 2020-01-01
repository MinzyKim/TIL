package lab.spring.di.persist;

import org.springframework.stereotype.Component;

@Component("detail")
public class DetailMessage implements Message {
	public String getMessage() {
		return "Detail Message";
	}
}
