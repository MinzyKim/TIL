package lab.spring.di.test;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lab.spring.di.service.UserService;

public class MassageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("annotConfig.xml");
		
		Locale locale = Locale.getDefault();
		String greet = context.getMessage("greeting", new Object[0], locale);
		System.out.println("default locale 인삿말 : "+greet);
		
		Locale locale_en = Locale.ENGLISH;
		greet = context.getMessage("greeting", new Object[0], locale_en);
		System.out.println("English locale 인삿말 : "+greet);
		
		UserService proc = context.getBean("loginService", UserService.class);
		
		String[] results = proc.login("admin", "a1234");
		//String[] results = proc.login("korea", "1234");
		
		for(String m : results) {
			System.out.println(m);
		}
	}

}
