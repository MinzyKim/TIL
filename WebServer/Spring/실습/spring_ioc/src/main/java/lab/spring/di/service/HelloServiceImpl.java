package lab.spring.di.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lab.spring.di.persist.Message;

@Service("hello")
public class HelloServiceImpl implements HelloService {
	
	@Autowired
	//@Qualifier("simple")
	@Resource(name="detail")
	private Message message;
	
	public HelloServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sayHello() {
		System.out.println(message.getMessage());
	}
	
	public void setMessage(Message message) {
		this.message=message;
	}
	
//	public void setMessage(Message message) {
//		this.message = message;
//	}

}