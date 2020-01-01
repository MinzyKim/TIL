package lab.spring.di.test;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lab.spring.di.service.HelloService;

public class LifecycleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource resource = new ClassPathResource("application.xml");
		//Spring ioc 컨테이너 객체 생성
		XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
		String beanName="service";
		HelloService service = beanFactory.getBean("service", HelloService.class);
		service.sayHello();
		//컨테이너로부터 빈 제거 
		beanFactory.removeBeanDefinition(beanName);

	}

}
