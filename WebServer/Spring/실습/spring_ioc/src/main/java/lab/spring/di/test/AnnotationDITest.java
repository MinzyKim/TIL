package lab.spring.di.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lab.spring.di.service.HelloService;

public class AnnotationDITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		String beanName = "hello";
		HelloService service1= context.getBean("hello", HelloService.class);
		service1.sayHello();
		HelloService service2 = context.getBean("hello", HelloService.class);
		service2.sayHello();
		System.out.println("스프링이 생성해선 빈이 singleton이라면 동일한 객체 리턴"+(service1==service2));
		
	}
}
