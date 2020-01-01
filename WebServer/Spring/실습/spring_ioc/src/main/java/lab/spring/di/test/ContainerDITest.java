package lab.spring.di.test;

import lab.spring.di.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContainerDITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

String beanName = "hello";
HelloService service1 = context.getBean(beanName, HelloService.class);
service1.sayHello();

HelloService service2 = context.getBean("hello", HelloService.class);
service2.sayHello();
System.out.println("스프링이 생성해선 빈이 singleton이라면 동일한 객체 리턴"+(service1==service2));
	}
}